/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.media.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.allbinary.logic.io.AbFileOutputStream;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.apache.batik.ext.awt.image.codec.png.PNGImageWriter;

/**
 *
 * @author user
 */
public class ImagePersistanceUtil {
 
    private static final ImagePersistanceUtil instance = new ImagePersistanceUtil();
    
    public static ImagePersistanceUtil getInstance() {
        return instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public void saveWithBatik(AbFile file, BufferedImage bufferedImage)
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
      
      LogUtil.put(LogFactory.getInstance("Wrote Image: " + file.getAbsolutePath(), "ImageUtil", "save"));
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
                    LogUtil.put(LogFactory.getInstance("Wrote: " + isWritten, this, ""));
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
    LogUtil.put(LogFactory.getInstance(commonStrings.START, this, canvasStrings.PAINT));
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

   public void saveWithImageIO(String filePath, BufferedImage bufferedImage)
   {
       saveWithImageIO(new File(filePath), bufferedImage);
   }
   
   public void saveWithImageIO(File file, BufferedImage bufferedImage)
   {
      ImageWriter writer = null;
      ImageOutputStream ios = null;

      try
      {
         // Obtain a writer based on the jpeg format.

         Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

         // Validate existence of writer.
         if (!iter.hasNext())
         {
            LogUtil.put(LogFactory.getInstance("Unable to save image to jpeg file type.", this, "save"));
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

         LogUtil.put(LogFactory.getInstance("Wrote Image: " + file.getAbsolutePath(), this, "save"));
      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "save", e));
      }
      finally
      {
         try
         {

            if (ios != null)
            {
               ios.flush();
               
               //Does not implement Closeable?
               //StreamUtil.getInstance().close(ios);
        try
        {
            if (ios != null)
            {
                LogUtil.put(LogFactory.getInstance("Closing: " + ios, ios, "close"));
                ios.close();
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, ios, "close", e));
        }               
            }

            if (writer != null)
            {
               writer.dispose();
            }
         }
         catch (IOException e2)
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "save", e2));
         }
      }
   }
    
}
