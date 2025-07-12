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

import java.awt.image.BufferedImage;

import org.allbinary.input.automation.ImageOutputData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.visual.media.MediaDataFactory;
import org.allbinary.media.image.ImagePersistanceUtil;
import org.allbinary.media.image.cache.BufferedImageCacheable;
import org.allbinary.media.image.io.ImageIOInterface;
import org.allbinary.string.CommonStrings;

public class ComparisonImageInputOutput implements ImageIOInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private final static String ROOT_NAME = "_Changed_";
    
    public ComparisonImageInputOutput()
    {
    }
    
    public void save(final Long frame) throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
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
                logUtil.put("Comparison Results Not Available for Output: " +
                    frame, this, commonStrings.SAVE);
            }
        }
        else
        {
            logUtil.put("No Comparison Results: for first frame: " +
                frame, this, commonStrings.SAVE);
        }
    }
    
    public void save(final ImageComparisonResult imageComparisonResult, final Long frame)
    throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final StringMaker filePathStringBuffer = new StringMaker();
        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append(ROOT_NAME);
        
        final StringMaker filePathStringBuffer1 = new StringMaker();
        filePathStringBuffer1.append(filePathStringBuffer.toString());
        filePathStringBuffer1.append("_1");
        filePathStringBuffer1.append(MediaDataFactory.getInstance().JPG.getExtension());
        String filePath1 = filePathStringBuffer1.toString();
        
        final StringMaker filePathStringBuffer2 = new StringMaker();
        filePathStringBuffer2.append(filePathStringBuffer.toString());
        filePathStringBuffer2.append("_2");
        filePathStringBuffer2.append(MediaDataFactory.getInstance().JPG.getExtension());
        String filePath2 = filePathStringBuffer2.toString();
        
        final BufferedImageCacheable[] bufferedImageCacheables =
            ChangedPixelsUtil.generateBufferedImageChacheables(
            imageComparisonResult);
        
        logUtil.put("Comparison Image File Path 1: " + filePath1, this, commonStrings.SAVE);
        logUtil.put("Comparison Image File Path 2: " + filePath2, this, commonStrings.SAVE);
        
        final BufferedImage[] bufferedImageArray = new BufferedImage[2];
        
        bufferedImageArray[0] = bufferedImageCacheables[0].getBufferedImage();
        bufferedImageArray[1] = bufferedImageCacheables[1].getBufferedImage();
        
        final ImagePersistanceUtil imagePersistanceUtil = ImagePersistanceUtil.getInstance();
        imagePersistanceUtil.saveWithImageIO(filePath1, bufferedImageArray[0]);
        imagePersistanceUtil.saveWithImageIO(filePath2, bufferedImageArray[1]);
    }
    
}
