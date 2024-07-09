package org.allbinary.device;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Font;

import org.allbinary.graphics.font.FontDebugBase;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;

public class OpenGLESGraphics 
extends javax.microedition.lcdui.Graphics
implements OpenGLSurfaceChangedInterface, GraphicsInterface
{
    protected GL10 gl;
    
    protected OpenGLESGraphics()
    {
        
    }
    
    public void update(GL gl)
    {
    }

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

    public GL10 getGl()
    {
        return gl;
    }
    
    public void setFont(final Font font, final FontDebugBase fontDebug) {
        throw new RuntimeException();
    }
}
