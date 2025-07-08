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
package org.allbinary.game.paint;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class OwnershipPaintable extends Paintable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private final static OwnershipPaintable SINGLETON = new OwnershipPaintable();
    
    private final String COPYRIGHT = "AllBinary Copyright (c) 2011";
    //private final String COPYRIGHT = "Copyright (c) 2011";
    //private final String COMPANY = "AllBinary";
    
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
    
    public void paint(final Graphics graphics)
    {
        //logUtil.put(commonStrings.START, this, canvasStrings.PAINT);
        
        graphics.setColor(color);
        
        final MyFont myFont = MyFont.getInstance();
        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        final int halfWidth = displayInfo.getLastHalfWidth();
        //int height = graphics.getClipHeight();
        final int height = displayInfo.getLastHeight();

        final Font font = graphics.getFont();

        final int beginWidth = (font.stringWidth(COPYRIGHT) >> 1);

        //final int COPYRIGHT_Y = 3 * myFont.DEFAULT_CHAR_HEIGHT;
        final int COPYRIGHT_Y = 2 * myFont.DEFAULT_CHAR_HEIGHT;
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
