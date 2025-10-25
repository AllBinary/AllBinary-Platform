package org.allbinary.graphics;

import android.graphics.Bitmap;

import org.allbinary.platform.graphics.PlatformBitmapBase;

/**
 *
 * @author User
 */
public class OpenGLBitmap extends PlatformBitmapBase {
        
    public final Bitmap bitmap;
    
    public OpenGLBitmap(final Object bitmap) {
        this.bitmap = (Bitmap) bitmap;
    }
    
    public void getPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        this.bitmap.getPixels(pixels, offset, stride, x, y, width, height);
    }

    public void setPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        this.bitmap.setPixels(pixels, offset, stride, x, y, width, height);
    }
    
    @Override
    public int getWidth() {
        return this.bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return this.bitmap.getHeight();
    }
    
    public void recycle() {
        this.bitmap.recycle();
    }
}
