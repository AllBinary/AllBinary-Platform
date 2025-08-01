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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;

public class GameFrameRunnable extends GameRunnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    public GameFrameRunnable(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    
    @Override
    public void run()
    {
        try
        {
            final AllBinaryGameCanvas allBinaryGameCanvas = this.allBinaryGameCanvas;
            
            allBinaryGameCanvas.getLoopTimeHelperP().setStartTime(gameTickTimeDelayHelper.setStartTime());

            allBinaryGameCanvas.processGame();

            if (!allBinaryGameCanvas.isRunning() || allBinaryGameCanvas.isPaused())
            {
                allBinaryGameCanvas.notifyDonePainting();
            }
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION,this, commonStrings.RUN, e);
        }
    }
}

