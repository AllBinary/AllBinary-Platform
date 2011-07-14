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
package allbinary.media.image.comparison;

import java.awt.image.BufferedImage;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.input.media.image.capture.CapturedImageWorkerResultsEvent;

import allbinary.input.media.image.capture.CapturedImageWorkerResultsListener;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandlerAbstract;

import allbinary.time.TimeHelper;

public class ImageComparisonWorker
    extends BasicEventHandlerAbstract
    implements CapturedImageWorkerResultsListener
{
    private boolean running;
    
    private Vector bufferedImageVector;
    
    private ImageComparatorConstraintsInterface imageComparatorConstraintsInterface;
    
    private ImageComparator imageComparator;
    
    private int index;
    
    public ImageComparisonWorker(
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface)
        throws Exception
    {
        this.bufferedImageVector = new Vector();
        
        this.imageComparatorConstraintsInterface =
            imageComparatorConstraintsInterface;
        
        this.imageComparator =
            new ImageComparator(
            imageComparatorConstraintsInterface);
    }
    
    public synchronized boolean isRunning()
    {
        return running;
    }
    
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }
    
    public synchronized void onCaptureEvent(
        CapturedImageWorkerResultsEvent capturedImageWorkerResultsEvent)
    {
        this.bufferedImageVector.add(capturedImageWorkerResultsEvent);
        
        if(this.bufferedImageVector.size() > 1)
        {
            this.run();
        }
    }
    
    public synchronized void onEvent(AllBinaryEventObject allBinaryEventObject)
    {
        this.onCaptureEvent((CapturedImageWorkerResultsEvent) allBinaryEventObject);
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(new Log("Start", this, "run"));
            
            this.setRunning(true);
            
            TimeHelper timeHelper = new TimeHelper(1000);
            timeHelper.setStartTime();
            
            if(this.imageComparatorConstraintsInterface.isFrameAllowed(index))
            {
                CapturedImageWorkerResultsEvent capturedImageWorkerResultsEvent[] =
                    new CapturedImageWorkerResultsEvent[2];
                
                capturedImageWorkerResultsEvent[0] =
                    (CapturedImageWorkerResultsEvent) this.bufferedImageVector.get(0);
                capturedImageWorkerResultsEvent[1] =
                    (CapturedImageWorkerResultsEvent) this.bufferedImageVector.get(1);
                
                if(this.imageComparatorConstraintsInterface.isImageValid(
                    capturedImageWorkerResultsEvent[0].getBufferedImage()) &&
                    this.imageComparatorConstraintsInterface.isImageValid(
                    capturedImageWorkerResultsEvent[1].getBufferedImage()))
                {
                    ImageComparisonResult imageComparisonResult =
                        this.imageComparator.compare(
                        capturedImageWorkerResultsEvent[0].getBufferedImage(),
                        capturedImageWorkerResultsEvent[1].getBufferedImage(), 
                        capturedImageWorkerResultsEvent[0].getFrame(),
                        capturedImageWorkerResultsEvent[1].getFrame(), 0);
                    
                    Long frame = capturedImageWorkerResultsEvent[1].getFrame();
                    
                    ImageComparisonResultFrameCacheable imageComparisonResultFrameCacheable =
                        new ImageComparisonResultFrameCacheable(
                        imageComparisonResult, frame);
                    
                    ImageComparisonResultCacheSingleton.getInstance().add(
                        imageComparisonResultFrameCacheable);
                    
                    this.fireEvent(new ImageComparisonResultsEvent(
                        this, imageComparisonResult));
                    
                    LogUtil.put(new Log("Image Comparison Result: " +
                        imageComparisonResult.toString() + " for frame: " + frame, this, "run"));
                }
                else
                {
                    LogUtil.put(new Log(
                        "An Image Was Not Valid: Image Worker Event Processing terminated", this, "run"));
                }
            }
            
            //Remove the first frame compared or not
            this.bufferedImageVector.remove(0);
            index++;
            
            LogUtil.put(new Log(
                "Frame: " + index + " Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
            
            this.setRunning(false);
            
            LogUtil.put(new Log("End", this, "run"));
        }
        catch (Exception e)
        {
            LogUtil.put(new Log("Exception", this, "run", e));
        }
    }
}
