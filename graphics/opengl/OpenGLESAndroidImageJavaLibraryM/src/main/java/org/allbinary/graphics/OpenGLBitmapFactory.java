package org.allbinary.graphics;

import java.io.InputStream;

import javax.microedition.lcdui.Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;

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
        
        public final Bitmap.Config bitmapConfig;

        private Config(final Bitmap.Config bitmapConfig) {
            this.bitmapConfig = bitmapConfig;
        }
    }
    
    public final Config ARGB_8888 = new Config(Bitmap.Config.ARGB_8888);
    
    public OpenGLBitmap createBitmap(int width, int height, OpenGLBitmapFactory.Config config) {
        return new OpenGLBitmap(Bitmap.createBitmap(width, height, config.bitmapConfig));
    }
    
    public OpenGLBitmap decodeStream(InputStream is) throws IOException {
        return new OpenGLBitmap(BitmapFactory.decodeStream(is));
    }

    public PlatformBitmapBase createBitmap(final Image image) {
        return new OpenGLBitmap(image.getBitmap());
    }

}
