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

import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.image.cache.BufferedImageFrameCacheable;
import org.allbinary.time.TimeDelayHelper;

public class ScreenCaptureImagesWorker extends BasicEventHandler
        implements CaptureWorkerInterface {

    private static long index;
    private boolean running;
    private ScreenScavangerRobot screenScavangerRobot;

    public ScreenCaptureImagesWorker() throws Exception {
        screenScavangerRobot = new ScreenScavangerRobot();
        index = ProcessingFrameIndexFactory.next();
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public void setThread(Thread thread) throws Exception {

    }

    public void run() {
        try {
            LogUtil.put(LogFactory.getInstance("Start", this, "run"));
            setRunning(true);
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            while (isRunning()) {
                timeHelper.setStartTime();
                BufferedImage bufferedImage
                        = screenScavangerRobot.getScreenAsBufferedImages()[0];
                Long frame = new Long(index);
                index++;
                CapturedBufferedImagesCacheSingleton.getInstance().add(
                        new BufferedImageFrameCacheable(bufferedImage, frame));
                CapturedImageWorkerResultsEvent capturedImageEvent
                        = new CapturedImageWorkerResultsEvent(this, frame,
                                bufferedImage);
                fireEvent(capturedImageEvent);
                LogUtil.put(LogFactory.getInstance("Time Elapsed: " + timeHelper.getElapsed(),
                        this, "run"));
                setRunning(false);
            }
            LogUtil.put(LogFactory.getInstance("End", this, "run"));
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance("Exception", this, "run", e));
        }
    }
}
