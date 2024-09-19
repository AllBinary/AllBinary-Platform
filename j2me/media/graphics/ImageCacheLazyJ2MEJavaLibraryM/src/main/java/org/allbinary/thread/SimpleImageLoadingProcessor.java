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

import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;

/**
 *
 * @author User
 */
public class SimpleImageLoadingProcessor extends BaseImageLoadingProcessor {
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final ImageCache imageCache;
    
    public SimpleImageLoadingProcessor(final ImageCache imageCache) {
        this.imageCache = imageCache;
    }
    
    public void runTask() {

        try {
            this.imageCache.loadImageForAnimation();
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }

    }
    
}
