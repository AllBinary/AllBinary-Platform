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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterfaceFactory;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.game.score.HighScore;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import java.util.Hashtable;
import org.allbinary.game.GameInfoData;
import org.allbinary.logic.java.bool.BooleanFactory;

public class RemoteHighScoresSubmissionProcessor
        implements RemoteHighScoresSubmissionProcessorInterface
{
    public RemoteHighScoresSubmissionProcessor()
    {
    }

    //String customerUserName, 
    public synchronized void process(RemoteHighScores remoteHighScores, HighScore highScore)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Begin Remote HighScores Submission", this, CommonStrings.getInstance().PROCESS));

            final GameInfoData gameInfoData = GameInfoData.getInstance();
            
            // System.out.println(message);
            final AbeClientInformationInterface abeClientInformation = 
                AbeClientInformationInterfaceFactory.getInstance();
            
            final Hashtable hashtable = abeClientInformation.toHashtable();

            //hashtable.put(RemoteHighScoresData.getInstance().GAME_INFO, highScore.getGameInfo().toString());

            //HashtableUtil.putAll(highScore.getGameInfo().toHashtable(), hashtable);
            hashtable.putAll(highScore.getGameInfo().toHashtable());

            hashtable.put(RemoteHighScoresData.getInstance().CUSTOMER_USER_NAME, "None");
            hashtable.put(RemoteHighScoresData.getInstance().DISPLAY_NAME, highScore.getName());

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

            //hashtable.put(RemoteHighScoresData.getInstance().SCORE, new Long(highScore.getScore()).toString());
            hashtable.put(RemoteHighScoresData.getInstance().SCORE, Long.toString(highScore.getScore()));
            
            if (XmlRpcAbeClient.isOnline)
            {
                final Hashtable resultHashtable = (Hashtable) new XmlRpcRemoteHighScoresClient(
                        abeClientInformation, "highscoresubmissionservice.php",
                        "HighScoreSubmissionService.process").get(hashtable);

                remoteHighScores.update(resultHashtable);
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().PROCESS, e));
        }
    }
}
