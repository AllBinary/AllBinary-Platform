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
package allbinary.game.paint;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import allbinary.graphics.Anchor;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;
import allbinary.graphics.paint.Paintable;

public class OwnershipPaintable extends Paintable
{
    //private final static OwnershipPaintable SINGLETON = new OwnershipPaintable();

    private final String COPYRIGHT = "AllBinary Copyright (c) 2010";
    //private final String COPYRIGHT = "Copyright (c) 2010";
    //private final String COMPANY = "AllBinary";

    //private int COMPANY_Y = 3 * MyFont.MYFONT.DEFAULT_CHAR_HEIGHT;
    private int COPYRIGHT_Y = 2 * MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
    
    private BasicColor basicColor = BasicColorFactory.getInstance().WHITE;
    private int color = basicColor.intValue();
    
    public static OwnershipPaintable getInstance()
    {
        //return SINGLETON;
        return new OwnershipPaintable();
    }
    
    private OwnershipPaintable()
    {
        
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        graphics.setColor(color);
        
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        int halfWidth = displayInfo.getLastHalfWidth();
        int height = graphics.getClipHeight();
        //displayInfo.getLastHeight()

        Font font = graphics.getFont();

        int beginWidth = (font.stringWidth(COPYRIGHT) >> 1);

        graphics.drawString(COPYRIGHT, halfWidth - beginWidth, height - COPYRIGHT_Y, anchor);

        /*
        beginWidth = (font.stringWidth(COMPANY) >> 1);

        graphics.drawString(COMPANY, halfWidth - beginWidth,
                height - COMPANY_Y, anchor);
        */
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        color = basicColor.intValue();
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }
}
