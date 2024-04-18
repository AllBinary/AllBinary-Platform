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
package org.allbinary.media.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageJ2SERotationUtil
{
   private static final ImageJ2SERotationUtil instance = new ImageJ2SERotationUtil();

    /**
     * @return the instance
     */
    public static ImageJ2SERotationUtil getInstance() {
        return instance;
    }
   
    private final ImageUtil imageUtil = ImageUtil.getInstance();
    private final ImageJ2SEUtil imageJ2SEUtil = ImageJ2SEUtil.getInstance();

    private ImageJ2SERotationUtil() {
    }

   public BufferedImage getRotatedImage(final Image bufferedImage, final int totalAngle) {
       return this.getRotatedImage(bufferedImage, TWO_PIE * totalAngle / 360);
   }

   public BufferedImage getRotatedImage(final Image bufferedImage, final double radians)
   {
      final BufferedImage newBufferedImage = 
         this.imageUtil.create(bufferedImage.getWidth(null), bufferedImage.getHeight(null));
      
      return this.getRotatedImage(bufferedImage, newBufferedImage, newBufferedImage.createGraphics(), radians);
   }
   
   private BufferedImage getRotatedImage(final Image bufferedImage, final BufferedImage newBufferedImage, final Graphics2D g, final double radians)
   {
      //g.translate((neww-w)/2, (newh-h)/2);

      g.rotate(radians, 
         newBufferedImage.getWidth(null) / 2, 
         newBufferedImage.getHeight(null) / 2);

      g.drawImage(bufferedImage, 0, 0, 
         bufferedImage.getWidth(null), bufferedImage.getHeight(null), null);

      //g.drawRenderedImage(image, null);
      g.dispose();

      return newBufferedImage;
   }

   private final double TWO_PIE = 2 * Math.PI;

   public BufferedImage rotateImage(final Image bufferedImage, final BufferedImage newBufferedImage, final int totalAngle)
   {
       final Graphics2D g = newBufferedImage.createGraphics();
       g.setBackground(imageJ2SEUtil.TRANSPARENT_COLOR);
       g.clearRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
       return this.getRotatedImage(bufferedImage, newBufferedImage, g, TWO_PIE * totalAngle / 360);
   }
   
   public BufferedImage getRotatedImage(final Image bufferedImage, final BufferedImage newBufferedImage, final int totalAngle)
   {
       final Graphics2D g = newBufferedImage.createGraphics();
       return this.getRotatedImage(bufferedImage, newBufferedImage, g, TWO_PIE * totalAngle / 360);
   }
   
   public BufferedImage[] getRotatedImages(final Image bufferedImage, final int numberOfFrames, final int totalAngle)
   {
      final BufferedImage bufferedImageArray[] = new BufferedImage[numberOfFrames];
      
      final double arc = (TWO_PIE) * totalAngle / 360;
      
      final int size = bufferedImageArray.length;
      for(int index = 0; index < size; index++)
      {
         final double radians = (arc / size) * index;
         bufferedImageArray[index] =
            this.getRotatedImage(bufferedImage, radians);
      }
      return bufferedImageArray;
   }

   public BufferedImage createSpriteImage(final BufferedImage[] bufferedImageArray)
   {
      int columns = 9;
      int rows = 0;

      final int size = bufferedImageArray.length;
      if(size < columns)
      {
          columns = size;
      }
      
      rows = (size / columns);
      
      //Extra row for incomplete but needed row
      if(size % columns != 0)
      {
          rows++;
      }
      
      final BufferedImage firstBufferedImage = bufferedImageArray[0];
      final BufferedImage bufferedImage = 
         this.imageUtil.create(
         firstBufferedImage.getWidth(null) * columns, 
         firstBufferedImage.getHeight(null) * rows);

      final Graphics2D g = bufferedImage.createGraphics();
      //g.translate((neww-w)/2, (newh-h)/2);

      int columnIndex = 0;
      int rowIndex = 0;

      BufferedImage nextBufferedImage;
      for(int index = 0; index < size; index++)
      {
         if(index/9 != 0 && index % 9 == 0)
         {
            rowIndex++;
            columnIndex = 0;
         }

         nextBufferedImage = bufferedImageArray[index];
         g.drawImage(nextBufferedImage, 
            nextBufferedImage.getWidth(null) * columnIndex, 
            nextBufferedImage.getHeight(null) * rowIndex, 
            nextBufferedImage.getWidth(null), 
            nextBufferedImage.getHeight(null), null);
         //g.drawRenderedImage(image, null);

         columnIndex++;
      }

      g.dispose();
      
      return bufferedImage;
   }   
}
