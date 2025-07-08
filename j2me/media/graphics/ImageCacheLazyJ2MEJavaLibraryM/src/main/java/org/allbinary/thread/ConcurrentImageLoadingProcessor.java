/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.thread;

import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class ConcurrentImageLoadingProcessor extends BaseImageLoadingProcessor {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final ImageCache imageCache;
    
    private final ABRunnable runnable = new ABRunnable() {

        public void run() {
            try {
                this.setRunning(true);
                //logUtil.put(commonStrings.START, this, commonStrings.RUN);

                imageCache.waitForLoadNow();
                
                //logUtil.put("found animation that has attempted to paint so load animations and images", this, commonStrings.RUN);
                
                imageCache.loadImages();
                imageCache.loadRemainingAnimations();
                
                this.setRunning(false);
                
                final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
                if(!progressCanvas.inProgress) {
                    progressCanvas.endFromInitialLazyLoadingComplete();
                }

//            logUtil.put(commonStrings.END, this, commonStrings.RUN);
            } catch (Exception e) {
                this.setRunning(false);
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }

    };
    
    public ConcurrentImageLoadingProcessor(final ImageCache imageCache) {
        this.imageCache = imageCache;
    }
    
    public void runTask() {
        if (!this.runnable.isRunning()) {
            ImageThreadPool.getInstance().runTask(this.runnable);
        }
    }
    
}
