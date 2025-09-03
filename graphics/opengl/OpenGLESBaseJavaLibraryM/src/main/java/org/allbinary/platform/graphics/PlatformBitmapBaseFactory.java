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

/**
 *
 * @author User
 */
public class PlatformBitmapBaseFactory {
    
    public static final PlatformBitmapBaseFactory NULL_PLATFORM_BITMAP_BASE_FACTORY = new PlatformBitmapBaseFactory();
    
    public PlatformBitmapBase createBitmap(final Image image) {
        return PlatformBitmapBase.NULL_PLATFORM_BITMAP_BASE;
    }

}
