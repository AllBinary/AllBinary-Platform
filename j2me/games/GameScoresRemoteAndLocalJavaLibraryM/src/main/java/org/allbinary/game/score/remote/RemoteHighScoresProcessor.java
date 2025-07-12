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
package org.allbinary.game.score.remote;

import java.util.Hashtable;

import org.allbinary.game.GameInfo;
import org.allbinary.game.GameInfoData;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.system.security.crypt.jcehelper.NoCrypt;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.HashtableUtil;

public class RemoteHighScoresProcessor 
   implements RemoteHighScoresProcessorInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final NoCrypt noCrypt = new NoCrypt();
    
   public RemoteHighScoresProcessor()
   {
   }
   
   //String customerUserName,
   public synchronized void process(final RemoteHighScores remoteHighScores, final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo)
           throws Exception
   {
      logUtil.put("Begin Remote HighScores Retrieval", this, commonStrings.PROCESS);

      final GameInfoData gameInfoData = GameInfoData.getInstance();

      // System.out.println(message);
      final Hashtable hashtable = abeClientInformation.toHashtable();

      HashtableUtil.getInstance().putAll(gameInfo.toHashtable(), hashtable);
      //hashtable.putAll(gameInfo.toHashtable());

      //hashtable.put(RemoteHighScoresData.getInstance().GAME_INFO, gameInfo.toString());

      hashtable.put(
              gameInfoData.SOFTWARE_INFORMATION,
              remoteHighScores.getSoftwareInformation().toString());

      hashtable.put(
              remoteHighScores.ASCENDING,
              remoteHighScores.getAscending().toString());

      final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

      hashtable.put(
              displayInfoSingleton.ORIENTATION,
              BooleanFactory.getInstance().toString(displayInfoSingleton.isPortrait()));

      hashtable.put(
              RemoteHighScoresData.getInstance().GAME_CONFIGURATION,
              GameConfigurationCentral.getInstance().toString());

      if(XmlRpcAbeClient.isOnline)
      {
          Hashtable resultHashtable = (Hashtable) new XmlRpcRemoteHighScoresClient(abeClientInformation,
                  "highscoresservicessl.php", "HighScoresService.process").get(hashtable, noCrypt);

          remoteHighScores.update(resultHashtable);
      }
   }
}
