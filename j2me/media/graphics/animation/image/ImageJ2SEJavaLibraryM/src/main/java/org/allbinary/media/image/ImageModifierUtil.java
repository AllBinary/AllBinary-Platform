/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.microedition.lcdui.Image;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.microemu.device.j2se.J2SEImmutableImage;
import org.microemu.device.j2se.J2SEMutableImage;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final ImageModifierUtil instance = new ImageModifierUtil();

    /**
     * @return the instance
     */
    public static ImageModifierUtil getInstanceOrCreate() {
        return instance;
    }

    public void setBasicColor(final BasicColor basicColor) {
        
    }

    //private final int MAX_WIDTH = 1024;
    //private final int MAX_HEIGHT = 1024;
    //private final int[] alphaArray = new int[MAX_WIDTH * MAX_HEIGHT];
    private final int[] colorArray = new int[1];

    public void setColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {

        BufferedImage newBufferedImage;
        //java.awt.Image newBufferedImage;
        if(image.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
            newBufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) image;
            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
            newBufferedImage = (BufferedImage) j2seImage.getImage();                        
        }

        final short r = basicColor.red;
        final short g = basicColor.green;
        final short b = basicColor.blue;
        
        final int width = image.getWidth();
        final int height = image.getHeight();
        
        ColorModel colorModel;
        Object dataElements;
        int ax;
        int rx;
        int gx;
        int bx;
        for (int index = 0; index < width; index++) {
            for (int index2 = 0; index2 < height; index2++) {
                colorModel = newBufferedImage.getColorModel();
                dataElements = newBufferedImage.getRaster().getDataElements(index, index2, null);
                ax = colorModel.getAlpha(dataElements);
                rx = colorModel.getRed(dataElements);
                gx = colorModel.getGreen(dataElements);
                bx = colorModel.getBlue(dataElements);
                if(rx != 0 || gx != 0 || bx != 0) {
                    newBufferedImage.setRGB(index, index2, (ax << 24) | (r << 16) | (g << 8) | (b));
                }
            }
        }
        
    }

    public void changeColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {
        
        BufferedImage newBufferedImage;
        //java.awt.Image newBufferedImage;
        if(image.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
            newBufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) image;
            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
            newBufferedImage = (BufferedImage) j2seImage.getImage();                        
        }

        final float MAX = 255;
        final float r = ((float) basicColor.red) / MAX;
        final float g = ((float) basicColor.green) / MAX;
        final float b = ((float) basicColor.blue) / MAX;
        
        final int width = image.getWidth();
        final int height = image.getHeight();
        
        ColorModel colorModel;
        Object dataElements;
        int ax;
        int rx;
        int gx;
        int bx;
        for (int index = 0; index < width; index++) {
            for (int index2 = 0; index2 < height; index2++) {
                colorModel = newBufferedImage.getColorModel();
                dataElements = newBufferedImage.getRaster().getDataElements(index, index2, null);
                ax = colorModel.getAlpha(dataElements);
                rx = colorModel.getRed(dataElements);
                gx = colorModel.getGreen(dataElements);
                bx = colorModel.getBlue(dataElements);
                //ax *= a;
                rx *= r;
                gx *= g;
                bx *= b;
                newBufferedImage.setRGB(index, index2, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }

//        final Graphics2D graphics = newBufferedImage.createGraphics();
//        graphics.setBackground(new Color(0, 0, 0, 0));
//        graphics.clearRect(0, 0, newBufferedImage.getWidth(),
//                newBufferedImage.getHeight());
//        
//        final Color newColor = new Color(color);
//        graphics.setXORMode(newColor);
//        graphics.drawImage( ((BufferedImage) ((J2SEMutableImage) originalImage).getImage()), null, 0, 0);
//        //graphics.drawImage( ((BufferedImage) ((J2SEImmutableImage) originalImage).getImage()), null, 0, 0);
//        graphics.dispose();
        
    }
    
    public void setAlpha(final Image unusedOriginalImage, final Image image, final int imageIndex, final int alphaInt) {
        
        byte alpha = (byte) alphaInt;
        BufferedImage newBufferedImage;
        //java.awt.Image newBufferedImage;
        if(image.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
            newBufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) image;
            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
            newBufferedImage = (BufferedImage) j2seImage.getImage();                        
        }

        final int width = image.getWidth();
        final int height = image.getHeight();

        final WritableRaster raster = newBufferedImage.getAlphaRaster();

        //raster.getPixels(0, 0, width, height, alphaArray);
        for (int index = 0; index < width; index++) {
            for (int index2 = 0; index2 < height; index2++) {
                raster.getPixel(index, index2, colorArray);
                colorArray[0] = alpha & colorArray[0];
                raster.setPixel(index, index2, colorArray);
                //alphaArray[index * index2] = alpha & alphaArray[index * index2];
            }
        }
        //raster.setPixels(0, 0, width, height, alphaArray);

//This only works if the image is lossless
//        alpha %= 0xff;
//        int color;
//        int mask;
//        int newcolor;

        
//        final int[] pixelArray = new int[width * height];
//        newBufferedImage.getRGB(0, 0, width, height, pixelArray, 0, width);
//        for (int index = 0; index < width; index++) {
//            for (int index2 = 0; index2 < height; index2++) {
//                //color = newBufferedImage.getRGB(index, index2);
//                //mask = (alpha << 24) | 0x00ffffff;
//                //newcolor = color & mask;
//                //newcolor = color;
//                //newBufferedImage.setRGB(index, index2, newcolor);
//                pixelArray[index * index2] = pixelArray[index * index2] & mask;
//            }
//        }
//        newBufferedImage.setRGB(0, 0, width, height, pixelArray, 0, width);

    }
    
    public Image[] getImageArray(final Image[] originalImageArray) {

        //return originalImageArray;

        try {
            final Image[] newImageArray = new Image[originalImageArray.length];

            final int size = newImageArray.length;
            Image originalImage;
            for (int index = 0; index < size; index++) {
                originalImage = originalImageArray[index];

                final Image image = ImageCreationUtil.getInstance().getInstance(
                        originalImage.getWidth(), originalImage.getHeight());

                image.getGraphics().drawImage(originalImage, 0, 0, Anchor.TOP_LEFT);
                newImageArray[index] = image;
            }

            return newImageArray;
        } catch(Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, this, "getImageArray", e);
            return originalImageArray;
        }
    }

    public void handleImage(final Image[] imageArray, final int index, final Image image) {
        
    }

    public void reset() {
        
    }
    
}
