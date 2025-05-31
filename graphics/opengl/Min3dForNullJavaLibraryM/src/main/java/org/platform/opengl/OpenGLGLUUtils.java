package org.platform.opengl;

import java.nio.ByteBuffer;
import javax.microedition.khronos.opengles.GL10;
import org.allbinary.graphics.OpenGLBitmap;
import org.allbinary.image.opengles.OpenGLESImage;

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
    
    public ByteBuffer makeByteBuffer(final OpenGLBitmap bmp, final boolean flip)
    {
        throw new RuntimeException();
    }
    
    public void texImage2D(final GL10 gl, final String glVersion, final int target, final int level, final boolean generateMipMap, final OpenGLESImage openGLESImage, final int border, final boolean flip) {
        throw new RuntimeException();
    }
    
    public void texImage2D(final GL10 gl, final String glVersion, final int target, final int level, final boolean generateMipMap, final OpenGLBitmap bitmap, final int border, final boolean flip) {
        throw new RuntimeException();
    }
}
