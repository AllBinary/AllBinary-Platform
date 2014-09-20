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

import java.io.File;
import java.util.Vector;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

import org.allbinary.time.TimeHelper;

public class SaveImageComparisonWorker
    implements ImageComparisonResultsListener
{
    private boolean running;
    
    private Vector imageComparisonInfoVector;
    
    public SaveImageComparisonWorker()
    {
        this.imageComparisonInfoVector = new Vector();
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
            (ImageComparisonResultsEvent) this.imageComparisonInfoVector.get(0);
            
            ImageComparisonResult imageComparisonInfo =
                imageComparisonResultsEvent.getImageComparisonResult();
            
            LogUtil.put(new Log(imageComparisonInfo.toString(), this, "run"));

            new ComparisonImageInputOutput().save(
                imageComparisonInfo, imageComparisonInfo.getFrameTwo());

            this.imageComparisonInfoVector.remove(imageComparisonInfo);

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