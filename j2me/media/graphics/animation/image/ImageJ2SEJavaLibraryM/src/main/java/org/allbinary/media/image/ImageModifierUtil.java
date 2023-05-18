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

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.microedition.lcdui.Image;
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

    public void setAlpha(final Image originalImage, final Image image,  final float alphaInt) {
        
        byte alpha = (byte) alphaInt;
        final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
        final BufferedImage newBufferedImage = (BufferedImage) j2seImage.getImage();
        
        alpha %= 0xff;
        final int width = newBufferedImage.getWidth();
        final int height = newBufferedImage.getHeight();

        int color;
        int mask;
        int newcolor;

        for (int index = 0; index < width; index++) {
            for (int index2 = 0; index2 < height; index2++) {
                color = newBufferedImage.getRGB(index, index2);
                mask = (alpha << 24) | 0x00ffffff;
                newcolor = color & mask;
                newBufferedImage.setRGB(index, index2, newcolor);
            }
        }

    }
    
    public Image[] getImageArray(final Image[] originalImageArray, final Image[] imageArray) {
        return imageArray;
    }

    public void handleImage(final Image[] imageArray, final int index, final Image image) {
        
    }
    
}
