package org.allbinary.android.device;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;

public class OpenGLESGraphics 
extends javax.microedition.lcdui.Graphics
implements OpenGLSurfaceChangedInterface
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
}
