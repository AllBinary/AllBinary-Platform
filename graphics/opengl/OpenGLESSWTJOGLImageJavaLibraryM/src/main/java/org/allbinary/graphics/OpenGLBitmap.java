package org.allbinary.graphics;

import javax.microedition.lcdui.Image;
import org.allbinary.DisposalUtil;
import org.allbinary.platform.graphics.PlatformBitmapBase;
import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

/**
 *
 * @author User
 */
public class OpenGLBitmap extends PlatformBitmapBase {

    public final Image image;
    
    public OpenGLBitmap(final Object bitmap) {
        this.image = (Image) bitmap;
    }
    
    public void getPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        if(this.image.isMutable()) {
            final SwtMutableImage mutableImage = (SwtMutableImage) this.image;
            mutableImage.getRGB(pixels, offset, stride, x, y, width, height);
        } else {
            final SwtImmutableImage immutableImage = (SwtImmutableImage) this.image;
            immutableImage.getRGB(pixels, offset, stride, x, y, width, height);
        }
    }

    public void setPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        if(this.image.isMutable()) {
            final SwtMutableImage mutableImage = (SwtMutableImage) this.image;
            mutableImage.setRGB(pixels, offset, stride, x, y, width, height);
        } else {
            final SwtImmutableImage immutableImage = (SwtImmutableImage) this.image;
            immutableImage.setRGB(pixels, offset, stride, x, y, width, height);
        }
    }
    
    public int getWidth() {
        return this.image.getWidth();    
    }

    public int getHeight() {
        return this.image.getHeight();
    }
    
    public void recycle() {
        DisposalUtil.getInstance().dispose(image);
    }
}
