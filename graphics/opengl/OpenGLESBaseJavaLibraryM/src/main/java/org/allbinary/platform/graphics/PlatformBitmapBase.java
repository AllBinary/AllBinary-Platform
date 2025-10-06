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
package org.allbinary.platform.graphics;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.NullCanvas;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

/**
 *
 * @author User
 */
public class PlatformBitmapBase {
    
    public static final PlatformBitmapBase NULL_PLATFORM_BITMAP_BASE = new PlatformBitmapBase();
    
    public int alpha = 255;
    public BasicColor basicColor = BasicColorFactory.getInstance().NULL_COLOR;
    
    public Image getImage() {
        return NullCanvas.NULL_IMAGE;
    }
}
