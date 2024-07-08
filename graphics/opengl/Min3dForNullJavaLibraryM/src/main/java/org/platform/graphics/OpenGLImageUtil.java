package org.platform.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.OpenGLBitmap;
import org.allbinary.media.image.AndroidImageUtil;
import org.allbinary.media.image.ImageModifierUtil;

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
   
    private final AndroidImageUtil androidImageUtil = AndroidImageUtil.getInstance();
    private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();
    
    public void paint(final OpenGLBitmap alphaBitmap, final OpenGLBitmap originalBitmap, final int index) {
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
