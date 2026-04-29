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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.media.image.cache.BufferedImageFrameCacheable;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class ScreenCaptureImagesWorker extends BasicEventHandler
        implements CaptureWorkerInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private static long index;
    private boolean running;
    private ScreenScavangerRobot screenScavangerRobot;

    public ScreenCaptureImagesWorker() throws Exception {
        screenScavangerRobot = new ScreenScavangerRobot();
        index = ProcessingFrameIndexFactory.next();
    }

    public synchronized boolean isRunning() {
        return this.running;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public void setThread(Thread thread) throws Exception {

    }

    public void run() {
        try {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.RUN);
            this.setRunning(true);
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            while (isRunning()) {
                timeHelper.setStartTimeTNT();
                BufferedImage bufferedImage
                        = this.screenScavangerRobot.getScreenAsBufferedImages()[0];
                Long frame = new Long(index);
                index++;
                CapturedBufferedImagesCacheSingleton.getInstance().add(
                        new BufferedImageFrameCacheable(bufferedImage, frame));
                CapturedImageWorkerResultsEvent capturedImageEvent
                        = new CapturedImageWorkerResultsEvent(this, frame,
                                bufferedImage);
                fireEvent(capturedImageEvent);
                this.logUtil.putF(CommonLabels.getInstance().ELAPSED + timeHelper.getElapsedTNT(), this, this.commonStrings.RUN);
                this.setRunning(false);
            }
            this.logUtil.putF(this.commonStrings.END, this, this.commonStrings.RUN);
        } catch (Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }
    }
}
