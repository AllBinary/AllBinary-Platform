package org.platform.graphics;

import java.io.IOException;
import java.io.InputStream;


import org.allbinary.graphics.OpenGLBitmap;
import org.allbinary.graphics.color.BasicColor;

/**
 *
 * @author User
 */
public class OpenGLImageUtil {
    
    private static final OpenGLImageUtil instance = new OpenGLImageUtil();
    
    /**
     * @return the instance
     */
    public static OpenGLImageUtil getInstance() {
        return instance;
    }
   
    //private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();
    
    public OpenGLBitmap paint(final OpenGLBitmap alphaBitmap, final OpenGLBitmap originalBitmap, final int index) {
       throw new RuntimeException();
    }
    
    public OpenGLBitmap paint(final OpenGLBitmap colorBitmap, final OpenGLBitmap originalBitmap, final int alpha, final BasicColor basicColor) {
        throw new RuntimeException();
    }

    public OpenGLBitmap createImage(final String name) throws IOException {
        throw new RuntimeException();
        //return null;
    }
    
    public static OpenGLBitmap createImage(final InputStream inputStream) throws IOException {
        throw new RuntimeException();
        //return null;
    }
}
