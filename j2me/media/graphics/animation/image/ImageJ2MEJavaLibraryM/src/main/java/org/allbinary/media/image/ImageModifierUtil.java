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

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.color.BasicColor;

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
    
    public void setColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {
        
    }

    public void changeColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {
        
    }
    
    public void setAlpha(final Image originalImage, final Image image, final int imageIndex, final int alphaInt) {
    }
    
    public Image[] getImageArray(final Image[] originalImageArray) {
        return originalImageArray;
    }
    
    public void handleImage(final Image[] imageArray, final int index, final Image image) {
        
    }
    
    public void reset() {
        
    }
    
}
