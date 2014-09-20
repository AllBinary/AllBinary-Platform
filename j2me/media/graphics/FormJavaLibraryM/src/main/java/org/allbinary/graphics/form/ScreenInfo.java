package org.allbinary.graphics.form;

public class ScreenInfo
{
    private static final ScreenInfo instance = new ScreenInfo();

    public static ScreenInfo getInstance()
    {
        return instance;
    }
    
    public final int SMALL_WIDTH = 320;
    public final int SMALL_HEIGHT = 240;

    public final int MEDIUM_WIDTH = 800;
    public final int MEDIUM_HEIGHT = 600;

}
