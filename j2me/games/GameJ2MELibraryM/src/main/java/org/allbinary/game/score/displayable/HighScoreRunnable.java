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
package org.allbinary.game.score.displayable;

import org.allbinary.J2MEUtil;
import org.allbinary.logic.ABSystemWrapper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.ARunnable;

class HighScoreRunnable extends ARunnable {

    final HighScoresCanvas highScoresCanvas;

    HighScoreRunnable(final HighScoresCanvas highScoresCanvas) {
        this.highScoresCanvas = highScoresCanvas;
    }

    @Override
    public void run() {

        final CommonStrings commonStrings = CommonStrings.getInstance();
        final LogUtil logUtil = LogUtil.getInstance();

        final HighScoresCanvas highScoresCanvas = this.highScoresCanvas;
        try {
            final boolean isHTML = J2MEUtil.isHTML();
            if (!isHTML) {
                while (!highScoresCanvas.hasPainted) {
                }
                highScoresCanvas.hasPainted = false;
            }
            final StringMaker stringMaker = new StringMaker();
            final ABSystemWrapper systemWrapper = ABSystemWrapper.getInstance();
            final long currentTimeMillis = systemWrapper.currentTimeMillis();
            logUtil.putF(stringMaker.append("HighScoresCanvas - Request repaint to be sure: ").appendlong(currentTimeMillis).toString(), this, commonStrings.RUN);
            highScoresCanvas.repaintBehavior.onChangeRepaint(highScoresCanvas);
            if (!isHTML) {
                while (!highScoresCanvas.hasPainted) {
                }
            }
            stringMaker.delete(0, stringMaker.length());
            logUtil.putF(stringMaker.append("HighScoresCanvas - Now that the canvas has completed repaint go ahead and fetch the scores: ").appendlong(currentTimeMillis).toString(), this, commonStrings.RUN);
            highScoresCanvas.executeUpdate();
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
        }

    }
}
