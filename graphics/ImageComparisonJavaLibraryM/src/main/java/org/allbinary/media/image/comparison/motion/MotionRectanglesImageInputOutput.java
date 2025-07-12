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
package org.allbinary.media.image.comparison.motion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import org.allbinary.input.automation.ImageOutputData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.PoolInterface;
import org.allbinary.logic.visual.media.MediaDataFactory;
import org.allbinary.media.image.ImagePersistanceUtil;
import org.allbinary.media.image.cache.BufferedImageCacheable;
import org.allbinary.media.image.cache.BufferedImageInfo;
import org.allbinary.media.image.cache.BufferedImageInfoFactory;
import org.allbinary.media.image.cache.BufferedImagePoolSingleton;
import org.allbinary.media.image.comparison.ImageComparisonResult;
import org.allbinary.media.image.io.ImageIOInterface;
import org.allbinary.string.CommonStrings;

public class MotionRectanglesImageInputOutput implements ImageIOInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final int NUMBER_OF_IMAGES = 1; //1,2, or 3
    
    public MotionRectanglesImageInputOutput()
    {
    }
    
    public void save(Long frame) throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        MotionRectanglesResultsFrameCacheable motionRectanglesResultsFrameCacheable =
            (MotionRectanglesResultsFrameCacheable)
            ((AutomaticCacheInterface) AllMotionRectanglesResultsCacheSingleton.getInstance()).get(frame);
        
        if(motionRectanglesResultsFrameCacheable != null)
        {
            MotionRectangles motionRectangles =
                motionRectanglesResultsFrameCacheable.getMotionRectangles();
            
            this.save(motionRectangles, frame);
            
            motionRectanglesResultsFrameCacheable =
                (MotionRectanglesResultsFrameCacheable)
                ((AutomaticCacheInterface) ConsolidatedMotionRectanglesResultsCacheSingleton.getInstance()).get(frame);
            
            motionRectangles =
                motionRectanglesResultsFrameCacheable.getMotionRectangles();
            
            this.save(motionRectangles, frame);
            
            motionRectanglesResultsFrameCacheable =
                (MotionRectanglesResultsFrameCacheable)
                ((AutomaticCacheInterface) ConstrainedMotionRectanglesResultsCacheSingleton.getInstance()).get(frame);
            
            motionRectangles =
                motionRectanglesResultsFrameCacheable.getMotionRectangles();
            
            this.save(motionRectangles, frame);
        }
        else
        {
            logUtil.put("No Motion Rectangle Results for Frame: " + frame, this, commonStrings.SAVE);
        }
    }
    
    public void save(MotionRectangles motionRectangles, Long frame)
    throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        final StringMaker filePathStringBuffer = new StringMaker();
        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append("_");
        filePathStringBuffer.append(motionRectangles.getName());
        
        StringMaker filePathStringBuffer1 = new StringMaker();
        filePathStringBuffer1.append(filePathStringBuffer.toString());
        filePathStringBuffer1.append("_1");
        filePathStringBuffer1.append(MediaDataFactory.getInstance().JPG.getExtension());
        
        StringMaker filePathStringBuffer2 = new StringMaker();
        filePathStringBuffer2.append(filePathStringBuffer.toString());
        filePathStringBuffer2.append("_2");
        filePathStringBuffer2.append(MediaDataFactory.getInstance().JPG.getExtension());
        
        StringMaker filePathStringBuffer3 = new StringMaker();
        filePathStringBuffer3.append(filePathStringBuffer.toString());
        filePathStringBuffer3.append(MediaDataFactory.getInstance().JPG.getExtension());
        
        logUtil.put("Motion Image File Path 1: " + filePathStringBuffer1.toString(), this, commonStrings.SAVE);
        logUtil.put("Motion Image File Path 2: " + filePathStringBuffer2.toString(), this, commonStrings.SAVE);
        logUtil.put("Motion Image File Path 2: " + filePathStringBuffer3.toString(), this, commonStrings.SAVE);
        
        BufferedImageCacheable[] bufferedImageCacheables =
            new BufferedImageCacheable[NUMBER_OF_IMAGES];
        
        ImageComparisonResult imageComparisonInfo =
            motionRectangles.getImageComparisonResult();
        
        BufferedImage originalBufferedImage =
            imageComparisonInfo.getBufferedImages()[0];
        
        BufferedImageInfo bufferedImageInfo =
            BufferedImageInfoFactory.getInstance(
            imageComparisonInfo.imageWidth,
            imageComparisonInfo.imageHeight,
            originalBufferedImage.getType());
        
        PoolInterface poolInterface =
            BufferedImagePoolSingleton.getInstance();
        
        for(int index = 0; index < NUMBER_OF_IMAGES; index++)
        {
            bufferedImageCacheables[index] = (BufferedImageCacheable)
            poolInterface.remove(bufferedImageInfo);
        }
        
        bufferedImageCacheables[0].getBufferedImage().setData(
            imageComparisonInfo.getBufferedImages()[1].getData());
        
        if(NUMBER_OF_IMAGES > 1)
        {
            bufferedImageCacheables[1].getBufferedImage().setData(
                imageComparisonInfo.getBufferedImages()[1].getData());
        }
        
            /*
            for(int heightIndex = 0; heightIndex < imageComparisonInfo.imageHeight; heightIndex++)
            {
                for(int widthIndex = 0; widthIndex < imageComparisonInfo.imageWidth; widthIndex++)
                {
                    int rgb1 = imageComparisonInfo.getBufferedImages()[1].getRGB(widthIndex, heightIndex);// & 0xFF; // assuming grayscale, so r==g==b
             
                    bufferedImages[0].setRGB(widthIndex, heightIndex, rgb1);
             
                    if(NUMBER_OF_IMAGES > 1)
                    {
                       int rgb2 = imageComparisonInfo.getBufferedImages()[0].getRGB(widthIndex, heightIndex);// & 0xFF; // assuming grayscale, so r==g==b
                       bufferedImages[1].setRGB(widthIndex, heightIndex, rgb2);
                    }
                }
            }
             */
        
        Graphics[] bufferedImageGraphicsArray;
        bufferedImageGraphicsArray = new Graphics[NUMBER_OF_IMAGES];
        
        for(int index = 0; index < NUMBER_OF_IMAGES; index++)
        {
            bufferedImageGraphicsArray[index] =
                bufferedImageCacheables[index].getBufferedImage().getGraphics();
        }
        
        final Vector rectangelList = motionRectangles.getVector();
        final int size = rectangelList.size();
        for(int index2 = 0; index2 < size; index2++)
        {
            MotionRectangle motionRectangle =
                (MotionRectangle) rectangelList.get(index2);
            
            Rectangle rectangle = motionRectangle.getRectangle();
            
            for(int index = 0; index < NUMBER_OF_IMAGES; index++)
            {
                bufferedImageGraphicsArray[index].setColor(Color.ORANGE);
                bufferedImageGraphicsArray[index].drawRect(
                    rectangle.x, rectangle.y,
                    rectangle.width, rectangle.height);
            }

            /*
            //For test frame
            if(rectangle.x > 560 && rectangle.y > 330 && 
               rectangle.x < 600 && rectangle.y < 365)// && imageComparisonInfo.getFrameTwo() == 19)
            logUtil.put("TreasureRectangles: " + rectangle.toString(), this, this.commonStrings.CONSTRUCTOR);
             */
        }
        
        final ImagePersistanceUtil imagePersistanceUtil = ImagePersistanceUtil.getInstance();
        
        if(NUMBER_OF_IMAGES > 1)
        {
            imagePersistanceUtil.saveWithImageIO(
                    filePathStringBuffer1.toString(),
                    bufferedImageCacheables[1].getBufferedImage());
        }
        
        //0 frame is the second image
        imagePersistanceUtil.saveWithImageIO(
            filePathStringBuffer2.toString(),
            bufferedImageCacheables[0].getBufferedImage());
        
        if(NUMBER_OF_IMAGES > 2)
        {
            imagePersistanceUtil.saveWithImageIO(
                    filePathStringBuffer3.toString(),
                    bufferedImageCacheables[2].getBufferedImage());
        }
    }
}
