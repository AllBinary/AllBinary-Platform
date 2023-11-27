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

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class SaveImageComparisonWorker
    implements ImageComparisonResultsListener
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "run"));
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            timeHelper.setStartTime();
            
            ImageComparisonResultsEvent imageComparisonResultsEvent = 
            (ImageComparisonResultsEvent) this.imageComparisonInfoVector.get(0);
            
            ImageComparisonResult imageComparisonInfo =
                imageComparisonResultsEvent.getImageComparisonResult();
            
            LogUtil.put(LogFactory.getInstance(imageComparisonInfo.toString(), this, "run"));

            new ComparisonImageInputOutput().save(
                imageComparisonInfo, imageComparisonInfo.getFrameTwo());

            this.imageComparisonInfoVector.remove(imageComparisonInfo);

            LogUtil.put(LogFactory.getInstance(
                "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
            
            LogUtil.put(LogFactory.getInstance(this.commonStrings.END, this, "run"));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "run", e));
        }
    }
}