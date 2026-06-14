/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.image.opengles;

import java.io.IOException;
import java.io.InputStream;
import org.allbinary.graphics.OpenGLBitmap;
import org.allbinary.graphics.color.BasicColor;

/**
 *
 * @author User
 */
public class OpenGLImageUtilBase {
 
    public OpenGLBitmap paint(final OpenGLBitmap alphaBitmap, final OpenGLBitmap originalBitmap, final int index) {
        throw new RuntimeException();
    }

    public OpenGLBitmap paint(final OpenGLBitmap colorBitmap, final OpenGLBitmap originalBitmap, final int alpha, final BasicColor basicColor) {
        throw new RuntimeException();
    }

    public OpenGLBitmap createImage(final String name) throws IOException {
        throw new RuntimeException();
    }
    
    public OpenGLBitmap createImageFromInputStream(final InputStream inputStream) throws IOException {
        throw new RuntimeException();
    }
    
}
