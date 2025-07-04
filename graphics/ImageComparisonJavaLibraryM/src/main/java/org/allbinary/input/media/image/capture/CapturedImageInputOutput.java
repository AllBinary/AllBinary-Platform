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

import org.allbinary.input.automation.ImageOutputData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.visual.media.MediaDataFactory;
import org.allbinary.media.image.ImagePersistanceUtil;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.media.image.cache.BufferedImageFrameCacheable;
import org.allbinary.media.image.io.ImageIOInterface;
import org.allbinary.string.CommonStrings;

public class CapturedImageInputOutput implements ImageIOInterface {

    public void save(Long frame) throws Exception {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        final BufferedImageFrameCacheable capturedBufferedImageCacheable
                = ((BufferedImageFrameCacheable) (Object) ((AutomaticCacheInterface) CapturedBufferedImagesCacheSingleton.getInstance()).get(frame));
        LogUtil.put(LogFactory.getInstance(("Saving: "
                + capturedBufferedImageCacheable.toString()),
                this, commonStrings.SAVE));
        save(capturedBufferedImageCacheable.getBufferedImage(), frame);
    }

    public void save(BufferedImage bufferedImage, Long frame) {
        
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        final ImageUtil imageUtil = ImageUtil.getInstance();
        final StringBuffer filePathStringBuffer = new StringBuffer();
        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append(MediaDataFactory.getInstance().JPG.getExtension());
        String filePath = filePathStringBuffer.toString();
        LogUtil.put(LogFactory.getInstance(("Image File Path: " + filePath
                + imageUtil.toString(bufferedImage)),
                this, commonStrings.SAVE));
        final ImagePersistanceUtil imagePersistanceUtil = ImagePersistanceUtil.getInstance();
        imagePersistanceUtil.saveWithImageIO(filePath, bufferedImage);
    }
}
