package org.allbinary.graphics;

import org.allbinary.platform.graphics.PlatformBitmapBase;

/**
 *
 * @author User
 */
public class OpenGLBitmap extends PlatformBitmapBase {
        
    public final Object bitmap;
    
    public OpenGLBitmap(final Object bitmap) {
        this.bitmap = bitmap;
    }
    
    public void getPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
    }

    public void setPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
    }
    
    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }
    
    public void recycle() {
    }
}
