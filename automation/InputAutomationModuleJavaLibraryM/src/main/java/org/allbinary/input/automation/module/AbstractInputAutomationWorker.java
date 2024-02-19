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
package org.allbinary.input.automation.module;

import org.allbinary.input.media.image.capture.CaptureWorkerInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.media.image.comparison.ImageComparisonWorker;
import org.allbinary.media.image.comparison.motion.MotionRectanglesWorker;
import org.allbinary.thread.RunnableInterface;
import org.allbinary.time.TimeDelayHelper;

public class AbstractInputAutomationWorker
    implements RunnableInterface//, CapturedImageWorkerResultsListener
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
            LogUtil.put(LogFactory.getInstance("Recieved Event", this, "onCaptureEvent"));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "onCaptureEvent", e));
        }
    }
    
    public void onEvent(AllBinaryEventObject allBinaryEventObject)
    {
        this.onCaptureEvent((CapturedImageWorkerResultsEvent)
        allBinaryEventObject);
    }
    */
    
    public void setThread(Thread thread)throws Exception
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

            LogUtil.put(LogFactory.getInstance("Starting CaptureWorkers - Need more images - Thread State: " + 
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
            LogUtil.put(LogFactory.getInstance("Waiting", this, this.commonStrings.RUN));
            Thread.sleep(250);
        }
    }
    
    protected void stopDataWorkers() throws Exception
    {
        this.getCaptureWorker().setRunning(false);
    }
    
    public void process() throws Exception {
        throw new RuntimeException();
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.RUN));
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
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

                LogUtil.put(LogFactory.getInstance(
                    CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed() + " Index: " + this.index, this, this.commonStrings.RUN));
            }
            
            this.stopDataWorkers();
            this.waitForDataWorkers();
            
            LogUtil.put(LogFactory.getInstance(this.commonStrings.END, this, this.commonStrings.RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, this.commonStrings.RUN, e));
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
