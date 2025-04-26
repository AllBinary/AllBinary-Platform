package org.allbinary.graphics.opengles;

import org.allbinary.graphics.opengles.OpenGLProcessor;

public class NullOpenGLProcessorFactory
{
    private static final OpenGLProcessor instance = new OpenGLProcessor();

    public static OpenGLProcessor getInstance()
    {
        return instance;
    }
}
