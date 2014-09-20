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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.time.GameTickTimeDelayHelperFactory;

public class GameFrameRunnable extends GameRunnable
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    public GameFrameRunnable(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }

    private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
        GameTickTimeDelayHelperFactory.getInstance();
    
    public void run()
    {
        try
        {
            final AllBinaryGameCanvas allBinaryGameCanvas = this.allBinaryGameCanvas;
            
            allBinaryGameCanvas.getLoopTimeHelper().setStartTime(
                    gameTickTimeDelayHelperFactory.setStartTime());

            allBinaryGameCanvas.processGame();

            if (!allBinaryGameCanvas.isRunning() || allBinaryGameCanvas.isPaused())
            {
                allBinaryGameCanvas.notifyDonePainting();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION,
                    this, CommonStrings.getInstance().RUN, e));
        }
    }
}

