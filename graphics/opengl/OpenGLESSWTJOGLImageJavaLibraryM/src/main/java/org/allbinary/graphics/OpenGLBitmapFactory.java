package org.allbinary.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Image;
import org.allbinary.image.ImageFactory;

import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.graphics.PlatformBitmapBase;

/**
 *
 * @author User
 */
public class OpenGLBitmapFactory extends PlatformBitmapBaseFactory {
    
    private static final OpenGLBitmapFactory instance = new OpenGLBitmapFactory();

    /**
     * @return the instance
     */
    public static OpenGLBitmapFactory getInstance() {
        return OpenGLBitmapFactory.instance;
    }

    public final OpenGLBitmapConfig ARGB_8888 = new OpenGLBitmapConfig(null);
    
    public OpenGLBitmap createBitmap(int width, int height, OpenGLBitmapConfig config) {
        //return new OpenGLBitmap(Image.createRGBImage(new int[width * height], width, height, true));
        return new OpenGLBitmap(Image.createImage(width, height));
    }
    
    public OpenGLBitmap decodeStream(InputStream inputStream) throws Exception {
        return new OpenGLBitmap(ImageFactory.getInstance().createImageFromInputStream(inputStream));
    }

    public PlatformBitmapBase createBitmap(final Image image) {
        return new OpenGLBitmap(image);
    }

}
