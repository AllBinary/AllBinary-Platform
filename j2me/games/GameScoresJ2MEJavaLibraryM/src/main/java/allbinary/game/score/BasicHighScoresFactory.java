package allbinary.game.score;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.SoftwareInformation;
import allbinary.game.BasicHighScoresGameInfoFactory;
import allbinary.game.GameInfo;

public class BasicHighScoresFactory extends NoHighScoresFactory 
implements HighScoresFactoryInterface
{
    private SoftwareInformation softwareInformation;

    public BasicHighScoresFactory(SoftwareInformation softwareInformation)
    {
        this.softwareInformation = softwareInformation;
    }

    private final HighScores[] highScoresArray = new HighScores[1];

    public HighScores[] createHighScores(GameInfo gameInfo)
    {
        System.gc();

        try
        {
            highScoresArray[0] = RecordStoreHighScores.getInstance("Top",
                    "Personal Top Scores", "Scores", new ScoreComparator(true));

            return highScoresArray;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "createHighScores", e));
            
            return super.createHighScores(gameInfo);
        }
    }
}
