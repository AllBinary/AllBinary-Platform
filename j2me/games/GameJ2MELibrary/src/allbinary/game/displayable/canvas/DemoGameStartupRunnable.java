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
import allbinary.thread.RunnableInterface;

public class DemoGameStartupRunnable implements RunnableInterface
{
    private final DemoCanvas demoCanvas;
    
    private boolean running;

    public DemoGameStartupRunnable(DemoCanvas demoCanvas)
    {
        this.demoCanvas = demoCanvas;
    }
    
    public void setThread(Thread thread)
    {
    }

    public synchronized boolean isRunning()
    {
        return running;
    }

    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }

    public void run()
    {
        try
        {
            this.setRunning(true);
            // LogUtil.put(LogFactory.getInstance("Start Runnable",
            // this, CommonStrings.getInstance().RUN));

            this.demoCanvas.stopGameDemo();

            this.demoCanvas.create();

            this.demoCanvas.start();

            this.setRunning(false);
            // LogUtil.put(LogFactory.getInstance("End Runnable", this,
            // CommonStrings.getInstance().RUN));
        }
        catch (Exception e)
        {
            this.setRunning(false);
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
        }
    }
}

