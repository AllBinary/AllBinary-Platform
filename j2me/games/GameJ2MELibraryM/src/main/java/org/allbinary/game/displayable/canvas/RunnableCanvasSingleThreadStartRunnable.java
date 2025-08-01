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

import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class RunnableCanvasSingleThreadStartRunnable
extends GameRunnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final RunnableCanvas runnableCanvas;
    
    public RunnableCanvasSingleThreadStartRunnable(RunnableCanvas demoCanvas)
    {
        this.runnableCanvas = demoCanvas;
    }

    @Override
    public void run()
    {
        try
        {
            runnableCanvas.run();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION,this, commonStrings.RUN, e);
        }
    }
    
    @Override
    public void processLoopSleep()
    throws Exception
    {
        runnableCanvas.processLoopSleep();
    }
}
