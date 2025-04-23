package org.allbinary.graphics.opengles;

public class NullOpenGLObjectProcessorFactory
{
    private static final OpenGLObjectProcessor instance = new OpenGLObjectProcessor();

    public static OpenGLObjectProcessor getInstance()
    {
        return instance;
    }
}
