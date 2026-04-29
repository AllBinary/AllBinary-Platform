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

import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.displayable.canvas.NullWaitGameRunnable;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.NullThread;
import org.allbinary.thread.RunnableInterface;
import org.allbinary.thread.ThreadObjectUtil;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayList;

public class RunnableCanvas extends MyCanvas 
    implements RunnableInterface
{
    
    protected final NullUtil nullUtil = NullUtil.getInstance();
    
    private Thread thread = NullThread.NULL_THREAD;
    private Thread currentThread = NullThread.NULL_THREAD;

    private boolean running;

    // private int wait;
    protected final TimeDelayHelper loopTimeHelper = new TimeDelayHelper(NullWaitGameRunnable.getInstance().WAIT);

    //protected ProcessPaintable processPaintable;
    protected Processor runnableCanvasRefreshHelper = Processor.getInstance();

    protected final CommonLabels commonLabels = CommonLabels.getInstance();
    
    protected final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
    
    public RunnableCanvas(final CommandListener commandListener, final BasicArrayList childNameList, final boolean hasParam)
    {
        super(CommonStrings.getInstance().UNKNOWN, childNameList);
        
        this.logUtil.putF(new StringMaker().append("delay: ").appendint(this.loopTimeHelper.delay).toString(), this, this.commonStrings.CONSTRUCTOR);
        
        //this.processPaintable = ProcessPaintableSingletonFactory.getInstance();
        this.runnableCanvasRefreshHelper = new RunnableCanvasRefreshHelper(this);

        if(commandListener != null)
        {
            this.initCommands(commandListener);
        } else if(hasParam) {
            this.logUtil.putF("commandListener was null", this, "initCommands");
        }
    }

    //Empty method for overriding 
    public void initCommands(CommandListener cmdListener)
    {
        this.logUtil.putF(commonStrings.NOT_IMPLEMENTED, this, "initCommands");
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

    @Override
    public void setThread(Thread thread)
    {
        this.thread = thread;
    }

    @Override
    public void setRunning(boolean running)
    {
        this.running = running;

        if (!this.running)
        {
            this.thread = NullThread.NULL_THREAD;
            synchronized(this)
            {
                this.threadObjectUtil.notifyObject(this);
            }
        }

        this.logUtil.putF(new StringMaker().append(this.IS_RUNNING).appendboolean(this.running).toString(), this, SET_RUNNING);
    }

    protected final String SET_RUNNING = "setRunning";
    private final String IS_RUNNING = "isRunning";
    private final String THREAD = "Thread: ";
    private final String NOT_EQUAL = " != ";

    // private final String NOT_RUNNING = "Not Running";

    @Override
    public synchronized boolean isRunning()
    {
        if (this.thread == this.currentThread)
        {
            // TWB - Release Change
            // This is should be remarked out for production since it is called
            // every frame
            /*
             * if (!running) { this.logUtil.putF(NOT_RUNNING, * this, IS_RUNNING); }
             */
            return this.running;
        } else
        {
            final StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(this.THREAD);
            if(this.thread != null) {
                stringBuffer.append(this.thread.toString());
            }
            
            stringBuffer.append(this.NOT_EQUAL);

            if(this.currentThread != null) {
                stringBuffer.append(StringUtil.getInstance().toString(this.currentThread));
            }
            
            
            this.logUtil.putF(stringBuffer.toString(), this, IS_RUNNING);
            return false;
        }
    }

    public TimeDelayHelper getLoopTimeHelperP()
    {
        return this.loopTimeHelper;
    }
    
    private long pauseWait = 0;

    protected void setWait(int wait)
    {
        this.loopTimeHelper.delay = wait;
        // this.wait = wait;
        this.pauseWait = (long) wait * 3;
        
        this.logUtil.putF(new StringMaker().append("setWait - delay: ").appendint(this.loopTimeHelper.delay).toString(), this, this.commonStrings.CONSTRUCTOR);
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
        this.currentThread = this.thread;
    }

    @Override
    protected void showNotify()
    {
        try
        {
            this.stopWaiting();
        } catch (Exception e)
        {
            this.logUtil.put(commonStrings.EXCEPTION, this, "showNotify", e);
        }
    }

    private boolean notified = false;

    private void stopWaiting()
    throws Exception
    {
        this.notified = true;
        synchronized (this) {
            this.threadObjectUtil.notifyObject(this);
        }
        //System.out.println("TWB:RunnableCanvas:stopWaiting:repaint");
        this.repaint();
    }
    
    public synchronized void waitOnNotify(long wait)
        throws Exception
    {
        if(!this.notified)
        {
            if (wait > 0)
            {
                this.threadObjectUtil.waitObject(this, (long) wait);
            } else
            {
                this.threadObjectUtil.waitObject(this);
            }
        }

            /*            try
            {

            while (!this.isDisplayed())
            {
                this.logUtil.putF("Waiting for it to be displayed", this, "waitUntilDisplayed");
                Thread.sleep(200);
            }
            this.logUtil.putF("Displayed", this, "waitUntilDisplayed");

        } catch (Exception e)
        {
            this.logUtil.put(commonStrings.EXCEPTION, this, "waitUntilDisplayed", e);
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
        //this.logUtil.putF(PAUSE_SLEEP + this.pauseWait, this, commonStrings.PROCESS);
        //this.logUtil.putF(PAUSE_SLEEP + System.currentTimeMillis(), this, commonStrings.PROCESS);
        Thread.sleep(this.pauseWait);
    }

    public boolean isPausable()
    {
        return false;
    }
    
    public void processGameSleep(long sleep) throws Exception
    {
        //this.logUtil.putF(GAME_SLEEP + sleep, this, commonStrings.PROCESS);
        Thread.sleep(sleep);
    }
    
    private final String START_PAUSE = "start pause - game thread sleep at: ";
    private final String END_PAUSE = "end pause - game thread sleep at: ";
    private final String PROCESS_LOOP_SLEEP = "processLoopSleep";
    public void processLoopSleep() throws Exception
    {
        //this.logUtil.putF(commonStrings.START, this, PROCESS_LOOP_SLEEP);
        
        this.runnableCanvasRefreshHelper.process();
        
        //this.processPaintable.process();
        
        if(this.isPaused() && this.isRunning() && !this.isSingleThread()) {
            final StringMaker stringMaker = new StringMaker();
            this.logUtil.putF(stringMaker.append(this.START_PAUSE).appendlong(System.currentTimeMillis()).append(this.PAUSE_SLEEP).appendlong(this.pauseWait).toString(), this, PROCESS_LOOP_SLEEP);
            while (this.isPaused() && this.isRunning() && !this.isSingleThread()) {
                this.processSleep();

                if (!this.isPausable()) {
                    stringMaker.delete(0, stringMaker.length());
                    this.logUtil.putF(stringMaker.append(this.END_PAUSE).appendlong(System.currentTimeMillis()).toString(), this, PROCESS_LOOP_SLEEP);
                    return;
                }
            }
        }

        //Note that if you used the game timer then elapsed would always be 0 so don't change
        long elapsedTime = this.loopTimeHelper.getElapsedTNT();

        /*
        long refreshRate = GameStatisticsFactory.getInstance().getRefreshRate();

        if (refreshRate != this.attemptFrameRate)
        {
            if (refreshRate > this.attemptFrameRate)
            {
                attemptFrameTime++;
                if(this.attemptFrameRate <= this.preferredFrameRate)
                {
                    attemptFrameRate++;
                }
            } else
                if(this.attemptFrameTime > 10)
            {
                attemptFrameTime--;
            }
                else
                    if(this.attemptFrameRate > 4)
                {
                        attemptFrameRate--;
                }
        }

        if (elapsedTime > this.attemptFrameTime)
        {
            elapsedTime = this.attemptFrameTime;
        }
        
        Thread.sleep(this.attemptFrameTime - elapsedTime);
        */
        
        final long wait = (long) this.loopTimeHelper.delay;
        
        if (elapsedTime > wait)
        {
            elapsedTime = wait;
        }
        else
        {
            this.processGameSleep(wait - elapsedTime);
        }
    }

    public boolean isSingleThread()
    {
        return false;
    }
    
    @Override
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
