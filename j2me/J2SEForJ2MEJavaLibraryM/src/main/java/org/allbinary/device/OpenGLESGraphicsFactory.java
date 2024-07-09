/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.allbinary.device;

/**
 *
 * @author user
 */
public class OpenGLESGraphicsFactory
{
    private static final OpenGLESGraphicsFactory instance = new OpenGLESGraphicsFactory();

    /**
     * @return the instance
     */
    public static OpenGLESGraphicsFactory getInstance()
    {
        return instance;
    }
    
    private OpenGLESGraphicsFactory openGLESGraphicsFactory;
    
    public void set(OpenGLESGraphicsFactory openGLESGraphicsFactory)
    {
        this.openGLESGraphicsFactory = openGLESGraphicsFactory;
    }
    
    public OpenGLESGraphics getOpenGLESGraphicsInstance()
    {
        return this.openGLESGraphicsFactory.getOpenGLESGraphicsInstance();
    }
}
