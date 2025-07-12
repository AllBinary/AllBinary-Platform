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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.media.image.ImageModifierUtil;

/**
 *
 * @author User
 */
public class SetColorProcessor extends ModifierBaseProcessor {
    
    private static final SetColorProcessor instance = new SetColorProcessor();

    /**
     * @return the instance
     */
    public static SetColorProcessor getInstance() {
        return instance;
    }
    
    @Override
    public void update(final ImageModifierUtil imageModifierUtil, final Image originalImage, final Image imageToShow, final int index, final BasicColor basicColor) {
        imageModifierUtil.setColor(originalImage, imageToShow, index, basicColor);
    }
    
}
