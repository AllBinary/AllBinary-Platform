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

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class MirrorImageUtil
{

   private MirrorImageUtil()
   {
   }

   public static BufferedImage getImage(
      BufferedImage bufferedImage, boolean verticle, boolean horizontal)
   {
      LogUtil.put(new Log("Starting", "MirrorImageUtil", "getImage"));

      BufferedImage newBufferedImage = 
         BufferedImageUtil.create(
            bufferedImage.getWidth(null),
            bufferedImage.getHeight(null)
         );

      Graphics2D g = newBufferedImage.createGraphics();

      if (verticle)
      {
         AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
         tx.translate(0, -bufferedImage.getHeight(null));
         AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
         bufferedImage = op.filter(bufferedImage, null);
      }

      if (horizontal)
      {
         AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
         tx.translate(-bufferedImage.getWidth(null), 0);
         AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
         bufferedImage = op.filter(bufferedImage, null);
      }

      g.drawImage(bufferedImage, 0, 0,
         bufferedImage.getWidth(null), bufferedImage.getHeight(null), null);

      g.dispose();

      return newBufferedImage;
   }

   public static BufferedImage[] getImages(
      BufferedImage bufferedImage, boolean verticle, boolean horizontal)
   {
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      
      int cellHeight = height;
      int cellWidth = height;
      
      int numberOfFramesPerOrientation = width / cellWidth;
      int numberOfFrames = numberOfFramesPerOrientation;
      
      if(verticle)
         numberOfFrames *= 2;
      if(horizontal)
         numberOfFrames *= 2;

      LogUtil.put(new Log("numberOfFramesPerOrientation: " + numberOfFramesPerOrientation + 
         " numberOfFrames: " + numberOfFrames, "MirrorImageUtil", ""));
      
      BufferedImage bufferedImageArray[] = new BufferedImage[numberOfFrames];
      
      int y = 0;
      for (int index = 0; index < numberOfFramesPerOrientation; index++)
      {
         int x = index * cellWidth;
         
         bufferedImageArray[index] = bufferedImage.getSubimage(x, y, cellWidth, cellHeight);
      }

      for (int index = 0; index < numberOfFramesPerOrientation; index++)
      {
         bufferedImageArray[index + numberOfFramesPerOrientation] = 
            MirrorImageUtil.getImage(bufferedImageArray[index], verticle, horizontal);
      }

      /*
      for (int index = 0; index < bufferedImageArray.length - 1; index++)
      {
      bufferedImageArray[index] = 
      MirrorImageUtil.getImage(bufferedImage);
      }
       */

      //bufferedImageArray[bufferedImageArray.length - 1] = bufferedImage;

      return bufferedImageArray;
   }

   public static BufferedImage createSpriteImage(
      BufferedImage bufferedImageArray[])
   {
      GraphicsConfiguration gc = ImageUtil.getDefaultConfiguration();

      int columns = bufferedImageArray.length;
      int max = columns;
      int rows = 0;

      if (bufferedImageArray.length < columns)
      {
         columns = bufferedImageArray.length;
      }

      rows = (bufferedImageArray.length / columns);

      //Extra row for incomplete but needed row
      if (bufferedImageArray.length % columns != 0)
      {
         rows++;
      }

      BufferedImage bufferedImage = BufferedImageUtil.create(
         bufferedImageArray[0].getWidth(null) * columns,
         bufferedImageArray[0].getHeight(null) * rows);

      Graphics2D g = bufferedImage.createGraphics();
      //g.translate((neww-w)/2, (newh-h)/2);

      int columnIndex = 0;
      int rowIndex = 0;

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         if (index / max != 0 && index % max == 0)
         {
            rowIndex++;
            columnIndex = 0;
         }

         g.drawImage(bufferedImageArray[index],
            bufferedImageArray[index].getWidth(null) * columnIndex,
            bufferedImageArray[index].getHeight(null) * rowIndex,
            bufferedImageArray[index].getWidth(null),
            bufferedImageArray[index].getHeight(null), null);
         //g.drawRenderedImage(image, null);

         columnIndex++;
      }

      g.dispose();

      return bufferedImage;
   }
}
