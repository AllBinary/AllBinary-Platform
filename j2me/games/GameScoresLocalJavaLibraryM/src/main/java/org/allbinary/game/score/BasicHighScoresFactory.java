package org.allbinary.game.score;

import org.allbinary.game.GameInfo;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.system.SoftwareInformation;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class BasicHighScoresFactory extends HighScoresBase
{
    private final AbeClientInformationInterface abeClientInformation;
    
    public BasicHighScoresFactory(final AbeClientInformationInterface abeClientInformation, 
        final SoftwareInformation softwareInformation)
    {
        this.abeClientInformation = abeClientInformation;
    }

    private final HighScores[] highScoresArray = new HighScores[1];

    private final String TOP = "Top";
    
    private final String SCORES = "Scores";
    private final String PERSONAL_HIGH_SCORES = "Personal Top Scores";

    private final String FETCH = "fetchHighScores";
    
    public void fetchHighScores(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener) {
        
        LogUtil.put(LogFactory.getInstance("Getting Local HighScores", this, FETCH));
        this.fetchHighScores(gameInfo, highScoresResultsListener, true);
    }
    
    public void fetchHighScores(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener, final boolean preload)
    {
        //System.gc();

        try {
            highScoresArray[0] = RecordStoreHighScores.getInstance(abeClientInformation, gameInfo,
                TOP, PERSONAL_HIGH_SCORES, SCORES, new ScoreComparator(true));

            highScoresResultsListener.setHighScoresArray(highScoresArray);
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, FETCH, e));

            //super.createHighScores(gameInfo);
        }
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
