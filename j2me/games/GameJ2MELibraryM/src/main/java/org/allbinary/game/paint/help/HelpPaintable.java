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
package org.allbinary.game.paint.help;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;

public class HelpPaintable extends Paintable implements UpdateMyFontInterface
{
    protected final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

    protected final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = this.updateMyFontProcessor;

    protected int anchor = Anchor.TOP_LEFT;
    
    protected final String title;
    protected String[] inputInfo = StringUtil.getInstance().getArrayInstance();

    //protected ColorFillPaintable colorFillPaintable;

    protected BasicColor basicColor;
    //private int color;
    protected int titleBeginWidth;
    private int[] beginWidthArray = NullUtil.getInstance().NULL_INT_ARRAY;
    private int charHeight;

    public HelpPaintable(final String title, final BasicColor backgroundBasicColor, final BasicColor basicColor)
    {
        this.title = title;
    
        //this.colorFillPaintable = new ColorFillPaintable(backgroundBasicColor);
        
        this.basicColor = basicColor;
        //this.color = basicColor.intValue();
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();

        this.titleBeginWidth = (font.stringWidth(this.title) >> 1);
        this.charHeight = font.getHeight();
        
        final int size = this.inputInfo.length;
        for (int index = 0; index < size; index++)
        {
            this.beginWidthArray[index] = (font.stringWidth(this.inputInfo[index]) >> 1);
        }

        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    public void setInputInfoP(final String[] inputInfo)
    {
        this.inputInfo = inputInfo;
        this.beginWidthArray = new int[this.inputInfo.length];
        this.myFontProcessor = this.updateMyFontProcessor;
    }
    
//    public int getHeight()
//    {
//        return font.getHeight() * (this.inputInfo.length + 2);
//    }
    
    @Override
    public void paint(final Graphics graphics)
    {
        this.myFontProcessor.process(graphics);

        final int halfWidth = this.displayInfo.getLastHalfWidth();

        //this.colorFillPaintable.paint(graphics);
        
        graphics.setColor(this.basicColor.intValue());
        
        graphics.drawString(this.title, halfWidth - this.titleBeginWidth, this.charHeight, this.anchor);

        final int size = this.inputInfo.length;
        for (int index = 0; index < size; index++)
        {
            graphics.drawString(this.inputInfo[index], halfWidth - this.beginWidthArray[index], (index + 3) * this.charHeight, this.anchor);
        }
    }
}
