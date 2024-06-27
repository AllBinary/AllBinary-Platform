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

import org.allbinary.logic.string.StringUtil;

public class BasicColorFactory
{
    private static final BasicColorFactory instance = new BasicColorFactory();
    
    public static BasicColorFactory getInstance()
    {
        return instance;
    }

    //C9B895
    public final BasicColor TAN = new BasicColor(0xE9D8B5);
    
    public final BasicColor SKIN_PINK = new BasicColor(0xFDCECE);

    // public final BasicColor BROWN = new BasicColor(0xFFFF00);
    // public final BasicColor LIGHT_BROWN = new BasicColor(0xFFFF00);

    public final BasicColor RED = new BasicColor(0xFF0000, "red");
    public final BasicColor LIGHT_RED = new BasicColor(0xFFA0A0, "light red");

    public final BasicColor DARK_GREEN = new BasicColor(0x00FF00);
    public final BasicColor GREEN = new BasicColor(0x00DD00, "green");
    public final BasicColor LIGHT_GREEN = new BasicColor(0x007700);
    public final BasicColor PURPLE = new BasicColor(0x400040, "purple");
    public final BasicColor BLUE = new BasicColor(0x0000FF, "blue");
    public final BasicColor LIGHT_BLUE = new BasicColor(0x00aaff);
    public final BasicColor VERY_LIGHT_BLUE = new BasicColor(0x000033);
    // public final BasicColor LIGHT_BLUE = new BasicColor(0x000077);
    public final BasicColor PUCE = new BasicColor(0xFF00AA);
    public final BasicColor AQUA = new BasicColor(0x00FFFF);
    public final BasicColor YELLOW = new BasicColor(0xFFFF00, "yellow");
    // MAGENTA
    public final BasicColor PINK = new BasicColor(0xFF00FF);
    public final BasicColor BLACK = new BasicColor(0x000000, "black");
    public final BasicColor GREY = new BasicColor(0xAAAAAA, "grey");
    public final BasicColor LIGHT_GREY = new BasicColor(0xC0C0C0);
    public final BasicColor DARK_GREY = new BasicColor(0x404040);
    // public final BasicColor ORANGE = new BasicColor(0xFFC800);
    public final BasicColor ORANGE = new BasicColor(0xe07718);

    public final BasicColor BROWN = new BasicColor(0x956B00);

    public final BasicColor WHITE = new BasicColor(0xFFFFFF);

    public final BasicColor TRANSPARENT_GREY = new BasicColor(0x52000000, 0xECECEC, StringUtil.getInstance().EMPTY_STRING);
    public final BasicColor TRANSPARENT_BLACK = new BasicColor(0x52000000, 0x000000, StringUtil.getInstance().EMPTY_STRING);
    public final BasicColor TRANSPARENT_RED = new BasicColor(0x52000000, 0xFF0000, StringUtil.getInstance().EMPTY_STRING);
    
    public final BasicColor CLEAR_COLOR = BLACK;
    public final BasicColor BORDER_COLOR = WHITE;
}
