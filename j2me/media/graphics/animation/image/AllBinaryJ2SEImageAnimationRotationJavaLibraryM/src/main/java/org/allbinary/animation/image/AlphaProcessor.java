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

import org.allbinary.media.image.ImageModifierUtil;

/**
 *
 * @author User
 */
public class AlphaProcessor extends ModifierBaseProcessor {
    
    private static final AlphaProcessor instance = new AlphaProcessor();

    /**
     * @return the instance
     */
    public static AlphaProcessor getInstance() {
        return instance;
    }
    
    @Override
    public void update(final ImageModifierUtil imageModifierUtil, final Image originalImage, final Image imageToShow, final int index, final int alpha) {
        imageModifierUtil.setAlpha(originalImage, imageToShow, index, alpha);
    }
    
}
