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
package org.allbinary.input.media.image.capture;

import java.awt.image.BufferedImage;
import java.util.Vector;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class SaveCapturedImageWorker extends BasicEventHandler
    implements CapturedImageWorkerResultsListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private boolean running;
    private Vector capturedImageWorkerResultsEventVector = new Vector();
    
    public SaveCapturedImageWorker() throws Exception {
    }
    
    public synchronized boolean isRunning() {
	return running;
    }
    
    public synchronized void setRunning(boolean running) {
	this.running = running;
    }
    
    public void onCaptureEvent
	(CapturedImageWorkerResultsEvent capturedImageEvent) {
	capturedImageWorkerResultsEventVector.add(capturedImageEvent);
	run();
    }
    
    public void onEvent(AllBinaryEventObject allBinaryEventObject) {
	onCaptureEvent((CapturedImageWorkerResultsEvent) allBinaryEventObject);
    }
    
    public void run() {
	try {
	    logUtil.put(this.commonStrings.START, this, this.commonStrings.RUN);
	    setRunning(true);
	    TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
	    timeHelper.setStartTime();
	    CapturedImageWorkerResultsEvent capturedImageWorkerResultsEvent
		= ((CapturedImageWorkerResultsEvent)
		   capturedImageWorkerResultsEventVector.get(0));
	    BufferedImage screenBufferedImage
		= capturedImageWorkerResultsEvent.getBufferedImage();
	    new CapturedImageInputOutput().save(screenBufferedImage,
						capturedImageWorkerResultsEvent
						    .getFrame());
	    capturedImageWorkerResultsEventVector
		.remove(capturedImageWorkerResultsEvent);
	    logUtil.put(CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(),
				this, this.commonStrings.RUN);
	    setRunning(false);
	    logUtil.put(this.commonStrings.END, this, this.commonStrings.RUN);
	} catch (Exception e) {
	    logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
	}
    }
}
