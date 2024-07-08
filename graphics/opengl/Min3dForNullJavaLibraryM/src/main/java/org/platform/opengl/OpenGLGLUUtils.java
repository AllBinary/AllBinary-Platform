package org.platform.opengl;

import org.allbinary.graphics.OpenGLBitmap;
import org.allbinary.platform.graphics.PlatformBitmapBase;

/**
 *
 * @author User
 */
public class OpenGLGLUUtils {
    
    private static final OpenGLGLUUtils instance = new OpenGLGLUUtils();

    /**
     * @return the instance
     */
    public static OpenGLGLUUtils getInstance() {
        return instance;
    }
    
    public void texImage2D(final int target, final int level, final PlatformBitmapBase bitmap, final int border) {
        throw new RuntimeException();
    }
    
}
