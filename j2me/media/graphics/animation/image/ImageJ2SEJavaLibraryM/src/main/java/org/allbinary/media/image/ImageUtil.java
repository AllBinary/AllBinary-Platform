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
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

public class ImageUtil
{
    private static final ImageUtil instance = new ImageUtil();

    /**
     * @return the instance
     */
    public static ImageUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
   private final CommonSeps commonSeps = CommonSeps.getInstance();
   
   private String IIOIMAGE_POOL_NAME = "IIOIMAGE_POOL_NAME";
   //public static PoolInterface poolInterface = null;

   private final String CREATE_BUFFERED_IMAGE = "createBufferedImage";

   private ImageUtil()
   {
      try
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));

         /*
         poolInterface =
         PoolInterfaceFactory.getInstance(
         new IIOImageCacheableFactory(),
         PoolType.SHIFT_ONE_VECTOR_POOL,
         CachePolicy.MAX_TIME_THOUSAND_MAX);
          */

      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
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
   
   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final int percent, final boolean scale)
      throws Exception
   {
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         final int newWidth = (int) (bufferedImageArray[index].getWidth() * percent / 100);
         final int newHeight = (int) (bufferedImageArray[index].getHeight() * percent / 100);

         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImageArray[index], newWidth, newHeight, scale);
      }

      return scaledBufferedImageArray;
   }

   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final float percent, final boolean scale)
      throws Exception
   {
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         final int newWidth = (int) (bufferedImageArray[index].getWidth() * percent);
         final int newHeight = (int) (bufferedImageArray[index].getHeight() * percent);

         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImageArray[index], newWidth, newHeight, scale);
      }

      return scaledBufferedImageArray;
   }
   
   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final int width, final int height, final boolean scale)
      throws Exception
   {
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImageArray[index], width, height, scale);
      }

      return scaledBufferedImageArray;
   }

   public BufferedImage createBufferedImage(final BufferedImage bufferedImage, final int newWidth, int newHeight)
      throws Exception
   {
       return this.createBufferedImage(bufferedImage, newWidth, newHeight, true);
   }
   
   public BufferedImage createBufferedImage(final BufferedImage bufferedImage, final int newWidth, int newHeight, final boolean scale) throws Exception {
       return this.createBufferedImage(bufferedImage, newWidth, newHeight, scale, false);
   }
   
   public BufferedImage createBufferedImage(final BufferedImage bufferedImage, final int newWidth, int newHeight, final boolean scale, final boolean allowTranslate)
      throws Exception
   {
      final double width = bufferedImage.getWidth();
      final double height = bufferedImage.getHeight();
      final double d_newWidth = newWidth;
      final double d_newHeight = newHeight;
      final double widthRatio = d_newWidth / width;
      final double heightRatio = d_newHeight / height;

      double ratioX = 1.0;
      double ratioY = 1.0;
      if(scale) {
          ratioX = widthRatio;
          ratioY = heightRatio;
      }
      
      final AffineTransform affineTransform = AffineTransform.getScaleInstance(ratioX, ratioY);
      
      LogUtil.put(LogFactory.getInstance(new StringMaker().append(width).append(this.commonSeps.FORWARD_SLASH).append(height)
              .append(this.commonSeps.COLON).append(newWidth).append(this.commonSeps.FORWARD_SLASH).append(newHeight).append(this.commonSeps.COLON)
              .append(widthRatio).append(this.commonSeps.FORWARD_SLASH).append(heightRatio).toString(), this, CREATE_BUFFERED_IMAGE));

      if(!scale && allowTranslate) {
          final double dx = (newWidth - width) / 2;
          final double dy = (newHeight - height) / 2;
          LogUtil.put(LogFactory.getInstance(new StringMaker().append("Translate dx: ").append(dx).append(" dy: ").append(dy).toString(), this, CREATE_BUFFERED_IMAGE));
          affineTransform.translate(dx, dy);
          
//          if (newWidth < width) {
//              final double translate = -(width - newWidth);
//              LogUtil.put(LogFactory.getInstance("Translating to keep image centered x3: " + translate, this, CREATE_BUFFERED_IMAGE));
//              affineTransform.translate(translate, 0);
//          }
//          if (newHeight < height) {
//              //final double translate = -(height - newHeight) / 2;
//              final double translate = -(height - newHeight);
//              LogUtil.put(LogFactory.getInstance("Translating to keep image centered y0: " + translate, this, CREATE_BUFFERED_IMAGE));
//              affineTransform.translate(0, translate);
//          }
//
//          //if(newHeight > height && widthRatio <= 1) {
//          if (newHeight > height) {
//              final double translate = (newHeight - height) / 2;
//              LogUtil.put(LogFactory.getInstance("Translating to keep image centered y1: " + translate, this, CREATE_BUFFERED_IMAGE));
//              affineTransform.translate(0, translate);
//          }
//          //if(newWidth > width && heightRatio <= 1) {
//          if (newWidth > width) {
//              final double translate = (newWidth - width) / 2;
//              LogUtil.put(LogFactory.getInstance("Translating to keep image centered x2: " + translate, this, CREATE_BUFFERED_IMAGE));
//              affineTransform.translate(translate, 0);
//          }
      }

      final BufferedImage newBufferedImage = new BufferedImage(
         newWidth, newHeight, BufferedImage.TYPE_INT_ARGB_PRE);

      final Graphics2D graphics = newBufferedImage.createGraphics();
      graphics.drawRenderedImage(bufferedImage, affineTransform);

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
      LogUtil.put("Draw Did Not Finish Yet.  Must Wait.... :-< Retry: " + numberOfRetries, "ImageUtil", CREATE_BUFFERED_IMAGE);
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
      LogUtil.put("Draw Was Complete", "ImageUtil", CREATE_BUFFERED_IMAGE);
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
