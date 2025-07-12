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
import org.allbinary.logic.string.StringMaker;


import javax.microedition.lcdui.Image;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.opengles.ModifierOpenGLESImageProcessor;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;

import org.eclipse.swt.graphics.ImageData;

import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

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

    //private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    private final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();

    private final Features features = Features.getInstance();

    public void setBasicColor(final BasicColor basicColor) {

    }

    //private final int MAX_WIDTH = 1024;
    //private final int MAX_HEIGHT = 1024;
    //private final int[] alphaArray = new int[MAX_WIDTH * MAX_HEIGHT];
    //private final int[] colorArray = new int[1];
    public void setColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {

        org.eclipse.swt.graphics.Image newBufferedImage;
        if (image.isMutable()) {
            final SwtMutableImage swtImage = (SwtMutableImage) image;
            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
        } else {
            final SwtImmutableImage swtImage = (SwtImmutableImage) image;
            //X cannot be cast to class java.awt.image.org.eclipse.swt.graphics.Image
            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
        }

        final short r = basicColor.red;
        final short g = basicColor.green;
        final short b = basicColor.blue;

        final int width = image.getWidth();
        final int height = image.getHeight();

        final ImageData imageData = newBufferedImage.getImageData();
        if (imageData.depth == 24) {
            //int ax;
            int rx;
            int gx;
            int bx;
            int index3;
            for (int index = 0; index < width; index++) {
                for (int index2 = 0; index2 < height; index2++) {
                    //ax = imageData.getAlpha(index,index2);
                    //pixel = imageData.getPixel(index, index2);
                    index3 = (index2 * imageData.bytesPerLine) + (index * 4);
                    rx = (imageData.data[index3] & 0xFF) << 16;
                    gx = (imageData.data[index3 + 1] & 0xFF) << 8;
                    bx = (imageData.data[index3 + 2] & 0xFF);

                    if (rx != 0 || gx != 0 || bx != 0) {
                        imageData.data[index3] = (byte) r;
                        imageData.data[index3 + 1] = (byte) g;
                        imageData.data[index3 + 2] = (byte) b;
                    }
                }
            }
        }

    }

    public void changeColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {

        if(features.isFeature(OpenGLFeatureFactory.getInstance().OPENGL)) {
            //logUtil.put(NO_COPY2, this, commonStrings.CONSTRUCTOR);
            //final CommonSeps commonSeps = CommonSeps.getInstance();
            //logUtil.put(new StringMaker().append(NO_COPY2).append(" from: ").append(originalImage.getWidth()).append(commonSeps.SPACE).append(originalImage.getHeight()).append(" to: ").append(width).append(commonSeps.SPACE).append(height).toString(), this, commonStrings.CONSTRUCTOR);
            
            if (image.getType() >= OpenGLESImage.TYPE) {
                final OpenGLESImage openGLESImage = ((OpenGLESImage) image);
                openGLESImage.imageProcessor = ModifierOpenGLESImageProcessor.getInstance();
                openGLESImage.openGLESImageProperties.redf = basicColor.getRedComponent();
                openGLESImage.openGLESImageProperties.greenf = basicColor.getGreenComponent();
                openGLESImage.openGLESImageProperties.bluef = basicColor.getBlueComponent();
                return;
            }
        }
        
        org.eclipse.swt.graphics.Image newBufferedImage;
        if (image.isMutable()) {
            final SwtMutableImage swtImage = (SwtMutableImage) image;
            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
        } else {
            final SwtImmutableImage swtImage = (SwtImmutableImage) image;
            //X cannot be cast to class java.awt.image.org.eclipse.swt.graphics.Image
            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
        }

        final float MAX = 255;
        final float r = ((float) basicColor.red) / MAX;
        final float g = ((float) basicColor.green) / MAX;
        final float b = ((float) basicColor.blue) / MAX;

        final int width = image.getWidth();
        final int height = image.getHeight();

        //int ax;
        int rx;
        int gx;
        int bx;
        final ImageData imageData = newBufferedImage.getImageData();

        if (imageData.depth == 24) {
            //int pixel;
            int index3;
            for (int index = 0; index < width; index++) {
                for (int index2 = 0; index2 < height; index2++) {
                    //ax = imageData.getAlpha(index, index2);
                    //pixel = imageData.getPixel(index, index2);
                    index3 = (index2 * imageData.bytesPerLine) + (index * 4);
                    rx = (imageData.data[index3] & 0xFF) << 16;
                    gx = (imageData.data[index3 + 1] & 0xFF) << 8;
                    bx = (imageData.data[index3 + 2] & 0xFF);
                    //ax *= a;
                    rx *= r;
                    gx *= g;
                    bx *= b;

                    imageData.data[index3] = (byte) rx;
                    imageData.data[index3 + 1] = (byte) gx;
                    imageData.data[index3 + 2] = (byte) bx;
                }
            }
        }

//        final Graphics2D graphics = newBufferedImage.createGraphics();
//        graphics.setBackground(new Color(0, 0, 0, 0));
//        graphics.clearRect(0, 0, newBufferedImage.getWidth(),
//                newBufferedImage.getHeight());
//        
//        final Color newColor = new Color(color);
//        graphics.setXORMode(newColor);
//        graphics.drawImage( ((org.eclipse.swt.graphics.Image) ((SwtMutableImage) originalImage).getImage()), null, 0, 0);
//        //graphics.drawImage( ((org.eclipse.swt.graphics.Image) ((SwtImmutableImage) originalImage).getImage()), null, 0, 0);
//        graphics.dispose();
    }

    public void setAlpha(final Image unusedOriginalImage, final Image image, final int imageIndex, final int alphaInt) {

        if(features.isFeature(OpenGLFeatureFactory.getInstance().OPENGL)) {
            //logUtil.put(NO_COPY2, this, commonStrings.CONSTRUCTOR);
            //final CommonSeps commonSeps = CommonSeps.getInstance();
            //logUtil.put(new StringMaker().append(NO_COPY2).append(" from: ").append(originalImage.getWidth()).append(commonSeps.SPACE).append(originalImage.getHeight()).append(" to: ").append(width).append(commonSeps.SPACE).append(height).toString(), this, commonStrings.CONSTRUCTOR);
            
            if (image.getType() >= OpenGLESImage.TYPE) {
                final OpenGLESImage openGLESImage = ((OpenGLESImage) image);
                openGLESImage.imageProcessor = ModifierOpenGLESImageProcessor.getInstance();
                openGLESImage.openGLESImageProperties.alphaf = ((float) alphaInt)  / 255f;
                return;
            }
        }
        
        this.setAlpha(image, alphaInt);
    }

    public void setAlpha(final Image image, final int alphaInt) {
        
        byte alpha = (byte) alphaInt;
        org.eclipse.swt.graphics.Image newBufferedImage;
        if (image.isMutable()) {
            final SwtMutableImage swtImage = (SwtMutableImage) image;
            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
        } else {
            final SwtImmutableImage swtImage = (SwtImmutableImage) image;
            //X cannot be cast to class java.awt.image.org.eclipse.swt.graphics.Image
            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
        }

        newBufferedImage.getImageData().alpha = alpha;
    }
    
    public Image[] getImageArray(final Image[] originalImageArray) {

        //return originalImageArray;
        try {
            final Image[] newImageArray = new Image[originalImageArray.length];

            final int size = newImageArray.length;
            Image originalImage;
            for (int index = 0; index < size; index++) {
                originalImage = originalImageArray[index];

                //final Image image = imageCreationUtil.getInstance(originalImage.getWidth(), originalImage.getHeight());
                //image.getGraphics().drawImage(originalImage, 0, 0, Anchor.TOP_LEFT);
                final Image image = this.imageCopyUtil.createImage(originalImage);
                newImageArray[index] = image;
            }

            return newImageArray;
        } catch (Exception e) {
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
