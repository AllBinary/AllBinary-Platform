package org.allbinary.game.score;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.SoftwareInformation;
import org.allbinary.game.GameInfo;

public class BasicHighScoresFactory extends HighScoresBase
{
    private final SoftwareInformation softwareInformation;
    
    public BasicHighScoresFactory(final SoftwareInformation softwareInformation)
    {
        this.softwareInformation = softwareInformation;
    }

    private final HighScores[] highScoresArray = new HighScores[1];

    private final String TOP = "Top";
    
    private final String SCORES = "Scores";
    private final String PERSONAL_HIGH_SCORES = "Personal Top Scores";

    public HighScores[] createHighScores(final GameInfo gameInfo) {
        return this.createHighScores(gameInfo, true);
    }
    
    public HighScores[] createHighScores(final GameInfo gameInfo, final boolean preload)
    {
        System.gc();

        try
        {
            highScoresArray[0] = RecordStoreHighScores.getInstance(softwareInformation, gameInfo,
                    TOP, PERSONAL_HIGH_SCORES, SCORES, new ScoreComparator(true));
            
            return highScoresArray;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "createHighScores", e));
            
            return super.createHighScores(gameInfo);
        }
    }
    
    public HighScoresHelperBase createHighScoresHelper() {
        return new HighScoresHelper2();
    }

}