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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class ConcurrentImageLoadingProcessor extends BaseImageLoadingProcessor {
    
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    class ImageCacheRunnable extends ABRunnable {

        private final ImageCache imageCache;
        
        public ImageCacheRunnable(final ImageCache imageCache) {
            this.imageCache = imageCache;
        }
        
        @Override
        public void run() {
            final LogUtil logUtil = LogUtil.getInstance();
            try {
                this.setRunning(true);
                //logUtil.putF(this.commonStrings.START, this, this.commonStrings.RUN);

                this.imageCache.waitForLoadNow();
                
                //logUtil.putF("found animation that has attempted to paint so load animations and images", this, this.commonStrings.RUN);
                
                this.imageCache.loadImages();
                this.imageCache.loadRemainingAnimations();
                
                this.setRunning(false);
                
                final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
                if(!progressCanvas.inProgress) {
                    progressCanvas.endFromInitialLazyLoadingComplete();
                }

//            logUtil.putF(this.commonStrings.END, this, this.commonStrings.RUN);
            } catch (Exception e) {
                this.setRunning(false);
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }
        
    };
    
    private final ABRunnable runnable;
    
    public ConcurrentImageLoadingProcessor(final ImageCache imageCache) {
        this.runnable = new ImageCacheRunnable(imageCache);
    }
    
    @Override
    public void runTask() {
        if (!this.runnable.isRunning()) {
            ImageThreadPool.getInstance().runTask(this.runnable);
        }
    }
    
}
