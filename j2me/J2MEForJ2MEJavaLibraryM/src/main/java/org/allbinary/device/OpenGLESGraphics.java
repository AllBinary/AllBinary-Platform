package org.allbinary.device;

import javax.microedition.lcdui.Font;

import org.allbinary.graphics.font.FontDebugBase;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;

public class OpenGLESGraphics 
extends javax.microedition.lcdui.Graphics
//implements OpenGLSurfaceChangedInterface, GraphicsInterface
{
    protected Object gl;
    
    protected OpenGLESGraphics()
    {
        
    }
    
    public void update(Object gl)
    {
    }

    public void set(Object gl)
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

    public Object getGl()
    {
        return gl;
    }
    
    public void setFont(final Font font, final FontDebugBase fontDebug) {
        throw new RuntimeException();
    }
}
