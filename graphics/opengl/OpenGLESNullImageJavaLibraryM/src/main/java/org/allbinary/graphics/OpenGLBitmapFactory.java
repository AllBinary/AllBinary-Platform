package org.allbinary.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Image;

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
        return instance;
    }

    public class Config {
        
        public final Object bitmapConfig;

        private Config(final Object bitmapConfig) {
            this.bitmapConfig = bitmapConfig;
        }
    }
    
    public final Config ARGB_8888 = new Config(null);
    
    public OpenGLBitmap createBitmap(int width, int height, OpenGLBitmapFactory.Config config) {
        return new OpenGLBitmap(null);
    }
    
    public OpenGLBitmap decodeStream(InputStream is) throws IOException {
        return new OpenGLBitmap(null);
    }

    public PlatformBitmapBase createBitmap(final Image image) {
        return new OpenGLBitmap(null);
    }

}
