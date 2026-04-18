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

    public BasicColor createInstance(final int value) {
        return new BasicColor(BasicColorUtil.getInstance().ALPHA, value, StringUtil.getInstance().EMPTY_STRING);
    }

    public BasicColor createInstanceA(final int alphaValue, final int value) {
        return new BasicColor(alphaValue, value, StringUtil.getInstance().EMPTY_STRING);
    }

    public BasicColor createInstanceAN(final int alphaValue, final int value, final String name) {
        return new BasicColor(alphaValue, value, name);
    }

    public BasicColor createInstanceARGB(final int alphaValue, final int r, final int g, final int b, final String name) {
        final int ALPHA_MASK = (int) 0xFF000000;
        return new BasicColor(alphaValue, ((alphaValue << 24) & ALPHA_MASK) + ((r << 16) & 0x00FF0000) + ((g << 8) & 0x0000FF00) + (b & 0x000000FF), StringUtil.getInstance().EMPTY_STRING);
    }


    public final BasicColor NULL_COLOR = this.createInstanceARGB(255, 255, 255, 255, "null color");

    //C9B895
    public final BasicColor TAN = this.createInstance(0xE9D8B5);

    public final BasicColor SKIN_PINK = this.createInstance(0xFDCECE);

    // public final BasicColor BROWN = BasicColorFactory.getInstance().createInstance(0xFFFF00);
    // public final BasicColor LIGHT_BROWN = BasicColorFactory.getInstance().createInstance(0xFFFF00);

    public final BasicColor RED = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0xFF0000, "red");
    public final BasicColor LIGHT_RED = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0xFFA0A0, "light red");

    public final BasicColor DARK_GREEN = this.createInstance(0x00FF00);
    public final BasicColor GREEN = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0x00DD00, "green");
    public final BasicColor LIGHT_GREEN = this.createInstance(0x007700);
    public final BasicColor PURPLE = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0x400040, "purple");
    public final BasicColor BLUE = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0x0000FF, "blue");
    public final BasicColor LIGHT_BLUE = this.createInstance(0x00aaff);
    public final BasicColor VERY_LIGHT_BLUE = this.createInstance(0x000033);
    // public final BasicColor LIGHT_BLUE = BasicColorFactory.getInstance().createInstance(0x000077);
    public final BasicColor PUCE = this.createInstance(0xFF00AA);
    public final BasicColor AQUA = this.createInstance(0x00FFFF);
    public final BasicColor YELLOW = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0xFFFF00, "yellow");
    // MAGENTA
    public final BasicColor PINK = this.createInstance(0xFF00FF);
    public final BasicColor BLACK = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0x000000, "black");
    public final BasicColor GREY = this.createInstanceAN(BasicColorUtil.getInstance().ALPHA, 0xAAAAAA, "grey");
    public final BasicColor LIGHT_GREY = this.createInstance(0xC0C0C0);
    public final BasicColor DARK_GREY = this.createInstance(0x404040);
    // public final BasicColor ORANGE = BasicColorFactory.getInstance().createInstance(0xFFC800);
    public final BasicColor ORANGE = this.createInstance(0xe07718);

    public final BasicColor BROWN = this.createInstance(0x956B00);

    public final BasicColor WHITE = this.createInstance(0xFFFFFF);

    public final BasicColor TRANSPARENT_COLOR = this.createInstanceAN(0x00000000, 0x000000, StringUtil.getInstance().EMPTY_STRING);
    public final BasicColor TRANSPARENT_GREY = this.createInstanceAN(0x52000000, 0xECECEC, StringUtil.getInstance().EMPTY_STRING);
    public final BasicColor TRANSPARENT_BLACK = this.createInstanceAN(0x52000000, 0x000000, StringUtil.getInstance().EMPTY_STRING);
    public final BasicColor TRANSPARENT_RED = this.createInstanceAN(0x52000000, 0xFF0000, StringUtil.getInstance().EMPTY_STRING);

    public final BasicColor CLEAR_COLOR = BLACK;
    public final BasicColor BORDER_COLOR = WHITE;
    
}
