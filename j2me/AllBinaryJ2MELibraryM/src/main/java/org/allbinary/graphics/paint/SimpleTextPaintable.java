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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;

public class SimpleTextPaintable extends Paintable implements UpdateMyFontInterface
{
    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

    private final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    private MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    private String text;
    private BasicColor basicColor;
    private int topScoresWidth = 0;
    private int fontHeight = 0;
    private int anchor = Anchor.TOP_LEFT;
    
    public SimpleTextPaintable(final String text, final BasicColor basicColor)
    {
        this.text = text;
        this.basicColor = basicColor;
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.topScoresWidth = (font.stringWidth(this.text) >> 1);
        this.fontHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
        
    @Override
    public void paint(final Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        
        //int width = graphics.getClipWidth();
        final int width = this.displayInfoSingleton.getLast()[this.displayInfoSingleton.WIDTH];

        graphics.setColor(this.getBasicColorP().intValue());

        graphics.drawString(this.text, (width >> 1) - this.topScoresWidth, this.fontHeight, this.anchor);
    }

    @Override
    public void setBasicColorP(final BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    private BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    public void setText(final String text)
    {
        this.text = text;
        this.myFontProcessor = this.updateMyFontProcessor;
    }

    public String getText()
    {
        return this.text;
    }    
}
