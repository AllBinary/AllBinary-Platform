package org.allbinary.device;

public class OpenGLESNullGraphicsFactory
{
    private static final OpenGLESGraphics instance = new OpenGLESGraphics();

    public static OpenGLESGraphics getInstance()
    {
        return instance;
    }
}
