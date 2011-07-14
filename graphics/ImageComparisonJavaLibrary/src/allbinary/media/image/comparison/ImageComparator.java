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
package allbinary.media.image.comparison;

import allbinary.graphics.color.ColorCacheFactory;
import allbinary.graphics.color.ColorCacheable;
import java.awt.Point;
import java.awt.image.BufferedImage;
import allbinary.input.automation.PointFactory;
import allbinary.media.image.comparison.pixel.PixelDeltaFactory;
import com.abcs.logic.util.cache.AutomaticCacheInterface;
import java.awt.Color;

public class ImageComparator
{

   private ImageComparatorConstraintsInterface imageComparatorConstraintsInterface;

   public ImageComparator(ImageComparatorConstraintsInterface imageComparatorConstraintsInterface)
   {
      this.imageComparatorConstraintsInterface = imageComparatorConstraintsInterface;
   }

   private void process(ImageComparisonResult imageComparisonInfo, Point point) throws Exception
   {
      boolean isCollsionWithAvoidRectangles = this.imageComparatorConstraintsInterface.isCollisionWithAvoidRectangles(point);

      /*
      if(isCollsionWithAvoidRectangles)
      {
      LogUtil.put(new Log("Pixel Collided with 1 or more avoid Rectangle(s): " +
      point, this, "compare"));
      }
       */

      if (!isCollsionWithAvoidRectangles)
      {

         int rgb1 = imageComparisonInfo.getBufferedImages()[0].getRGB(point.x, point.y);
         // & 0xFF; // assuming grayscale, so r==g==b
         int rgb2 = imageComparisonInfo.getBufferedImages()[1].getRGB(point.x, point.y);
         // & 0xFF; // assuming grayscale, so r==g==b
         AutomaticCacheInterface automaticCacheInterface = ColorCacheFactory.getInstance();

         Integer colorInteger = Integer.valueOf(rgb1);
         ColorCacheable colorCacheable = (ColorCacheable) automaticCacheInterface.get(colorInteger);
         Color color = colorCacheable.getColor();

         Integer colorInteger2 = Integer.valueOf(rgb2);
         ColorCacheable colorCacheable2 = (ColorCacheable) automaticCacheInterface.get(colorInteger);
         Color color2 = colorCacheable.getColor();

         if (this.imageComparatorConstraintsInterface.isColorAllowed(0, point, color) || 
               this.imageComparatorConstraintsInterface.isColorAllowed(1, point, color2))
         {
            if (Math.abs(rgb1 - rgb2) <= imageComparisonInfo.getTolerance())
            {
               imageComparisonInfo.pixelsThatMatch++;
            } else
            {
               imageComparisonInfo.add(PixelDeltaFactory.getInstance(point.x, point.y, rgb1, rgb2));
            }
         }
      } else
      {
         imageComparisonInfo.pixelsIgnored++;
      }
   }

   public ImageComparisonResult compare(BufferedImage bufferedImage1, BufferedImage bufferedImage2, Long frameOne, Long frameTwo, int tolerance) throws Exception
   {
      //LogUtil.put(new Log("Start", this, "compare"));
      if (bufferedImage1 == null || bufferedImage2 == null)
      {
         throw new Exception("Input images must not be null.");
      }

      ImageComparisonResult imageComparisonInfo = new ImageComparisonResult(bufferedImage1, bufferedImage2, frameOne, frameTwo, tolerance);

      for (int indexY = 0; indexY < imageComparisonInfo.imageHeight; indexY++)
      {
         for (int indexX = 0; indexX < imageComparisonInfo.imageWidth; indexX++)
         {
            this.process(imageComparisonInfo, PointFactory.getInstance(indexX, indexY));

            if (imageComparisonInfo.getNonMatchingPixelVector().size() > this.imageComparatorConstraintsInterface.getMaxNonMatchingPixelDeltas())
            {
               break;
            }
         }
      }

      //LogUtil.put(new Log("End", this, "compare"));
      return imageComparisonInfo;
   }
}