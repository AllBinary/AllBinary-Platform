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
package org.allbinary.canvas;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.thread.RunnableInterface;
import org.allbinary.thread.ThreadObjectUtil;
import org.allbinary.time.TimeDelayHelper;
import javax.microedition.lcdui.CommandListener;
import org.allbinary.logic.basic.string.CommonLabels;

public class RunnableCanvas extends MyCanvas 
    implements RunnableInterface
{
    private Thread thread;
    private Thread currentThread;

    private boolean running;

    // private int wait;
    private final TimeDelayHelper loopTimeHelper = new TimeDelayHelper(240);

    //protected ProcessPaintable processPaintable;
    protected Processor runnableCanvasRefreshHelper;

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonLabels commonLabels = CommonLabels.getInstance();
    
    public RunnableCanvas(CommandListener commandListener)
    {
        //this.processPaintable = ProcessPaintableSingletonFactory.getInstance();
        this.runnableCanvasRefreshHelper = new RunnableCanvasRefreshHelper(this);

        if(commandListener != null)
        {
            this.initCommands(commandListener);
        }
    }

    public RunnableCanvas()
    {
        this(null);
    }

    //Empty method for overriding 
    public void initCommands(CommandListener cmdListener)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().NOT_IMPLEMENTED, this, "initCommands"));
    }

    public boolean isMainCanvas()
    {
        if (this.getCustomCommandListener() != null)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /*
     * public CommandListener getCommandListener() { return commandListener; }
     */

    public void setThread(Thread thread)
    {
        this.thread = thread;
    }

    public void setRunning(boolean running)
    {
        this.running = running;

        if (!this.running)
        {
            this.thread = null;
            synchronized(this)
            {
                ThreadObjectUtil.getInstance().notifyObject(this);
            }
        }

        LogUtil.put(LogFactory.getInstance(new StringMaker().append(IS_RUNNING).append(this.running).toString(), this, SET_RUNNING));
    }

    protected final String SET_RUNNING = "setRunning";
    private final String IS_RUNNING = "isRunning";
    private final String THREAD = "Thread: ";
    private final String NOT_EQUAL = " != ";

    // private final String NOT_RUNNING = "Not Running";

    public synchronized boolean isRunning()
    {
        if (this.thread == this.currentThread)
        {
            // TWB - Release Change
            // This is should be remarked out for production since it is called
            // every frame
            /*
             * if (!running) { LogUtil.put(LogFactory.getInstance(NOT_RUNNING,
             * this, IS_RUNNING)); }
             */
            return running;
        } else
        {
            StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(THREAD);
            if(this.thread != null)
            stringBuffer.append(this.thread.toString());
            stringBuffer.append(NOT_EQUAL);
            if(this.currentThread != null)
            stringBuffer.append(this.currentThread);
            
            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, IS_RUNNING));
            return false;
        }
    }

    public TimeDelayHelper getLoopTimeHelper()
    {
        return loopTimeHelper;
    }
    
    private int pauseWait = 0;

    protected void setWait(int wait)
    {
        this.getLoopTimeHelper().setDelay(wait);
        // this.wait = wait;
        this.pauseWait = wait * 3;
    }

    protected int getWait()
    {
        return this.getLoopTimeHelper().getDelay();
    }
    
    public void setCurrentThread()
    {
        this.currentThread = Thread.currentThread();
    }

    protected void setCurrentThreadFake()
    {
        this.currentThread = thread;
    }

    protected void showNotify()
    {
        try
        {
            this.stopWaiting();
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "showNotify", e));
        }
    }

    private boolean notified = false;

    public synchronized void stopWaiting()
    throws Exception
    {
        this.notified = true;
        ThreadObjectUtil.getInstance().notifyObject(this);
        this.repaint();
    }
    
    public synchronized void waitOnNotify(int wait)
        throws Exception
    {
        if(!this.notified)
        {
            if (wait > 0)
            {
                ThreadObjectUtil.getInstance().waitObject(this, wait);
            } else
            {
                ThreadObjectUtil.getInstance().waitObject(this);
            }
        }

            /*            try
            {

            while (!this.isDisplayed())
            {
                LogUtil.put(LogFactory.getInstance("Waiting for it to be displayed", this,
                        "waitUntilDisplayed"));
                Thread.sleep(200);
            }
            LogUtil.put(LogFactory.getInstance("Displayed", this, "waitUntilDisplayed"));

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "waitUntilDisplayed", e));
        }
            */        
    }

    /*
    private final int preferredFrameRate = 12;
    private final int preferredFrameTime = 1000 / preferredFrameRate;
    private int attemptFrameRate = preferredFrameRate;
    private int attemptFrameTime = preferredFrameTime;
    */

    //private final String PAUSE_SLEEP = "pause sleep";
    //private final String GAME_SLEEP = "game sleep";
    public void processSleep() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.PROCESS, this, PAUSE_SLEEP));
        Thread.sleep(pauseWait);
    }

    public boolean isPausable()
    {
        return false;
    }
    
    public void processGameSleep(long sleep) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.PROCESS, this, GAME_SLEEP + sleep));
        Thread.sleep(sleep);
    }
    
    public void processLoopSleep() throws Exception
    {
        this.runnableCanvasRefreshHelper.process();
        
        //this.processPaintable.process();
        
        while (this.isPaused() && this.isRunning() && !this.isSingleThread())
        {
            this.processSleep();

            if(!this.isPausable())
            {
                return;
            }
        }

        //Note that if you used the game timer then elapsed would always be 0 so don't change
        long elapsedTime = this.loopTimeHelper.getElapsed();

        /*
        long refreshRate = GameStatisticsFactory.getInstance().getRefreshRate();

        if (refreshRate != attemptFrameRate)
        {
            if (refreshRate > attemptFrameRate)
            {
                attemptFrameTime++;
                if(attemptFrameRate <= this.preferredFrameRate)
                {
                    attemptFrameRate++;
                }
            } else
                if(attemptFrameTime > 10)
            {
                attemptFrameTime--;
            }
                else
                    if(attemptFrameRate > 4)
                {
                        attemptFrameRate--;
                }
        }

        if (elapsedTime > attemptFrameTime)
        {
            elapsedTime = attemptFrameTime;
        }
        
        Thread.sleep(attemptFrameTime - elapsedTime);
        */
        
        final int wait = this.loopTimeHelper.getDelay();
        
        if (elapsedTime > wait)
        {
            elapsedTime = wait;
        }
        else
        {
            processGameSleep(wait - elapsedTime);
        }
    }

    public boolean isSingleThread()
    {
        return false;
    }
    
    public void run()
    {
        this.setCurrentThread();
    }
    
    //Hack for GD
    public void end2() throws Exception {
        
    }
}
