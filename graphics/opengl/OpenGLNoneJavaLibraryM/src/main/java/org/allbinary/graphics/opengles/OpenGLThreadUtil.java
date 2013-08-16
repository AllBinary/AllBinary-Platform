package org.allbinary.graphics.opengles;

public class OpenGLThreadUtil
{
    private static final OpenGLThreadUtil instance = new OpenGLThreadUtil();

    public static OpenGLThreadUtil getInstance()
    {
        return instance;
    }
            
    public void onPause()
    {
    }

    public void onResume()
    {
    }
}
