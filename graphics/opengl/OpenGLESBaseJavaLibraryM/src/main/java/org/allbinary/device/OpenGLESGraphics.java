package org.allbinary.device;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.font.FontDebugBase;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;
import org.allbinary.string.CommonStrings;

public class OpenGLESGraphics 
extends Graphics
implements OpenGLSurfaceChangedInterface, GraphicsInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    protected GL10 gl = NullGL10.NULL_GL10;
    
    public OpenGLESGraphics()
    {
        
    }
    
    public void update(GL gl)
    {
    }

    @Override
    public void set(GL gl)
    {
    }

    public void init() throws Exception
    {
        
    }

    public void update() throws Exception
    {
        
    }

    public void setOrthoginalMode()
    {
        
    }

    public void setCameraMode()
    {
        
    }

    public GL10 getGl10()
    {
        return gl;
    }

    @Override    
    public void setFont(final Font font, final FontDebugBase fontDebug) {
        throw new RuntimeException();
    }
}
