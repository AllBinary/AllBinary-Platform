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
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class ImageUtil
{
    private static final ImageUtil instance = new ImageUtil();

    /**
     * @return the instance
     */
    public static ImageUtil getInstance() {
        return instance;
    }
    
   private String IIOIMAGE_POOL_NAME = "IIOIMAGE_POOL_NAME";
   //public static PoolInterface poolInterface = null;

   private ImageUtil()
   {
      try
      {
         LogUtil.put(LogFactory.getInstance("Start", "ImageUtil", "Static Block"));

         /*
         poolInterface =
         PoolInterfaceFactory.getInstance(
         new IIOImageCacheableFactory(),
         PoolType.SHIFT_ONE_VECTOR_POOL,
         CachePolicy.MAX_TIME_THOUSAND_MAX);
          */

         LogUtil.put(LogFactory.getInstance("End", "ImageUtil", "Static Block"));
      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance("Exception", "ImageUtil", "Static Block", e));
      }
   }

   public GraphicsConfiguration getDefaultConfiguration()
   {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gd = ge.getDefaultScreenDevice();
      return gd.getDefaultConfiguration();
   }

   public BufferedImage create(final int width, final int height)
   {
      final GraphicsConfiguration graphicsConfiguration = this.getDefaultConfiguration();
      return graphicsConfiguration.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
   }
   
   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final int percent)
      throws Exception
   {
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         final int newWidth = bufferedImageArray[index].getWidth() * percent / 100;
         final int newHeight = bufferedImageArray[index].getHeight() * percent / 100;

         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImageArray[index], newWidth, newHeight);
      }

      return scaledBufferedImageArray;
   }

   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final int width, final int height)
      throws Exception
   {
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImageArray[index], width, height);
      }

      return scaledBufferedImageArray;
   }
   
   public BufferedImage createBufferedImage(final BufferedImage bufferedImage, final int newWidth, int newHeight)
      throws Exception
   {
      //final double width = bufferedImage.getWidth();
      final double height = bufferedImage.getHeight();
      //final double d_newWidth = newWidth;
      final double d_newHeight = newHeight;
      final double widthRatio = d_newHeight / height;
      final double heightRatio = d_newHeight / height;

      final AffineTransform at = AffineTransform.getScaleInstance(
         widthRatio, heightRatio);

      final BufferedImage newBufferedImage = new BufferedImage(
         newWidth, newHeight, BufferedImage.TYPE_INT_ARGB_PRE);

      final Graphics2D g = newBufferedImage.createGraphics();
      g.drawRenderedImage(bufferedImage, at);

      /*
      Graphics2D g = (Graphics2D) newBufferedImage.getGraphics();
      g.scale(widthRatio, heightRatio);
      
      ImageObserver imageObserver = (ImageObserver) new JFrame();
      boolean isDrawDone = g.drawImage(bufferedImage, 0, 0, imageObserver);
      
      if(!isDrawDone)
      {
      boolean isTimeUp = false;
      final int MAX = 10;
      int numberOfRetries = 0;
      while(!isDrawDone && !isTimeUp)
      {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
      LogUtil.put("Draw Did Not Finish Yet.  Must Wait.... :-< Retry: " + numberOfRetries, "ImageUtil", "createBufferedImage");
      }
      
      Thread.currentThread().sleep(1000);
      
      isDrawDone = imageObserver.imageUpdate(
      newBufferedImage, ImageObserver.ALLBITS, 0, 0, 0, 0);
      
      if(numberOfRetries > MAX)
      {
      throw new Exception("Draw Image Is Taking To Long. >" + numberOfRetries*1000);
      }
      
      numberOfRetries++;
      }
      }
      else
      {
      //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      //{
      LogUtil.put("Draw Was Complete", "ImageUtil", "createBufferedImage");
      //}
      }
       */
      return newBufferedImage;
   }

   public String toString(BufferedImage bufferedImage)
   {
      final StringBuffer stringBuffer = new StringBuffer();

      stringBuffer.append(" BufferedImage: ");
      stringBuffer.append(" Width: ");
      stringBuffer.append(bufferedImage.getWidth());
      stringBuffer.append(" Height: ");
      stringBuffer.append(bufferedImage.getHeight());
      stringBuffer.append(" Type: ");
      stringBuffer.append(bufferedImage.getType());

      return stringBuffer.toString();
   }
}
