/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.game.score.remote;

import java.util.Hashtable;
import java.util.Vector;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.SoftwareInformation;
import allbinary.game.GameInfo;
import allbinary.game.score.HighScore;
import allbinary.game.score.HighScores;
import allbinary.game.score.RemoteErrorHighScoresSingletonFactory;

public class RemoteHighScores extends HighScores
{

   private static final Hashtable hashTable = new Hashtable();
   private final SoftwareInformation softwareInformation;
   private Boolean ascending;
   
   private final String HIGH_SCORES = "HIGH_SCORES";
   public final String SOFTWARE_INFORMATION = "SOFTWARE_INFORMATION";
   public final String ASCENDING = "ASCENDING";

   private RemoteHighScores(
           SoftwareInformation softwareInformation, GameInfo gameInfo,
           String heading, String columnTwoHeading, Boolean ascending)
           throws Exception
   {
      super(gameInfo.toString(), heading, columnTwoHeading);

      this.softwareInformation = softwareInformation;

      this.setAscending(ascending);

      RemoteHighScoresProcessorFactory.getInstance().process(this, gameInfo);
   }

   public static synchronized HighScores getInstance(
           SoftwareInformation softwareInformation, GameInfo gameInfo,
           String heading, String columnTwoHeading, Boolean isAscending)
   {
      try
      {
         HighScores highScores = (HighScores) hashTable.get(gameInfo);

         if (highScores == null)
         {
            highScores = new RemoteHighScores(
                    softwareInformation, gameInfo,
                    heading, columnTwoHeading, isAscending);

            hashTable.put(gameInfo, highScores);
         }

         return highScores;
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, RemoteErrorHighScoresSingletonFactory.getInstance(), CommonStrings.getInstance().GET_INSTANCE, e));
         return RemoteErrorHighScoresSingletonFactory.getInstance();
      }
   }

   public void add(HighScore newHighScore)
   {
      RemoteHighScoresSubmissionProcessorFactory.getInstance().process(this, newHighScore);
   }

   //This is called when the data comes back in the response
   public void update(Hashtable hashtable)
   {
      this.getList().clear();
      Vector vector = (Vector) hashtable.get(HIGH_SCORES);
      for (int index = 0; index < vector.size(); index++)
      {
         Vector highScoreVector = (Vector) vector.elementAt(index);
         String displayName = (String) highScoreVector.elementAt(0);
         String score = (String) highScoreVector.elementAt(1);

         long longScore = Long.parseLong(score);
         //Long.valueOf(score).longValue()

         HighScore highScore = new HighScore(-1, displayName, null, longScore);

         this.getList().add(highScore);
      }
   }

   private void setAscending(Boolean ascending)
   {
      this.ascending = ascending;
   }

   public Boolean getAscending()
   {
      return ascending;
   }

   public SoftwareInformation getSoftwareInformation()
   {
      return softwareInformation;
   }
}
