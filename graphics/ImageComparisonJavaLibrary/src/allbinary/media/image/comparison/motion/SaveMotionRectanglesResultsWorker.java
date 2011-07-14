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
package allbinary.media.image.comparison.motion;

import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

import allbinary.time.TimeHelper;

public class SaveMotionRectanglesResultsWorker
    implements MotionRectanglesResultsListener
{
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
            LogUtil.put(new Log("Start", this, "run"));
            
            this.setRunning(true);
            
            TimeHelper timeHelper = new TimeHelper(1000);
            
            timeHelper.setStartTime();

            MotionRectanglesResultsEvent motionRectanglesResultsEvent = 
                (MotionRectanglesResultsEvent) 
                this.getMotionRectanglesVector().get(0);
            
            MotionRectangles motionRectangles = 
                motionRectanglesResultsEvent.getMotionRectangles();

            new MotionRectanglesImageInputOutput().save(motionRectangles, 
                motionRectanglesResultsEvent.getFrame());
            
            this.getMotionRectanglesVector().remove(motionRectangles);
            
            LogUtil.put(new Log(
                "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
            
            LogUtil.put(new Log("End", this, "run"));
        }
        catch (Exception e)
        {
            LogUtil.put(new Log("Exception", this, "run", e));
        }
    }
    
}
