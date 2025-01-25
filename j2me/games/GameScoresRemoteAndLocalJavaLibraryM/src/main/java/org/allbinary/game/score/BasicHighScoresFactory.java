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
package org.allbinary.game.score;

import org.allbinary.game.BasicHighScoresGameInfoFactory;
import org.allbinary.game.GameInfo;
import org.allbinary.game.GameType;
import org.allbinary.game.GameTypeFactory;
import org.allbinary.game.score.remote.RemoteHighScores;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.system.SoftwareInformation;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.thread.SecondaryThreadPool;

public class BasicHighScoresFactory extends HighScoresBase
{
    private final AbeClientInformationInterface abeClientInformation;
    private final SoftwareInformation softwareInformation;

    public BasicHighScoresFactory(final AbeClientInformationInterface abeClientInformation, final SoftwareInformation softwareInformation)
    {
        this.abeClientInformation = abeClientInformation;
        this.softwareInformation = softwareInformation;
    }

    private final HighScores[] highScoresArray = new HighScores[2];
    
    private final String TOP = "Top";
    
    private final String SCORES = "Scores";
    private final String PERSONAL_HIGH_SCORES = "Personal Top Scores";
    private final String WORLD_TOP_SCORES = "World Top Scores";
    
    private final String FETCH = "fetchHighScores";
    
    public void fetchHighScores(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener)
    {
        LogUtil.put(LogFactory.getInstance("Getting Remote/Local HighScores", this, FETCH));
        this.fetchHighScores(gameInfo, highScoresResultsListener, true);
    }
    
    public void fetchHighScores(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener, final boolean preload)
    {
        //System.gc();

        SecondaryThreadPool.getInstance().runTask(new Runnable() {
            public void run() {
                
                try {
                    LogUtil.put(LogFactory.getInstance(commonStrings.START, this, FETCH));

                    highScoresArray[0] = RecordStoreHighScores.getInstance(abeClientInformation, gameInfo,
                        TOP, PERSONAL_HIGH_SCORES, SCORES, new ScoreComparator(true));

                    final GameType gameType = gameInfo.getGameType();

                    final GameTypeFactory gameTypeFactory = GameTypeFactory.getInstance();

                    GameInfo gameInfo2 = gameInfo;
                    if (gameType == gameTypeFactory.SINGLE_PLAYER || gameType == gameTypeFactory.BOT) {
                        gameInfo2 = BasicHighScoresGameInfoFactory.getInstance().SINGLE_PLAYER_SERVER;
                    } else if (gameType == gameTypeFactory.MULTI_PLAYER) {
                        gameInfo2 = BasicHighScoresGameInfoFactory.getInstance().MULTI_PLAYER_SERVER;
                    }

                    highScoresArray[1]
                        = //RemoteErrorHighScoresSingletonFactory.getInstance();
                        RemoteHighScores.getInstance(abeClientInformation,
                            softwareInformation, gameInfo2,
                            WORLD_TOP_SCORES, SCORES, BooleanFactory.getInstance().FALSE, preload);

                    LogUtil.put(LogFactory.getInstance(commonStrings.END, this, FETCH));

                    LastFetchHighScoresFactory.getInstance().highScoresArray = highScoresArray;
                    highScoresResultsListener.setHighScoresArray(highScoresArray);
                } catch (Exception e) {
                    LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, FETCH, e));

                    //return super.createHighScores(gameInfo);
                }

            }
        });
        
    }
    
    public HighScoresHelperBase createHighScoresHelper() {
        return new HighScoresHelper2();
    }

    public static boolean loaded(final int index2) {
        if(index2 >= 0) {
            return true;
        }
        return false;
    }
    
}
