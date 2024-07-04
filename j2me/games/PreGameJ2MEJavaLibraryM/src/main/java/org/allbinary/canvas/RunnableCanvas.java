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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.thread.RunnableInterface;
import org.allbinary.thread.ThreadObjectUtil;
import org.allbinary.time.TimeDelayHelper;
import javax.microedition.lcdui.CommandListener;
import org.allbinary.game.displayable.canvas.NullWaitRunnable;
import org.allbinary.logic.string.CommonLabels;

public class RunnableCanvas extends MyCanvas 
    implements RunnableInterface
{
    
    private Thread thread;
    private Thread currentThread;

    private boolean running;

    // private int wait;
    protected final TimeDelayHelper loopTimeHelper = new TimeDelayHelper(NullWaitRunnable.getInstance().WAIT);

    //protected ProcessPaintable processPaintable;
    protected Processor runnableCanvasRefreshHelper;

    protected final CommonLabels commonLabels = CommonLabels.getInstance();
    
    protected final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
    
    public RunnableCanvas(final CommandListener commandListener, final boolean hasParam)
    {
        LogUtil.put(LogFactory.getInstance("delay: " + this.loopTimeHelper.delay, this, this.commonStrings.CONSTRUCTOR));
        
        //this.processPaintable = ProcessPaintableSingletonFactory.getInstance();
        this.runnableCanvasRefreshHelper = new RunnableCanvasRefreshHelper(this);

        if(commandListener != null)
        {
            this.initCommands(commandListener);
        } else if(hasParam) {
            LogUtil.put(LogFactory.getInstance("commandListener was null", this, "initCommands"));
        }
    }

    public RunnableCanvas(final CommandListener commandListener) {
        this(commandListener, true);
    }
    
    public RunnableCanvas()
    {   
        this(null, false);
    }

    //Empty method for overriding 
    public void initCommands(CommandListener cmdListener)
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.NOT_IMPLEMENTED, this, "initCommands"));
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
                threadObjectUtil.notifyObject(this);
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
            final StringMaker stringBuffer = new StringMaker();
            
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
        loopTimeHelper.delay = wait;
        // this.wait = wait;
        this.pauseWait = wait * 3;
        
        LogUtil.put(LogFactory.getInstance("setWait - delay: " + this.loopTimeHelper.delay, this, this.commonStrings.CONSTRUCTOR));
    }

    protected int getWait()
    {
        return this.loopTimeHelper.delay;
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

    private synchronized void stopWaiting()
    throws Exception
    {
        this.notified = true;
        ThreadObjectUtil.getInstance().notifyObject(this);
        //System.out.println("TWB:RunnableCanvas:stopWaiting:repaint");
        this.repaint();
    }
    
    public synchronized void waitOnNotify(int wait)
        throws Exception
    {
        if(!this.notified)
        {
            if (wait > 0)
            {
                threadObjectUtil.waitObject(this, wait);
            } else
            {
                threadObjectUtil.waitObject(this);
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

    private final String PAUSE_SLEEP = "pause sleep";
    //private final String GAME_SLEEP = "game sleep";
    public void processSleep() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(PAUSE_SLEEP + this.pauseWait, this, commonStrings.PROCESS));
        //LogUtil.put(LogFactory.getInstance(PAUSE_SLEEP + System.currentTimeMillis(), this, commonStrings.PROCESS));
        Thread.sleep(pauseWait);
    }

    public boolean isPausable()
    {
        return false;
    }
    
    public void processGameSleep(long sleep) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(GAME_SLEEP + sleep, this, commonStrings.PROCESS));
        Thread.sleep(sleep);
    }
    
    private final String START_PAUSE = "start pause - game thread sleep: ";
    private final String END_PAUSE = "end pause - game thread sleep: ";
    public void processLoopSleep() throws Exception
    {
        this.runnableCanvasRefreshHelper.process();
        
        //this.processPaintable.process();
        
        if(this.isPaused() && this.isRunning() && !this.isSingleThread()) {
            final StringMaker stringMaker = new StringMaker();
            LogUtil.put(LogFactory.getInstance(stringMaker.append(START_PAUSE).append(System.currentTimeMillis()).toString(), this, commonStrings.RUN));
            while (this.isPaused() && this.isRunning() && !this.isSingleThread()) {
                this.processSleep();

                if (!this.isPausable()) {
                    stringMaker.delete(0, stringMaker.length());
                    LogUtil.put(LogFactory.getInstance(stringMaker.append(END_PAUSE).append(System.currentTimeMillis()).toString(), this, commonStrings.RUN));
                    return;
                }
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
        
        final int wait = this.loopTimeHelper.delay;
        
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
    
    public int getType() {
        return -1;
    }

}
