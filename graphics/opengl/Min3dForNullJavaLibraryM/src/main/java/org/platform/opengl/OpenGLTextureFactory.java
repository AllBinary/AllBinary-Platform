package org.platform.opengl;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.platform.opengles.PlatformTextureBaseFactory;

public class OpenGLTextureFactory extends PlatformTextureBaseFactory
{
    private static final OpenGLTextureFactory instance = new OpenGLTextureFactory();

    public static OpenGLTextureFactory getInstance()
    {
        return instance;
    }

    private final OpenGLGLUUtils gluUtils = OpenGLGLUUtils.getInstance();
    
    private OpenGLTextureFactory()
    {

    }
    
    public void load(final GL10 gl, final int target, final int level, final Image image, final int border)
    {
        throw new RuntimeException();
    }

}
