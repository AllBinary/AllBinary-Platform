/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.graphics.color;

public class BasicColorUtil
{
    private static final BasicColorUtil instance = new BasicColorUtil();

    public static BasicColorUtil getInstance()
    {
        return instance;
    }
    
    public final BasicColor[] ZERO_ARRAY = new BasicColor[0];
    public final int ALPHA = 0xFF000000;
    public final boolean isAlpha = true;
    public final boolean ffOpaque = true;
    
    public int get(int red, int green, int blue)
    {
        int value;

        if (isAlpha)
        {
            if (ffOpaque)
            {
                value = ALPHA;
            }
        }
        
        value = (red << 16) | value;
        value = (green << 8) | value;
        value = blue | value;
        
        return value;
    }
    
    public int get(final int alphaValue, final int r, final int g, final int b)
    {
        return ((alphaValue << 24) & 0xFF000000) + ((r << 16) & 0x00FF0000) + ((g << 8) & 0x0000FF00) + (b & 0x000000FF);
    }
    
}
