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
package org.allbinary.media.image.comparison.motion;

import java.util.Vector;

import java.awt.image.BufferedImage;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.basic.util.event.AllBinaryEventObject;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandlerAbstract;
import org.allbinary.media.image.comparison.ImageComparisonResult;
import org.allbinary.media.image.comparison.ImageComparisonResultsEvent;
import org.allbinary.media.image.comparison.ImageComparisonResultsListener;

import org.allbinary.time.TimeHelper;

public class MotionRectanglesWorker
    extends BasicEventHandlerAbstract
    implements ImageComparisonResultsListener
{
    private long index;
    
    private boolean running;
    
    private Vector imageComparisonInfoVector;
    
    private MotionRectangleConstraintsInterface motionRectangleConstraintsInterface;
    
    public MotionRectanglesWorker(
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
    {
        this.imageComparisonInfoVector = new Vector();
        
        this.motionRectangleConstraintsInterface =
            motionRectangleConstraintsInterface;
    }
    
    public void onImageComparisonResultsEvent(
        ImageComparisonResultsEvent imageComparisonResultsEvent)
    {
        this.imageComparisonInfoVector.add(
            imageComparisonResultsEvent);
        
        this.run();
    }
    
    public void onEvent(AllBinaryEventObject allBinaryEventObject)
    {
        this.onImageComparisonResultsEvent(
            (ImageComparisonResultsEvent) allBinaryEventObject);
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
            LogUtil.put(new Log("Start", this, "run"));
            
            this.setRunning(true);
            
            TimeHelper timeHelper = new TimeHelper(1000);
            
            timeHelper.setStartTime();
            
            ImageComparisonResultsEvent imageComparisonResultsEvent =
                (ImageComparisonResultsEvent)
                this.imageComparisonInfoVector.get(0);
            
            ImageComparisonResult imageComparisonInfo = (ImageComparisonResult) 
               imageComparisonResultsEvent.getImageComparisonResult();

            LogUtil.put(new Log(
                imageComparisonInfo.toString(), this, "run"));
            
            BufferedImage latestBufferedImage =
                imageComparisonInfo.getBufferedImages()[1];
            
            AllMotionRectangles allMotionRectangles =
                new AllMotionRectangles(imageComparisonInfo);

            AllMotionRectanglesResultsCacheSingleton.getInstance().add(
                new MotionRectanglesResultsFrameCacheable(
                allMotionRectangles, imageComparisonInfo.getFrameTwo()));
            
            ConsolidateMotionRectangles consolidatedMotionRectangles =
                new ConsolidateMotionRectangles(allMotionRectangles);

            ConsolidatedMotionRectanglesResultsCacheSingleton.getInstance().add(
                new MotionRectanglesResultsFrameCacheable(
                consolidatedMotionRectangles,
                imageComparisonInfo.getFrameTwo()));
            
            ConstrainedMotionRectangles constrainedMotionRectangles =
                new ConstrainedMotionRectangles(
                   this.motionRectangleConstraintsInterface,
                   consolidatedMotionRectangles);

            constrainedMotionRectangles.applyMotionRectangleConstraints(
                consolidatedMotionRectangles);
            
            ConstrainedMotionRectanglesResultsCacheSingleton.getInstance().add(
                new MotionRectanglesResultsFrameCacheable(
                constrainedMotionRectangles,
                imageComparisonInfo.getFrameTwo()));
                     
            this.fireEvent(new MotionRectanglesResultsEvent(this, 
                imageComparisonInfo.getFrameTwo(),
                (MotionRectangles) constrainedMotionRectangles));
            
            this.imageComparisonInfoVector.remove(imageComparisonResultsEvent);
            
            this.index++;
            
            LogUtil.put(new Log(
                "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
            
            this.setRunning(false);
            
            LogUtil.put(new Log("End", this, "run"));
        }
        catch (Exception e)
        {
            LogUtil.put(new Log("Exception", this, "run", e));
        }
    }
}