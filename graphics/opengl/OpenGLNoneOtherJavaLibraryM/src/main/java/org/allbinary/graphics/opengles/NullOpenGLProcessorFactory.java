package org.allbinary.graphics.opengles;

public class NullOpenGLProcessorFactory
{
    private static final OpenGLProcessor instance = new OpenGLProcessor();

    public static OpenGLProcessor getInstance()
    {
        return instance;
    }
}
