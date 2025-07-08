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
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class SaveImageComparisonWorker
    implements ImageComparisonResultsListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private boolean running;
    
    private final Vector imageComparisonInfoVector = new Vector();
    
    public SaveImageComparisonWorker()
    {
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
        return running;
    }

    public synchronized void setRunning(final boolean running)
    {
        this.running = running;
    }
    
    public void run()
    {
        try
        {
            logUtil.put(this.commonStrings.START, this, this.commonStrings.RUN);
            
            this.setRunning(true);
            
            final TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            timeHelper.setStartTime();
            
            final ImageComparisonResultsEvent imageComparisonResultsEvent = 
                    (ImageComparisonResultsEvent) this.imageComparisonInfoVector.get(0);
            
            final ImageComparisonResult imageComparisonInfo =
                imageComparisonResultsEvent.getImageComparisonResult();
            
            logUtil.put(imageComparisonInfo.toString(), this, this.commonStrings.RUN);

            new ComparisonImageInputOutput().save(imageComparisonInfo, imageComparisonInfo.getFrameTwo());

            this.imageComparisonInfoVector.remove(imageComparisonInfo);

            logUtil.put(CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(), this, this.commonStrings.RUN);
            
            logUtil.put(this.commonStrings.END, this, this.commonStrings.RUN);
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }
    }
}