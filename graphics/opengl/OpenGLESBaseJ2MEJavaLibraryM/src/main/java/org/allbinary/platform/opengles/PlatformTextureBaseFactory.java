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
package org.allbinary.platform.opengles;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class PlatformTextureBaseFactory {
   
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public void load(final GL10 gl, final int target, final int level, final Image image, final int border, final boolean flip) {
        
    }

}
