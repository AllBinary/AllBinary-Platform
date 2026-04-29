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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.media.image.comparison.ImageComparisonResult;
import org.allbinary.media.image.comparison.ImageComparisonResultsEvent;
import org.allbinary.media.image.comparison.ImageComparisonResultsListener;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class MotionRectanglesWorker
    extends BasicEventHandler
    implements ImageComparisonResultsListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Vector imageComparisonInfoVector = new Vector();
    
    private final MotionRectangleConstraintsInterface motionRectangleConstraintsInterface;
    
    private long index;
    
    private boolean running;
    
    public MotionRectanglesWorker(final MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
    {   
        this.motionRectangleConstraintsInterface = motionRectangleConstraintsInterface;
    }
    
    public void onImageComparisonResultsEvent(final ImageComparisonResultsEvent imageComparisonResultsEvent)
    {
        this.imageComparisonInfoVector.add(imageComparisonResultsEvent);
        
        this.run();
    }
    
    public void onEvent(final AllBinaryEventObject allBinaryEventObject)
    {
        this.onImageComparisonResultsEvent((ImageComparisonResultsEvent) allBinaryEventObject);
    }
    
    public synchronized boolean isRunning()
    {
        return this.running;
    }
    
    public synchronized void setRunning(final boolean running)
    {
        this.running = running;
    }
        
    public void run()
    {
        try
        {            
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.RUN);
            
            this.setRunning(true);
            
            final TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            timeHelper.setStartTimeTNT();
            
            final ImageComparisonResultsEvent imageComparisonResultsEvent =
                (ImageComparisonResultsEvent)
                this.imageComparisonInfoVector.get(0);
            
            final ImageComparisonResult imageComparisonInfo = (ImageComparisonResult) 
               imageComparisonResultsEvent.getImageComparisonResult();

            this.logUtil.putF(imageComparisonInfo.toString(), this, this.commonStrings.RUN);
            
            //final BufferedImage latestBufferedImage = imageComparisonInfo.getBufferedImages()[1];
            
            final AllMotionRectangles allMotionRectangles =
                new AllMotionRectangles(imageComparisonInfo);

            AllMotionRectanglesResultsCacheSingleton.getInstance().add(
                new MotionRectanglesResultsFrameCacheable(
                allMotionRectangles, imageComparisonInfo.getFrameTwo()));
            
            final ConsolidateMotionRectangles consolidatedMotionRectangles =
                new ConsolidateMotionRectangles(allMotionRectangles);

            ConsolidatedMotionRectanglesResultsCacheSingleton.getInstance().add(
                new MotionRectanglesResultsFrameCacheable(
                consolidatedMotionRectangles,
                imageComparisonInfo.getFrameTwo()));
            
            final ConstrainedMotionRectangles constrainedMotionRectangles =
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
            
            this.logUtil.putF(CommonLabels.getInstance().ELAPSED + timeHelper.getElapsedTNT(), this, this.commonStrings.RUN);
            
            this.setRunning(false);
            
            this.logUtil.putF(this.commonStrings.END, this, this.commonStrings.RUN);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }
    }
}