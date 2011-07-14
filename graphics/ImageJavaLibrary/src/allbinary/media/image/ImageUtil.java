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
package allbinary.media.image;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.batik.ext.awt.image.codec.png.PNGImageWriter;

import abcs.logic.basic.io.AbFileOutputStream;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

public class ImageUtil
{

   private static String IIOIMAGE_POOL_NAME = "IIOIMAGE_POOL_NAME";
   //public static PoolInterface poolInterface = null;
   

   static
   {
      try
      {
         LogUtil.put(new Log("Start", "ImageUtil", "Static Block"));

         /*
         poolInterface =
         PoolInterfaceFactory.getInstance(
         new IIOImageCacheableFactory(),
         PoolType.SHIFT_ONE_VECTOR_POOL,
         CachePolicy.MAX_TIME_THOUSAND_MAX);
          */

         LogUtil.put(new Log("End", "ImageUtil", "Static Block"));
      }
      catch (Exception e)
      {
         LogUtil.put(new Log("Exception", "ImageUtil", "Static Block", e));
      }
   }

   private ImageUtil()
   {
   }

   public static GraphicsConfiguration getDefaultConfiguration()
   {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gd = ge.getDefaultScreenDevice();
      return gd.getDefaultConfiguration();
   }

   public static BufferedImage[] createBufferedImage(
      BufferedImage[] bufferedImageArray, int percent)
      throws Exception
   {
      BufferedImage[] scaledBufferedImageArray =
         new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         int newWidth = bufferedImageArray[index].getWidth() * percent / 100;
         int newHeight = bufferedImageArray[index].getHeight() * percent / 100;

         scaledBufferedImageArray[index] =
            ImageUtil.createBufferedImage(
            bufferedImageArray[index],
            newWidth, newHeight);
      }

      return scaledBufferedImageArray;
   }

   public static BufferedImage createBufferedImage(
      BufferedImage bufferedImage,
      int newWidth, int newHeight)
      throws Exception
   {
      double width = bufferedImage.getWidth();
      double height = bufferedImage.getHeight();
      double d_newWidth = newWidth;
      double d_newHeight = newHeight;
      double widthRatio = d_newHeight / height;
      double heightRatio = d_newHeight / height;

      AffineTransform at = AffineTransform.getScaleInstance(
         widthRatio, heightRatio);

      BufferedImage newBufferedImage = new BufferedImage(
         newWidth, newHeight, BufferedImage.TYPE_INT_ARGB_PRE);

      Graphics2D g = newBufferedImage.createGraphics();
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
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
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
      //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      //{
      LogUtil.put("Draw Was Complete", "ImageUtil", "createBufferedImage");
      //}
      }
       */
      return newBufferedImage;
   }

   public static void saveWithBatik(AbFile file, BufferedImage bufferedImage)
      throws Exception
   {
      PNGImageWriter batikPNGImageWriter = new PNGImageWriter();

      AbFileOutputStream fileOutputStream = new AbFileOutputStream(file);
      
      try
      {
      
      batikPNGImageWriter.writeImage(
         bufferedImage, fileOutputStream);
      
      }
      finally
      {
    	  fileOutputStream.flush();
    	  StreamUtil.getInstance().close(fileOutputStream);
      }
      
      LogUtil.put(new Log("Wrote Image: " + file.getAbsolutePath(), "ImageUtil", "save"));
   }

                        /*
                        Iterator writers = ImageIO.getImageWritersByFormatName("PNG");
                        //ImageWriter writer = null;
                        PNGImageWriter writer = null;
                        while(writers.hasNext())
                        {
                        //writer = (ImageWriter) writers.next();
                        writer = (PNGImageWriter) writers.next();
                        System.out.println("Found: " + writer);
                        }
                        ImageWriteParam param = writer.getDefaultWriteParam();
                        //param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                        //param.setCompressionQuality(1);
                        ImageTypeSpecifier imTy = param.getDestinationType();
                        ImageTypeSpecifier imTySp =
                        ImageTypeSpecifier.createFromRenderedImage(generatedBufferedImageArray[index]);
                        param.setDestinationType(imTySp);
                        try {
                        ImageOutputStream ios = ImageIO.createImageOutputStream(files[index]);
                        writer.setOutput(ios);
                        writer.writeInsert(0, new IIOImage(generatedBufferedImageArray[index], null, null), param);
                        } catch (Exception e) {
                        System.err.println("could not write "+"PNG"+" file with alpha channel !");
                        e.printStackTrace();
                        }
                         * */

                        //ResizeImageJPanel.this.writeImage(generatedBufferedImageArray[index]);
                    /*      
                    boolean isWritten =
                    ImageIO.write((RenderedImage) 
                    generatedBufferedImageArray[index],
                    "PNG",
                    files[index]);
                    LogUtil.put(new Log("Wrote: " + isWritten, this, ""));
                     */

        /*
    private void writeImage(BufferedImage bufferedImage) {
        String design = "ds3";
        String model = "tfy";

        String view = "f";

        String[] partOrder = new String[]{"bod", "cap", "nos"};

        Map colours = defaultColours(null, null);

        ParameterBlock pb = new ParameterBlock();
        pb.add(new Float(100));
        pb.add(new Float(600));

        pb.add(new Byte[]{new Byte((byte) 255), new Byte((byte) 255),
            new Byte((byte) 255), new Byte((byte) 255)
        });

        RenderedImage base = (RenderedImage) JAI.create("constant", pb);

        RenderedImage penpic = base;
        for (int i = 0; i < partOrder.length; i++) {
            String part = partOrder[i];

            String colour = (String) colours.get(part);
            String filename = createFileName(part, colour, view);

            String penBaseDir = ".";

            String absFilename = penBaseDir + AbPathData.getInstance().SEP + filename;

            RenderedImage layer = (RenderedImage) JAI.create("fileload", absFilename);

            if (penpic == null) {
                penpic = layer;
            } else {
                RenderedImage layerAlpha = getAlpha(layer);

                ParameterBlock compPb = new ParameterBlock();
                compPb.addSource(layer);
                compPb.addSource(penpic);
                compPb.add(layerAlpha);
                compPb.add(null);
                compPb.add(new Boolean(false));
                compPb.add(CompositeDescriptor.NO_DESTINATION_ALPHA);

                penpic = (RenderedImage) JAI.create("composite", compPb);
            }
        }

        // Final image, without alpha:
        ParameterBlock removeAlphaPb = new ParameterBlock();
        removeAlphaPb.addSource(penpic);
        removeAlphaPb.add(new int[]{0, 1, 2});
        RenderedImage finalImg = (RenderedImage) JAI.create("bandselect", removeAlphaPb);

        // Add alpha to final image:
        ParameterBlock constAlphaPb = new ParameterBlock();
        constAlphaPb.add(new Float(penpic.getWidth()));
        constAlphaPb.add(new Float(penpic.getHeight()));
        constAlphaPb.add(new Byte[]{new Byte((byte) 255)});

        RenderedImage constAlpha = (RenderedImage) JAI.create("constant", constAlphaPb);

        ParameterBlock constAlphaFinalPb = new ParameterBlock();
        constAlphaFinalPb.addSource(finalImg);
        constAlphaFinalPb.addSource(constAlpha);

        RenderedImage constAlphaFinal = (RenderedImage) JAI.create("bandmerge", constAlphaFinalPb);


        ImageIO.write(penpic, "png", new File("alpha.png"));
    //ImageIO.write(constAlphaFinal, "png", new File("alphaConstant.png"));
    //ImageIO.write(finalImg, "png", new File("noalpha.png"));        
    }
         */
    /*
    public void paint(Graphics graphics) {
    LogUtil.put(new Log("Painting", this, "paint"));
     */
    //graphics.setColor(BasicColors.BLUE.toColor());
    //graphics.fillRect(0, 0, getWidth(),getHeight());
      /*
    int columnIndex = 0;
    int rowIndex = 0;
    for(int index = 0; index < bufferedImageArray.length; index++)
    {
    if(index/9 != 0 && index % 9 == 0)
    {
    rowIndex ++;
    columnIndex = 0;
    }
    graphics.drawImage(this.bufferedImageArray[index],
    bufferedImageArray[index].getWidth(null) * columnIndex, 
    bufferedImageArray[index].getHeight(null) * rowIndex, 
    this.bufferedImageArray[index].getWidth(null),
    this.bufferedImageArray[index].getHeight(null), null);
    columnIndex++;
    }
     */
    /*
    this.jPanel1.update(graphics);
    }
*/   
    /*
	protected static Map defaultColours(String design, String model) {
		
		String ds3tfyDefaultColours = "bod,f35;acc,x01;nos,f10;cli,f10;cap,f10";
		
		Map result = new HashMap();
		String[] records = ds3tfyDefaultColours.split(";");
		for ( int i = 0; i < records.length; i++ ) {
			String[] fields = records[i].split(",");
			result.put(fields[0], fields[1]);
		}
		
		return result;
	}
		
	protected static RenderedImage getAlpha(RenderedImage img) {
		int alphaBandIndex = 3;
		
		ParameterBlock alphaPb = new ParameterBlock();
		alphaPb.addSource(img);
		alphaPb.add(new int[]{alphaBandIndex});
		RenderedImage alphaImg = (RenderedImage)JAI.create("bandselect", alphaPb);
		
		return alphaImg;
	}
      */  
   
   public static void saveWithImageIO(File file, BufferedImage bufferedImage)
   {
      ImageWriter writer = null;
      ImageOutputStream ios = null;

      try
      {
         // Obtain a writer based on the jpeg format.

         Iterator iter;
         iter = ImageIO.getImageWritersByFormatName("jpeg");

         // Validate existence of writer.
         if (!iter.hasNext())
         {
            LogUtil.put(new Log("Unable to save image to jpeg file type.", "ImageUtil", "save"));
            return;
         }

         // Extract writer.
         writer = (ImageWriter) iter.next();

         // Configure writer output destination.
         ios = ImageIO.createImageOutputStream(file);
         writer.setOutput(ios);

         // Set JPEG compression quality to 95%.
         ImageWriteParam iwp = writer.getDefaultWriteParam();
         iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
         iwp.setCompressionQuality(0.95f);

         // Write the image.
         //IIOImageCacheable iioImageCacheable = (IIOImageCacheable)
         //poolInterface.remove(IIOIMAGE_POOL_NAME);

         //iioImageCacheable.setBufferedImage(
         //  (BufferedImage) bufferedImage);

         //writer.write(null, (IIOImage) iioImageCacheable.getIioImage(), iwp);
         writer.write(null, new IIOImage(bufferedImage, null, null), iwp);

         //writer.dispose();

         //poolInterface.add(iioImageCacheable);

         LogUtil.put(new Log("Wrote Image: " + file.getAbsolutePath(), "ImageUtil", "save"));
      }
      catch (Exception e)
      {
         LogUtil.put(new Log("Exception", "ImageUtil", "save", e));
      }
      finally
      {
         try
         {

            if (ios != null)
            {
               ios.flush();
               StreamUtil.getInstance().close(ios);
            }

            if (writer != null)
            {
               writer.dispose();
            }
         }
         catch (IOException e2)
         {
            LogUtil.put(new Log("Exception", "ImageUtil", "save", e2));
         }
      }
   }

   public static String toString(BufferedImage bufferedImage)
   {
      StringBuffer stringBuffer = new StringBuffer();

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
