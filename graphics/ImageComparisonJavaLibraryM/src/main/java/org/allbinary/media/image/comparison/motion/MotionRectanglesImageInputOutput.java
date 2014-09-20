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

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.logic.visual.media.MediaData;
import org.allbinary.input.automation.ImageOutputData;
import org.allbinary.media.image.cache.BufferedImageCacheable;
import org.allbinary.media.image.cache.BufferedImageInfo;
import org.allbinary.media.image.cache.BufferedImageInfoFactory;
import org.allbinary.media.image.cache.BufferedImagePoolSingleton;
import org.allbinary.media.image.io.ImageIOInterface;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.media.image.comparison.ImageComparisonResult;
import com.abcs.logic.util.cache.PoolInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class MotionRectanglesImageInputOutput implements ImageIOInterface
{
    private final int NUMBER_OF_IMAGES = 1; //1,2, or 3
    
    public MotionRectanglesImageInputOutput()
    {
    }
    
    public void save(Long frame) throws Exception
    {
        MotionRectanglesResultsFrameCacheable motionRectanglesResultsFrameCacheable =
            (MotionRectanglesResultsFrameCacheable)
            AllMotionRectanglesResultsCacheSingleton.getInstance().get(frame);
        
        if(motionRectanglesResultsFrameCacheable != null)
        {
            MotionRectangles motionRectangles =
                motionRectanglesResultsFrameCacheable.getMotionRectangles();
            
            this.save(motionRectangles, frame);
            
            motionRectanglesResultsFrameCacheable =
                (MotionRectanglesResultsFrameCacheable)
                ConsolidatedMotionRectanglesResultsCacheSingleton.getInstance().get(frame);
            
            motionRectangles =
                motionRectanglesResultsFrameCacheable.getMotionRectangles();
            
            this.save(motionRectangles, frame);
            
            motionRectanglesResultsFrameCacheable =
                (MotionRectanglesResultsFrameCacheable)
                ConstrainedMotionRectanglesResultsCacheSingleton.getInstance().get(frame);
            
            motionRectangles =
                motionRectanglesResultsFrameCacheable.getMotionRectangles();
            
            this.save(motionRectangles, frame);
        }
        else
        {
            LogUtil.put(new Log("No Motion Rectangle Results for Frame: " + frame, this, "save"));
        }
    }
    
    public void save(MotionRectangles motionRectangles, Long frame)
    throws Exception
    {
        StringBuffer filePathStringBuffer = new StringBuffer();
        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append("_");
        filePathStringBuffer.append(motionRectangles.getName());
        
        StringBuffer filePathStringBuffer1 = new StringBuffer();
        filePathStringBuffer1.append(filePathStringBuffer.toString());
        filePathStringBuffer1.append("_1");
        filePathStringBuffer1.append(MediaData.JPG.getExtension());
        
        StringBuffer filePathStringBuffer2 = new StringBuffer();
        filePathStringBuffer2.append(filePathStringBuffer.toString());
        filePathStringBuffer2.append("_2");
        filePathStringBuffer2.append(MediaData.JPG.getExtension());
        
        StringBuffer filePathStringBuffer3 = new StringBuffer();
        filePathStringBuffer3.append(filePathStringBuffer.toString());
        filePathStringBuffer3.append(MediaData.JPG.getExtension());
        
        LogUtil.put(new Log("Motion Image File Path 1: " + filePathStringBuffer1.toString(), this, "save"));
        LogUtil.put(new Log("Motion Image File Path 2: " + filePathStringBuffer2.toString(), this, "save"));
        LogUtil.put(new Log("Motion Image File Path 2: " + filePathStringBuffer3.toString(), this, "save"));
        
        BufferedImageCacheable bufferedImageCacheables[] =
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
            poolInterface.get(bufferedImageInfo);
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
        
        Graphics bufferedImageGraphicsArray[];
        bufferedImageGraphicsArray = new Graphics[NUMBER_OF_IMAGES];
        
        for(int index = 0; index < NUMBER_OF_IMAGES; index++)
        {
            bufferedImageGraphicsArray[index] =
                bufferedImageCacheables[index].getBufferedImage().getGraphics();
        }
        
        Iterator iterator = motionRectangles.getVector().iterator();
        while(iterator.hasNext())
        {
            MotionRectangle motionRectangle =
                (MotionRectangle) iterator.next();
            
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
            LogUtil.put(new Log("TreasureRectangles: " + rectangle.toString(), this, "Constructor"));
             */
        }
        
        if(NUMBER_OF_IMAGES > 1)
        {
            ImageUtil.save(filePathStringBuffer1.toString(),
                bufferedImageCacheables[1].getBufferedImage());
        }
        
        //0 frame is the second image
        ImageUtil.save(filePathStringBuffer2.toString(),
            bufferedImageCacheables[0].getBufferedImage());
        
        if(NUMBER_OF_IMAGES > 2)
        {
            ImageUtil.save(filePathStringBuffer3.toString(),
                bufferedImageCacheables[2].getBufferedImage());
        }
    }
}
