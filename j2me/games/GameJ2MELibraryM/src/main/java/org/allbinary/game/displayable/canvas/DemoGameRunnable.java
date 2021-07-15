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

public class DemoGameRunnable extends GameRunnable
{
    private final DemoCanvas demoCanvas;
    
    public DemoGameRunnable(DemoCanvas demoCanvas)
    {
        this.demoCanvas = demoCanvas;
    }

    private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
        GameTickTimeDelayHelperFactory.getInstance();

    public void run()
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().RUN));

            //final AllBinaryGameCanvas allBinaryGameCanvas = this.allBinaryGameCanvas;
            
            demoCanvas.getLoopTimeHelper().setStartTime(
                    gameTickTimeDelayHelperFactory.setStartTime());

            demoCanvas.processGame();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION,
                    this, CommonStrings.getInstance().RUN, e));
        }
    }
    
    public void processLoopSleep()
    throws Exception
    {
        demoCanvas.processLoopSleep();
    }
}

