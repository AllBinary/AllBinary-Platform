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

import org.allbinary.graphics.color.ColorCacheFactory;
import org.allbinary.graphics.color.ColorCacheable;
import java.awt.image.BufferedImage;
import org.allbinary.media.image.comparison.pixel.PixelDeltaFactory;
import java.awt.Color;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;

public class ImageComparator
{

   private final ImageComparatorConstraintsInterface imageComparatorConstraintsInterface;

   public ImageComparator(final ImageComparatorConstraintsInterface imageComparatorConstraintsInterface)
   {
      this.imageComparatorConstraintsInterface = imageComparatorConstraintsInterface;
   }

   private void process(final ImageComparisonResult imageComparisonInfo, final GPoint point) throws Exception
   {
      final boolean isCollsionWithAvoidRectangles = this.imageComparatorConstraintsInterface.isCollisionWithAvoidRectangles(point);

      /*
      if(isCollsionWithAvoidRectangles)
      {
      logUtil.put("Pixel Collided with 1 or more avoid Rectangle(s): " +
      point, this, "compare");
      }
       */

      if (!isCollsionWithAvoidRectangles)
      {

         final int rgb1 = imageComparisonInfo.getBufferedImages()[0].getRGB(point.getX(), point.getY());
         // & 0xFF; // assuming grayscale, so r==g==b
         final int rgb2 = imageComparisonInfo.getBufferedImages()[1].getRGB(point.getX(), point.getY());
         // & 0xFF; // assuming grayscale, so r==g==b
         final AutomaticCacheInterface automaticCacheInterface = ColorCacheFactory.getInstance();

         final Integer colorInteger = Integer.valueOf(rgb1);
         final ColorCacheable colorCacheable = (ColorCacheable) automaticCacheInterface.get(colorInteger);
         final Color color = colorCacheable.getColor();

         final Integer colorInteger2 = Integer.valueOf(rgb2);
         final ColorCacheable colorCacheable2 = (ColorCacheable) automaticCacheInterface.get(colorInteger);
         final Color color2 = colorCacheable.getColor();

         if (this.imageComparatorConstraintsInterface.isColorAllowed(0, point, color) || 
               this.imageComparatorConstraintsInterface.isColorAllowed(1, point, color2))
         {
            if (Math.abs(rgb1 - rgb2) <= imageComparisonInfo.getTolerance())
            {
               imageComparisonInfo.pixelsThatMatch++;
            } else
            {
               imageComparisonInfo.add(PixelDeltaFactory.getInstance(point.getX(), point.getY(), rgb1, rgb2));
            }
         }
      } else
      {
         imageComparisonInfo.pixelsIgnored++;
      }
   }

   public ImageComparisonResult compare(final BufferedImage bufferedImage1, final BufferedImage bufferedImage2, final Long frameOne, final Long frameTwo, final int tolerance) throws Exception {
       return this.compare(bufferedImage1, bufferedImage2, frameOne, frameTwo, tolerance, StringUtil.getInstance().EMPTY_STRING);
   }
   
   public ImageComparisonResult compare(final BufferedImage bufferedImage1, final BufferedImage bufferedImage2, final Long frameOne, final Long frameTwo, final int tolerance, final String name) throws Exception
   {
      //logUtil.put(this.commonStrings.START, this, "compare");
      if (bufferedImage1 == null || bufferedImage2 == null)
      {
         throw new Exception("Input images must not be null.");
      }

      final ImageComparisonResult imageComparisonInfo = new ImageComparisonResult(name, bufferedImage1, bufferedImage2, frameOne, frameTwo, tolerance);

      for (int indexY = 0; indexY < imageComparisonInfo.imageHeight; indexY++)
      {
         for (int indexX = 0; indexX < imageComparisonInfo.imageWidth; indexX++)
         {
            this.process(imageComparisonInfo, PointFactory.getInstance().getInstance(indexX, indexY));

            if (imageComparisonInfo.getNonMatchingPixelVector().size() > this.imageComparatorConstraintsInterface.getMaxNonMatchingPixelDeltas())
            {
               break;
            }
         }
      }

      //logUtil.put(this.commonStrings.END, this, "compare");
      return imageComparisonInfo;
   }
}