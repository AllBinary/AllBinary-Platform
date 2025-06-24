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

import org.allbinary.media.image.cache.BufferedImageCacheable;
import org.allbinary.media.image.cache.BufferedImageInfo;
import org.allbinary.media.image.cache.BufferedImageInfoFactory;
import org.allbinary.media.image.cache.BufferedImagePoolSingleton;
import java.awt.image.BufferedImage;
import java.util.Vector;



import org.allbinary.media.image.comparison.pixel.PixelDelta;
import org.allbinary.logic.util.cache.PoolInterface;

public class ChangedPixelsUtil
{
    private final static int START_X  = 0;
    private final static int START_Y  = 0;
    private final static int OFFSET  = 0;
    private final static int SCAN_SIZE  = 0;
    
    private final static int[] CLEAR_INT_ARRAY = new int[1800*1600];
    
   /*
   static
   {
       for(int index = 0; index < CLEAR_BYTE_ARRAY.length; index++)
       {
           CLEAR_BYTE_ARRAY[in]
       }
   }
    */
    
    private ChangedPixelsUtil()
    {
        
    }
    
    //GenerateImageWithBoundingBoxesAroundMovingArtifacts
    public static BufferedImageCacheable[] 
        generateBufferedImageChacheables(
        ImageComparisonResult imageComparisonInfo)
        throws Exception
    {
        BufferedImageCacheable[] bufferedImageCacheables;
        
        BufferedImage originalBufferedImage =
            imageComparisonInfo.getBufferedImages()[0];
        
        bufferedImageCacheables = new BufferedImageCacheable[2];
        
        BufferedImageInfo bufferedImageInfo =
            BufferedImageInfoFactory.getInstance(
            imageComparisonInfo.imageWidth,
            imageComparisonInfo.imageHeight,
            originalBufferedImage.getType());
        
        PoolInterface poolInterface = BufferedImagePoolSingleton.getInstance();
        
        for(int index = 0; index < bufferedImageCacheables.length; index++)
        {
            BufferedImageCacheable bufferedImageCacheable =
                (BufferedImageCacheable) poolInterface.remove(bufferedImageInfo);
            
            bufferedImageCacheables[index] = bufferedImageCacheable;
            //clear the image
            bufferedImageCacheables[index].getBufferedImage().setRGB(START_X, START_Y,
                bufferedImageInfo.getWidth(), bufferedImageInfo.getHeight(),
                CLEAR_INT_ARRAY, OFFSET, SCAN_SIZE);
        }
        
        final Vector vector = imageComparisonInfo.getNonMatchingPixelVector();
        final int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            PixelDelta pixelDelta = (PixelDelta) vector.get(index);
            
            if(pixelDelta.getColorDelta() == null)
            {
                System.out.print("ColorDelta");
                System.exit(0);
            }
            
            bufferedImageCacheables[0].getBufferedImage().setRGB(
                pixelDelta.getPoint().getX(), pixelDelta.getPoint().getY(), pixelDelta.getColorDelta().getRgb1());
            
            bufferedImageCacheables[1].getBufferedImage().setRGB(
                pixelDelta.getPoint().getX(), pixelDelta.getPoint().getY(), pixelDelta.getColorDelta().getRgb2());
        }
        return bufferedImageCacheables;
    }
}
