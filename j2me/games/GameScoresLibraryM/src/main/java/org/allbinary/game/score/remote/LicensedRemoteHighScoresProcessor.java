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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterfaceFactory;
import org.allbinary.game.GameInfo;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.logic.java.bool.BooleanFactory;

public class LicensedRemoteHighScoresProcessor 
   implements RemoteHighScoresProcessorInterface
{

   public LicensedRemoteHighScoresProcessor()
   {
   }

   //String customerUserName,
   public synchronized void process(RemoteHighScores remoteHighScores, GameInfo gameInfo)
           throws Exception
   {
      LogUtil.put(LogFactory.getInstance("Begin Remote HighScores", this, CommonStrings.getInstance().PROCESS));

      // System.out.println(message);
      AbeClientInformationInterface abeClientInformation =
              AbeClientInformationInterfaceFactory.getInstance();

      Hashtable hashtable = abeClientInformation.toHashtable();

      //HashtableUtil.putAll(gameInfo.toHashtable(), hashtable);
      hashtable.putAll(gameInfo.toHashtable());

      //hashtable.put(RemoteHighScoresData.getInstance().GAME_INFO, gameInfo.toString());

      hashtable.put(
              remoteHighScores.SOFTWARE_INFORMATION,
              remoteHighScores.getSoftwareInformation().toString());

      hashtable.put(
              remoteHighScores.ASCENDING,
              remoteHighScores.getAscending().toString());

      DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

      hashtable.put(
              displayInfoSingleton.ORIENTATION,
              BooleanFactory.getInstance().toString(displayInfoSingleton.isPortrait()));

      hashtable.put(
              RemoteHighScoresData.getInstance().GAME_CONFIGURATION,
              GameConfigurationCentral.getInstance().toString());

      if(XmlRpcAbeClient.isOnline)
      {
          Hashtable resultHashtable = (Hashtable) new XmlRpcRemoteHighScoresClient(abeClientInformation,
                  "highscoresservice.php", "HighScoresService.process").get(hashtable);

          remoteHighScores.update(resultHashtable);
      }
   }
}
