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
import javax.microedition.lcdui.Image;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.microemu.device.j2se.J2SEImmutableImage;
import org.microemu.device.j2se.J2SEMutableImage;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    
    private static final ImageModifierUtil instance = new ImageModifierUtil();

    /**
     * @return the instance
     */
    public static ImageModifierUtil getInstanceOrCreate() {
        return instance;
    }

    public void setBasicColor(final BasicColor basicColor) {
        
    }

    public void setAlpha(final Image originalImage, final Image image, final int imageIndex, final int alphaInt) {
        
//        byte alpha = (byte) alphaInt;
//        BufferedImage newBufferedImage;
//        //java.awt.Image newBufferedImage;
//        if(image.isMutable()) {
//            final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
//            newBufferedImage = (BufferedImage) j2seImage.getImage();
//        } else {
//            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) image;
//            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
//            newBufferedImage = (BufferedImage) j2seImage.getImage();                        
//        }
//        
//        alpha %= 0xff;
//        final int width = image.getWidth();
//        final int height = image.getHeight();
//
//        int color;
//        int mask;
//        int newcolor;
//
//        for (int index = 0; index < width; index++) {
//            for (int index2 = 0; index2 < height; index2++) {
//                color = newBufferedImage.getRGB(index, index2);
//                mask = (alpha << 24) | 0x00ffffff;
//                newcolor = color & mask;
//                newBufferedImage.setRGB(index, index2, newcolor);
//            }
//        }

    }
    
    public Image[] getImageArray(final Image[] originalImageArray) {

        return originalImageArray;

//        try {
//            final Image[] newImageArray = new Image[originalImageArray.length];
//
//            final int size = newImageArray.length;
//            Image originalImage;
//            for (int index = 0; index < size; index++) {
//                originalImage = originalImageArray[index];
//
//                final Image image = ImageCreationUtil.getInstance().getInstance(
//                        originalImage.getWidth(), originalImage.getHeight());
//
//                image.getGraphics().drawImage(originalImage, 0, 0, Anchor.TOP_LEFT);
//                newImageArray[index] = image;
//            }
//
//            return newImageArray;
//        } catch(Exception e) {
//            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, "getImageArray", e);
//            return originalImageArray;
//        }
    }

    public void handleImage(final Image[] imageArray, final int index, final Image image) {
        
    }

    public void reset() {
        
    }
    
}
