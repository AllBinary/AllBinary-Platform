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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.SoftwareInformation;
import org.allbinary.game.BasicHighScoresGameInfoFactory;
import org.allbinary.game.GameInfo;
import org.allbinary.game.GameType;
import org.allbinary.game.GameTypeFactory;
import org.allbinary.game.score.remote.RemoteHighScores;
import org.allbinary.logic.java.bool.BooleanFactory;

public class BasicHighScoresFactory extends NoHighScoresFactory 
implements HighScoresFactoryInterface
{
    private SoftwareInformation softwareInformation;

    public BasicHighScoresFactory(SoftwareInformation softwareInformation)
    {
        this.softwareInformation = softwareInformation;
    }

    private final HighScores[] highScoresArray = new HighScores[2];

    
    private static final String TOP = "Top";
    
    private static final String SCORES = "Scores";
    private static final String PERSONAL_HIGH_SCORES = "Personal Top Scores";
    private static final String WORLD_TOP_SCORES = "World Top Scores";
    
    public HighScores[] createHighScores(GameInfo gameInfo) 
    {
        return this.createHighScores(gameInfo, true);
    }
    
    public HighScores[] createHighScores(GameInfo gameInfo, final boolean preload)
    {
        System.gc();

        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "createHighScores"));
            
            highScoresArray[0] = RecordStoreHighScores.getInstance(
                    TOP, PERSONAL_HIGH_SCORES, SCORES, new ScoreComparator(true));

            final GameType gameType = gameInfo.getGameType();
            
            final GameTypeFactory gameTypeFactory = GameTypeFactory.getInstance();
            
            if(gameType == gameTypeFactory.SINGLE_PLAYER)
            {
                gameInfo = BasicHighScoresGameInfoFactory.getInstance().SINGLE_PLAYER_SERVER;
            }
            else
                if(gameType == gameTypeFactory.MULTI_PLAYER)
            {
                    gameInfo = BasicHighScoresGameInfoFactory.getInstance().MULTI_PLAYER_SERVER;
            }
            
            highScoresArray[1] = 
                    //RemoteErrorHighScoresSingletonFactory.getInstance();
                    RemoteHighScores.getInstance(
                    this.softwareInformation, gameInfo,
                    WORLD_TOP_SCORES, SCORES, BooleanFactory.getInstance().FALSE, preload);

            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this, "createHighScores"));
            
            return highScoresArray;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "createHighScores", e));
            
            return super.createHighScores(gameInfo);
        }
    }
}
