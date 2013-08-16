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
package allbinary.game.displayable.canvas;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.time.GameTickTimeDelayHelperFactory;

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

