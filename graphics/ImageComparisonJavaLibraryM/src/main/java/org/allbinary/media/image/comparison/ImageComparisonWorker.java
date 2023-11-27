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
package org.allbinary.media.image.comparison;

import java.util.Vector;
import org.allbinary.input.media.image.capture.CapturedImageWorkerResultsEvent;
import org.allbinary.input.media.image.capture.CapturedImageWorkerResultsListener;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class ImageComparisonWorker
    extends BasicEventHandler
    implements CapturedImageWorkerResultsListener
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Vector bufferedImageVector = new Vector();
    
    private final ImageComparatorConstraintsInterface imageComparatorConstraintsInterface;
    
    private final ImageComparator imageComparator;
    
    private boolean running;
    
    private int index;
    
    public ImageComparisonWorker(
        final ImageComparatorConstraintsInterface imageComparatorConstraintsInterface)
        throws Exception
    {   
        this.imageComparatorConstraintsInterface =
            imageComparatorConstraintsInterface;
        
        this.imageComparator =new ImageComparator(imageComparatorConstraintsInterface);
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
        final CapturedImageWorkerResultsEvent capturedImageWorkerResultsEvent)
    {
        this.bufferedImageVector.add(capturedImageWorkerResultsEvent);
        
        if(this.bufferedImageVector.size() > 1)
        {
            this.run();
        }
    }
    
    public synchronized void onEvent(final AllBinaryEventObject allBinaryEventObject)
    {
        this.onCaptureEvent((CapturedImageWorkerResultsEvent) allBinaryEventObject);
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.RUN));
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            timeHelper.setStartTime();
            
            if(this.imageComparatorConstraintsInterface.isFrameAllowed(index))
            {
                final CapturedImageWorkerResultsEvent[] capturedImageWorkerResultsEvent =
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
                    final ImageComparisonResult imageComparisonResult =
                        this.imageComparator.compare(
                        capturedImageWorkerResultsEvent[0].getBufferedImage(),
                        capturedImageWorkerResultsEvent[1].getBufferedImage(), 
                        capturedImageWorkerResultsEvent[0].getFrame(),
                        capturedImageWorkerResultsEvent[1].getFrame(), 0);
                    
                    final Long frame = capturedImageWorkerResultsEvent[1].getFrame();
                    
                    final ImageComparisonResultFrameCacheable imageComparisonResultFrameCacheable =
                        new ImageComparisonResultFrameCacheable(
                        imageComparisonResult, frame);
                    
                    ImageComparisonResultCacheSingleton.getInstance().add(
                        imageComparisonResultFrameCacheable);
                    
                    this.fireEvent(new ImageComparisonResultsEvent(
                        this, imageComparisonResult));
                    
                    LogUtil.put(LogFactory.getInstance("Image Comparison Result: " +
                        imageComparisonResult.toString() + " for frame: " + frame, this, this.commonStrings.RUN));
                }
                else
                {
                    LogUtil.put(LogFactory.getInstance(
                        "An Image Was Not Valid: Image Worker Event Processing terminated", this, this.commonStrings.RUN));
                }
            }
            
            //Remove the first frame compared or not
            this.bufferedImageVector.remove(0);
            index++;
            
            LogUtil.put(LogFactory.getInstance(
                "Frame: " + index + " Time Elapsed: " + timeHelper.getElapsed(), this, this.commonStrings.RUN));
            
            this.setRunning(false);
            
            LogUtil.put(LogFactory.getInstance(this.commonStrings.END, this, this.commonStrings.RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, this.commonStrings.RUN, e));
        }
    }
}
