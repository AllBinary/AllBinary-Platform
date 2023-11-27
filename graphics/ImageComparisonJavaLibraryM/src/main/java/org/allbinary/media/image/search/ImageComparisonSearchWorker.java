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
package org.allbinary.media.image.search;

import java.awt.image.BufferedImage;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.media.image.comparison.ImageComparisonResult;
import org.allbinary.media.image.comparison.ImageComparisonResultsEvent;
import org.allbinary.media.image.comparison.ImageComparisonResultsListener;
import org.allbinary.time.TimeDelayHelper;

public class ImageComparisonSearchWorker
    extends BasicEventHandler
    implements ImageComparisonResultsListener
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private long index;
    
    private boolean running;
    
    private Vector imageComparisonInfoVector;
    
    private ImageComparisonSearchConstraintsInterface imageSearchConstraintsInterface;

    public ImageComparisonSearchWorker(
        ImageComparisonSearchConstraintsInterface imageSearchConstraintsInterface)
    {
        this.imageComparisonInfoVector = new Vector();
        
        this.imageSearchConstraintsInterface =
            imageSearchConstraintsInterface;
    }
    
    public void onImageComparisonResultsEvent(
        ImageComparisonResultsEvent imageComparisonResultsEvent)
    {
        this.imageComparisonInfoVector.add(
            imageComparisonResultsEvent.getImageComparisonResult());
        
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
            
            ImageComparisonResult imageComparisonInfo =
                (ImageComparisonResult) this.imageComparisonInfoVector.get(0);
            
            LogUtil.put(LogFactory.getInstance(imageComparisonInfo.toString(), this, "run"));
            
            BufferedImage latestBufferedImage =
                imageComparisonInfo.getBufferedImages()[1];
            
            /*
            ImageSearch imageSearch =
                new ImageSearch(
                    this.imageSearchConstraintsInterface,
                    imageComparisonInfo);
            */
            
            //this.fireEvent(new ImageComparisonSearchResultsEvent(this, imageSearch));
            
            this.imageComparisonInfoVector.remove(imageComparisonInfo);
            
            this.index++;
            
            LogUtil.put(LogFactory.getInstance(
                "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
            
            this.setRunning(false);
            
            LogUtil.put(LogFactory.getInstance(this.commonStrings.END, this, "run"));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "run", e));
        }
    }
}