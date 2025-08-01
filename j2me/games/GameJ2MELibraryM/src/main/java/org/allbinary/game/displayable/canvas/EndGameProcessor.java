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
package org.allbinary.game.displayable.canvas;

import org.allbinary.canvas.Processor;
import org.allbinary.game.score.HighScores;
import org.allbinary.game.score.NullHighScoresSingletonFactory;
import org.allbinary.graphics.paint.NullPaintable;

public class EndGameProcessor extends Processor
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private AllBinaryGameCanvas gameCanvas;

    private final long WAIT = 5000;

    public EndGameProcessor(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }
    
    @Override
    public void process() throws Exception
    {
        // Only Show End of game for people
        if (this.gameCanvas.isHighScoreSubmitted())
        {
            // logUtil.put("Score Submitted: Time In State: " + this.getGameStateTimeHelper().getElapsed(), this, "showEndOfGame");
            if (this.gameCanvas.getGameStateTimeHelper().isElapsed(WAIT))
            {
                if (this.gameCanvas.getGameState() == AllBinaryGameCanvas.SHOW_END_RESULT_GAME_STATE)
                {
                    final HighScores highScores = this.gameCanvas.highScoresHelper.getSelectedHighScores();
                    if(highScores != NullHighScoresSingletonFactory.getInstance()) {
                        this.gameCanvas.highScoresHelper.selectHighScores();
                        this.gameCanvas.getRealHighScoresPaintable().setHighScores(highScores);
                        this.gameCanvas.setGameState(AllBinaryGameCanvas.SHOW_HIGH_SCORE_GAME_STATE);
                        this.gameCanvas.setHighScoresPaintable(this.gameCanvas.getRealHighScoresPaintable());
                    }
                }
                else if (this.gameCanvas.getGameState() == AllBinaryGameCanvas.SHOW_HIGH_SCORE_GAME_STATE)
                {
                    this.gameCanvas.setGameState(AllBinaryGameCanvas.SHOW_END_RESULT_GAME_STATE);
                    this.gameCanvas.setHighScoresPaintable(NullPaintable.getInstance());
                }
            }
        }
    }
}
