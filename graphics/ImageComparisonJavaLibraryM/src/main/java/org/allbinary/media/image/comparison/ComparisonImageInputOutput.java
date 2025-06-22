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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.input.automation.ImageOutputData;
import org.allbinary.media.image.cache.BufferedImageCacheable;
import org.allbinary.media.image.io.ImageIOInterface;
import java.awt.image.BufferedImage;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.visual.media.MediaDataFactory;
import org.allbinary.media.image.ImagePersistanceUtil;

public class ComparisonImageInputOutput implements ImageIOInterface
{
    
    private final static String ROOT_NAME = "_Changed_";
    
    public ComparisonImageInputOutput()
    {
    }
    
    public void save(final Long frame) throws Exception
    {
        if(frame > 0)
        {
            final ImageComparisonResultFrameCacheable imageComparisonResultFrameCacheable =
                (ImageComparisonResultFrameCacheable)
                ((AutomaticCacheInterface) ImageComparisonResultCacheSingleton.getInstance()).get(frame);
            
            if(imageComparisonResultFrameCacheable != null)
            {
                final ImageComparisonResult imageComparisonResult =
                    imageComparisonResultFrameCacheable.getImageComparisonResult();
                
                this.save(imageComparisonResult,
                    imageComparisonResultFrameCacheable.getFrame());
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("Comparison Results Not Available for Output: " +
                    frame, this, "save"));
            }
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("No Comparison Results: for first frame: " +
                frame, this, "save"));
        }
    }
    
    public void save(final ImageComparisonResult imageComparisonResult, final Long frame)
    throws Exception
    {
        final StringBuffer filePathStringBuffer = new StringBuffer();
        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append(ROOT_NAME);
        
        final StringBuffer filePathStringBuffer1 = new StringBuffer();
        filePathStringBuffer1.append(filePathStringBuffer.toString());
        filePathStringBuffer1.append("_1");
        filePathStringBuffer1.append(MediaDataFactory.getInstance().JPG.getExtension());
        String filePath1 = filePathStringBuffer1.toString();
        
        final StringBuffer filePathStringBuffer2 = new StringBuffer();
        filePathStringBuffer2.append(filePathStringBuffer.toString());
        filePathStringBuffer2.append("_2");
        filePathStringBuffer2.append(MediaDataFactory.getInstance().JPG.getExtension());
        String filePath2 = filePathStringBuffer2.toString();
        
        final BufferedImageCacheable[] bufferedImageCacheables =
            ChangedPixelsUtil.generateBufferedImageChacheables(
            imageComparisonResult);
        
        LogUtil.put(LogFactory.getInstance("Comparison Image File Path 1: " + filePath1, this, "save"));
        LogUtil.put(LogFactory.getInstance("Comparison Image File Path 2: " + filePath2, this, "save"));
        
        final BufferedImage[] bufferedImageArray = new BufferedImage[2];
        
        bufferedImageArray[0] = bufferedImageCacheables[0].getBufferedImage();
        bufferedImageArray[1] = bufferedImageCacheables[1].getBufferedImage();
        
        final ImagePersistanceUtil imagePersistanceUtil = ImagePersistanceUtil.getInstance();
        imagePersistanceUtil.saveWithImageIO(filePath1, bufferedImageArray[0]);
        imagePersistanceUtil.saveWithImageIO(filePath2, bufferedImageArray[1]);
    }
    
}
