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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;

import org.allbinary.time.TimeDelayHelper;

public class SaveMotionRectanglesResultsWorker
    implements MotionRectanglesResultsListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private boolean running;
    
    private Vector motionRectanglesVector;
    
    public SaveMotionRectanglesResultsWorker()
    {
        this.motionRectanglesVector = new Vector();
    }

    public Vector getMotionRectanglesVector()
    {
        return this.motionRectanglesVector;
    }
    
    public void onMotionRectanglesImageComparisonResultsEvent(
        MotionRectanglesResultsEvent motionRectanglesResultsEvent)
    {
        this.getMotionRectanglesVector().add(
            motionRectanglesResultsEvent);
        
        this.run();
    }
    
    public void onEvent(AllBinaryEventObject allBinaryEventObject)
    {
        this.onMotionRectanglesImageComparisonResultsEvent(
            (MotionRectanglesResultsEvent)
            allBinaryEventObject);
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
            logUtil.put(this.commonStrings.START, this, this.commonStrings.RUN);
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            timeHelper.setStartTime();

            MotionRectanglesResultsEvent motionRectanglesResultsEvent = 
                (MotionRectanglesResultsEvent) 
                this.getMotionRectanglesVector().get(0);
            
            MotionRectangles motionRectangles = 
                motionRectanglesResultsEvent.getMotionRectangles();

            new MotionRectanglesImageInputOutput().save(motionRectangles, 
                motionRectanglesResultsEvent.getFrame());
            
            this.getMotionRectanglesVector().remove(motionRectangles);
            
            logUtil.put(
                CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(), this, this.commonStrings.RUN);
            
            logUtil.put(this.commonStrings.END, this, this.commonStrings.RUN);
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }
    }
    
}
