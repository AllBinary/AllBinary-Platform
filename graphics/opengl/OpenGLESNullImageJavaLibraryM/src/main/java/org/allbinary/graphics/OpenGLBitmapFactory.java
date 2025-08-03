package org.allbinary.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Image;
import org.allbinary.logic.NullUtil;

import org.allbinary.platform.graphics.PlatformBitmapBase;
import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;

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

        public Config(final Object bitmapConfig) {
            this.bitmapConfig = bitmapConfig;
        }
    }
    
    public final Config ARGB_8888 = new Config(NullUtil.getInstance().NULL_OBJECT);
    
    public OpenGLBitmap createBitmap(int width, int height, OpenGLBitmapFactory.Config config) {
        return new OpenGLBitmap(NullUtil.getInstance().NULL_OBJECT);
    }
    
    public OpenGLBitmap decodeStream(InputStream inputStream) throws IOException {
        return new OpenGLBitmap(NullUtil.getInstance().NULL_OBJECT);
    }

    @Override
    public PlatformBitmapBase createBitmap(final Image image) {
        return new OpenGLBitmap(NullUtil.getInstance().NULL_OBJECT);
    }

}
