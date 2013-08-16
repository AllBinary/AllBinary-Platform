package org.allbinary.image.opengles;

public class OpenGLStrings
{
    private static final OpenGLStrings instance = new OpenGLStrings();

    public static OpenGLStrings getInstance()
    {
        return instance;
    }
    
    public final String GL_ERROR_LABEL = "GLError: ";
    public final String SET = "set(GL10)";
}
