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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.canvas.RunnableCanvas;

public class RunnableCanvasSingleThreadStartRunnable
extends GameRunnable
{
    private final RunnableCanvas runnableCanvas;
    
    public RunnableCanvasSingleThreadStartRunnable(RunnableCanvas demoCanvas)
    {
        this.runnableCanvas = demoCanvas;
    }

    public void run()
    {
        try
        {
            runnableCanvas.run();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this, commonStrings.RUN, e));
        }
    }
    
    public void processLoopSleep()
    throws Exception
    {
        runnableCanvas.processLoopSleep();
    }
}
