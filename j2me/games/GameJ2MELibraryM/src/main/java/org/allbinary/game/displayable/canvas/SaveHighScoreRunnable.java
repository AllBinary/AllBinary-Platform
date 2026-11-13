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

import org.allbinary.game.score.displayable.HighScoreTextBox;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

class SaveHighScoreRunnable implements Runnable {

    private final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
    //Fields instead of the captured locals so the class does not need the enclosing method, such as in C#
    private final boolean isLast;
    private final HighScoreTextBox textBox;

    SaveHighScoreRunnable(final boolean isLast, final HighScoreTextBox textBox) {
        this.isLast = isLast;
        this.textBox = textBox;
    }

    @Override
    public void run() {
        final LogUtil logUtil = LogUtil.getInstance();
        try {
            //Let the endgame sound play first
//                        Thread.sleep(100);
//                        progressCanvas.addPortion(6, "Saving High Score");

            if (this.isLast) {
                this.textBox.submit();
            } else {
                this.textBox.saveHighScore();
            }
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "run", e);
            this.progressCanvas.end();
        }
    }
}
