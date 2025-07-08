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
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;

public class ImageUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
         logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

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
         logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
      }
   }

   private GraphicsConfiguration getDefaultConfiguration()
   {
      final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      final GraphicsDevice gd = ge.getDefaultScreenDevice();
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
       final int size = bufferedImageArray.length;
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[size];

      BufferedImage bufferedImage;
      for (int index = 0; index < size; index++)
      {
          bufferedImage = bufferedImageArray[index];
         final int newWidth = (int) (bufferedImage.getWidth() * percent / 100);
         final int newHeight = (int) (bufferedImage.getHeight() * percent / 100);

         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImage, newWidth, newHeight, scale);
      }

      return scaledBufferedImageArray;
   }

   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final float percent, final boolean scale)
      throws Exception
   {
       final int size = bufferedImageArray.length;
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[size];

      BufferedImage bufferedImage;
      for (int index = 0; index < size; index++)
      {
          bufferedImage = bufferedImageArray[index];
         final int newWidth = (int) (bufferedImage.getWidth() * percent);
         final int newHeight = (int) (bufferedImage.getHeight() * percent);

         scaledBufferedImageArray[index] =
            this.createBufferedImage(bufferedImage, newWidth, newHeight, scale);
      }

      return scaledBufferedImageArray;
   }
   
   public BufferedImage[] createBufferedImage(final BufferedImage[] bufferedImageArray, final int width, final int height, final boolean scale)
      throws Exception
   {
       final int size = bufferedImageArray.length;
      final BufferedImage[] scaledBufferedImageArray = new BufferedImage[size];

      for (int index = 0; index < size; index++)
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
      
      logUtil.put(new StringMaker().append((float) width).append(this.commonSeps.FORWARD_SLASH).append((float) height)
              .append(this.commonSeps.COLON).append(newWidth).append(this.commonSeps.FORWARD_SLASH).append(newHeight).append(this.commonSeps.COLON)
              .append((float) widthRatio).append(this.commonSeps.FORWARD_SLASH).append((float) heightRatio).toString(), this, CREATE_BUFFERED_IMAGE);

      if(!scale && allowTranslate) {
          final double dx = (newWidth - width) / 2;
          final double dy = (newHeight - height) / 2;
          logUtil.put(new StringMaker().append("Translate dx: ").append((float) dx).append(" dy: ").append((float) dy).toString(), this, CREATE_BUFFERED_IMAGE);
          affineTransform.translate(dx, dy);
          
//          if (newWidth < width) {
//              final double translate = -(width - newWidth);
//              logUtil.put("Translating to keep image centered x3: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(translate, 0);
//          }
//          if (newHeight < height) {
//              //final double translate = -(height - newHeight) / 2;
//              final double translate = -(height - newHeight);
//              logUtil.put("Translating to keep image centered y0: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(0, translate);
//          }
//
//          //if(newHeight > height && widthRatio <= 1) {
//          if (newHeight > height) {
//              final double translate = (newHeight - height) / 2;
//              logUtil.put("Translating to keep image centered y1: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(0, translate);
//          }
//          //if(newWidth > width && heightRatio <= 1) {
//          if (newWidth > width) {
//              final double translate = (newWidth - width) / 2;
//              logUtil.put("Translating to keep image centered x2: " + translate, this, CREATE_BUFFERED_IMAGE);
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
       final CommonLabels commonLabels = CommonLabels.getInstance();
      final StringBuffer stringBuffer = new StringBuffer();

      stringBuffer.append(" BufferedImage -");
      stringBuffer.append(commonLabels.WIDTH_LABEL);
      stringBuffer.append(bufferedImage.getWidth());
      stringBuffer.append(commonLabels.HEIGHT_LABEL);
      stringBuffer.append(bufferedImage.getHeight());
      stringBuffer.append(" Type: ");
      stringBuffer.append(bufferedImage.getType());

      return stringBuffer.toString();
   }
}
