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
package org.allbinary.graphics.paint;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;

public class SimpleTextPaintable extends Paintable
{
    private String text;
    private BasicColor basicColor;
    
    public SimpleTextPaintable(final String text, final BasicColor basicColor)
    {
        this.text = text;

        this.basicColor = basicColor;
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    private final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
    @Override
    public void paint(final Graphics graphics)
    {
        final MyFont myFont = MyFont.getInstance();
        
        //int width = graphics.getClipWidth();
        final int width = this.displayInfoSingleton.getLast()[this.displayInfoSingleton.WIDTH];
        
        final int topScoresWidth = (graphics.getFont().stringWidth(this.text) >> 1);

        graphics.setColor(this.getBasicColorP().intValue());
        
        graphics.drawString(this.text, (width >> 1) - topScoresWidth, myFont.DEFAULT_CHAR_HEIGHT * 3, anchor);    
    }

    @Override
    public void setBasicColorP(final BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    private BasicColor getBasicColorP()
    {
        return basicColor;
    }

    public void setText(final String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }    
}
