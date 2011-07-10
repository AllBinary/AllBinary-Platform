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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.xmlrpc.XmlRpcAbeClient;
import abcs.logic.java.bool.BooleanFactory;
import abcs.logic.system.security.licensing.AbeClientInformationInterface;
import abcs.logic.system.security.licensing.AbeClientInformationInterfaceFactory;
import allbinary.game.configuration.GameConfigurationCentral;
import allbinary.game.score.HighScore;
import allbinary.graphics.displayable.DisplayInfoSingleton;

public class LicensedRemoteHighScoresSubmissionProcessor
        implements RemoteHighScoresSubmissionProcessorInterface
{
    public LicensedRemoteHighScoresSubmissionProcessor()
    {
    }

    //String customerUserName, 
    public synchronized void process(RemoteHighScores remoteHighScores, HighScore highScore)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Begin Remote HighScores", this, CommonStrings.getInstance().PROCESS));

            // System.out.println(message);
            AbeClientInformationInterface abeClientInformation = 
                AbeClientInformationInterfaceFactory.getInstance();
            
            Hashtable hashtable = abeClientInformation.toHashtable();

            //hashtable.put(RemoteHighScoresData.getInstance().GAME_INFO, highScore.getGameInfo().toString());

            //HashtableUtil.putAll(highScore.getGameInfo().toHashtable(), hashtable);
            hashtable.putAll(highScore.getGameInfo().toHashtable());
            
            hashtable.put(RemoteHighScoresData.getInstance().CUSTOMER_USER_NAME, "None");
            hashtable.put(RemoteHighScoresData.getInstance().DISPLAY_NAME, highScore.getName());

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

            //hashtable.put(RemoteHighScoresData.getInstance().SCORE, new Long(highScore.getScore()).toString());
            hashtable.put(RemoteHighScoresData.getInstance().SCORE, Long.toString(highScore.getScore()));
            
            if (XmlRpcAbeClient.isOnline)
            {
                Hashtable resultHashtable = (Hashtable) new XmlRpcRemoteHighScoresClient(
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
