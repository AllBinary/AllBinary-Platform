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

import org.allbinary.logic.communication.log.LogUtil;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import org.allbinary.logic.communication.log.LogFactory;

public class MirrorImageUtil
{

    private static final MirrorImageUtil instance = new MirrorImageUtil();

    /**
     * @return the instance
     */
    public static MirrorImageUtil getInstance() {
        return instance;
    }
    
    private final ImageUtil imageUtil = ImageUtil.getInstance();
    
   private MirrorImageUtil()
   {
   }

   public BufferedImage getImage(
      BufferedImage bufferedImage, boolean verticle, boolean horizontal)
   {
      LogUtil.put(LogFactory.getInstance("Starting", this, "getImage"));

      BufferedImage newBufferedImage = 
         this.imageUtil.create(
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

   public BufferedImage[] getImages(
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

      LogUtil.put(LogFactory.getInstance("numberOfFramesPerOrientation: " + numberOfFramesPerOrientation + 
         " numberOfFrames: " + numberOfFrames, this, "getImages"));
      
      final BufferedImage[] bufferedImageArray = new BufferedImage[numberOfFrames];
      
      int y = 0;
      for (int index = 0; index < numberOfFramesPerOrientation; index++)
      {
         int x = index * cellWidth;
         
         bufferedImageArray[index] = bufferedImage.getSubimage(x, y, cellWidth, cellHeight);
      }

      for (int index = 0; index < numberOfFramesPerOrientation; index++)
      {
         bufferedImageArray[index + numberOfFramesPerOrientation] = 
            this.getImage(bufferedImageArray[index], verticle, horizontal);
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

}
