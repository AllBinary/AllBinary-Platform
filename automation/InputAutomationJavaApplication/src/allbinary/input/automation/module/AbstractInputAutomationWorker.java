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
package allbinary.input.automation.module;


import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.input.media.image.capture.CaptureWorkerInterface;
import allbinary.media.image.comparison.ImageComparisonWorker;
import allbinary.media.image.comparison.motion.MotionRectanglesWorker;

import allbinary.thread.RunnableInterface;

import allbinary.time.TimeHelper;

abstract public class AbstractInputAutomationWorker
    implements RunnableInterface//, CapturedImageWorkerResultsListener
{
    private long index;

    private boolean running;
    
    private InputAutomationActionInterface inputAutomationActionInterface;
    
    private CaptureWorkerInterface captureWorkerInterface;
    private ImageComparisonWorker imageComparisonWorker;
    private MotionRectanglesWorker motionRectanglesWorker;

    private Thread captureThread;

    public AbstractInputAutomationWorker(
        InputAutomationActionInterface inputAutomationActionInterface)
        throws Exception
    {
    }
    
    /*
    public void onCaptureEvent(
        CapturedImageWorkerResultsEvent capturedImageWorkerResultsEvent)
    {
        try
        {
            LogUtil.put(new Log("Recieved Event", this, "onCaptureEvent"));
        }
        catch (Exception e)
        {
            LogUtil.put(new Log("Exception", this, "onCaptureEvent", e));
        }
    }
    
    public void onEvent(AllBinaryEventObject allBinaryEventObject)
    {
        this.onCaptureEvent((CapturedImageWorkerResultsEvent)
        allBinaryEventObject);
    }
    */
    
    public synchronized boolean isRunning()
    {
        return running;
    }
    
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }

    protected synchronized boolean isAnyDataWorkerRunning()
    {
        if(captureThread != null && 
            (captureThread.isAlive() || this.getCaptureWorker().isRunning() ||
            this.getMotionRectanglesWorker().isRunning() ||
            this.getImageComparisonWorker().isRunning()))
        {
            return true;
        }
        return false;
    }
    
    protected synchronized void startDataWorkers() throws Exception
    {
        if(!isAnyDataWorkerRunning())
        {
            captureThread = new Thread(this.getCaptureWorker());

            LogUtil.put(new Log("Starting CaptureWorkers - Need more images - Thread State: " + 
                captureThread.getState().toString(), 
                this, "startCaptureWorkers"));

            captureThread.start();
            //this.getCaptureWorker().run();
        }
    }
    
    protected synchronized void waitForDataWorkers() throws Exception
    {
        while(isAnyDataWorkerRunning())
        {
            LogUtil.put(new Log("Waiting", this, "run"));
            Thread.sleep(250);
        }
    }
    
    protected void stopDataWorkers() throws Exception
    {
        this.getCaptureWorker().setRunning(false);
    }
    
    public abstract void process() throws Exception;
    
    public void run()
    {
        try
        {
            LogUtil.put(new Log("Start", this, "run"));
            
            this.setRunning(true);
            
            TimeHelper timeHelper = new TimeHelper(1000);
            
        /*
        Iterator iterator = robotVector.iterator();
        while(iterator.hasNext())
        {
            InputRobotInterface robot = (InputRobotInterface) iterator.next();
            basicAttackInterface.test(robot);
        }
         */
            
            while(this.isRunning())
            {
                timeHelper.setStartTime();
                
                this.process();
                
                this.index++;

                LogUtil.put(new Log(
                    "Time Elapsed: " + timeHelper.getElapsed() + " Index: " + this.index, this, "run"));
            }
            
            this.stopDataWorkers();
            this.waitForDataWorkers();
            
            LogUtil.put(new Log("End", this, "run"));
        }
        catch (Exception e)
        {
            LogUtil.put(new Log("Exception", this, "run", e));
        }
    }
    
    public InputAutomationActionInterface getInputAutomationActionInterface()
    {
        return inputAutomationActionInterface;
    }
    
    public void setInputAutomationActionInterface(InputAutomationActionInterface inputAutomationActionInterface)
    {
        this.inputAutomationActionInterface = inputAutomationActionInterface;
    }    

    protected CaptureWorkerInterface getCaptureWorker()
    {
        return captureWorkerInterface;
    }

    protected void setCaptureWorker(CaptureWorkerInterface captureWorkerInterface)
    {
        this.captureWorkerInterface = captureWorkerInterface;
    }

    protected ImageComparisonWorker getImageComparisonWorker()
    {
        return imageComparisonWorker;
    }

    protected void setImageComparisonWorker(ImageComparisonWorker imageComparisonWorker)
    {
        this.imageComparisonWorker = imageComparisonWorker;
    }

    protected MotionRectanglesWorker getMotionRectanglesWorker()
    {
        return motionRectanglesWorker;
    }

    protected void setMotionRectanglesWorker(MotionRectanglesWorker motionRectanglesWorker)
    {
        this.motionRectanglesWorker = motionRectanglesWorker;
    }    
}
