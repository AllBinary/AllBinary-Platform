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
package allbinary.game.score;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.bool.BooleanFactory;
import abcs.logic.system.SoftwareInformation;
import allbinary.game.BasicHighScoresGameInfoFactory;
import allbinary.game.GameInfo;
import allbinary.game.GameType;
import allbinary.game.GameTypeFactory;
import allbinary.game.score.remote.RemoteHighScores;

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
        System.gc();

        try
        {
            highScoresArray[0] = RecordStoreHighScores.getInstance(
                    TOP, PERSONAL_HIGH_SCORES, SCORES, new ScoreComparator(true));

            GameType gameType = gameInfo.getGameType();
            
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
            
            highScoresArray[1] = RemoteHighScores.getInstance(
                    this.softwareInformation, gameInfo,
                    WORLD_TOP_SCORES, SCORES, BooleanFactory.getInstance().FALSE);

            return highScoresArray;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "createHighScores", e));
            
            return super.createHighScores(gameInfo);
        }
    }
}
