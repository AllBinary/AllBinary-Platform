package org.allbinary.game.score;

import org.allbinary.game.GameInfo;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.SoftwareInformation;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class BasicHighScoresFactory extends HighScoresBase
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
    
    @Override
    public void fetchHighScores(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener) {
        
        this.logUtil.putF("Getting Local HighScores", this, this.FETCH);
        this.fetchHighScoresPreload(gameInfo, highScoresResultsListener, true);
    }
    
    @Override
    public void fetchHighScoresPreload(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener, final boolean preload)
    {
        //System.gc();

        try {
            this.highScoresArray[0] = RecordStoreHighScores.getInstance(this.abeClientInformation, gameInfo,
                this.TOP, this.PERSONAL_HIGH_SCORES, this.SCORES, new ScoreComparator(true));

            highScoresResultsListener.setHighScoresArray(this.highScoresArray);
        } catch (Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.FETCH, e);

            //super.createHighScores(gameInfo);
        }
    }
    
    @Override
    public HighScoresResultsListener createHighScoresHelper() {
        return new HighScoresHelper2();
    }

    public static boolean loaded(final int index2) {
        if(index2 >= 0) {
            return true;
        }
        return false;
    }

}
