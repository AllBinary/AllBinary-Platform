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
package allbinary.graphics.color;

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
}
