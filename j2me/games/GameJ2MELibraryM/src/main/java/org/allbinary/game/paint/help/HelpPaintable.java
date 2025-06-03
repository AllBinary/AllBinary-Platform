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

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.Paintable;

public class HelpPaintable extends Paintable
{
    private final MyFont myFont = MyFont.getInstance();
    
    protected String TITLE = "Help Screen";
    protected String[] inputInfo = StringUtil.getInstance().getArrayInstance();

    //protected ColorFillPaintable colorFillPaintable;
    
    protected BasicColor basicColor;
    //private int color;

    public HelpPaintable(final String title, final BasicColor backgroundBasicColor, final BasicColor basicColor)
    {
        this.TITLE = title;
    
        //this.colorFillPaintable = new ColorFillPaintable(backgroundBasicColor);
        
        this.basicColor = basicColor;
        //this.color = basicColor.intValue();
    }
    
    public HelpPaintable(final BasicColor basicColor)
    {
        this.basicColor = basicColor;
        //this.color = basicColor.intValue();
    }
    
    public void setInputInfo(final String[] inputInfo)
    {
        this.inputInfo = inputInfo;
    }
    
    public int getHeight()
    {
        return this.myFont.DEFAULT_CHAR_HEIGHT * (this.inputInfo.length + 2);
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(final Graphics graphics)
    {
        final int halfWidth = DisplayInfoSingleton.getInstance().getLastHalfWidth();
        int beginWidth = (graphics.getFont().stringWidth(this.TITLE) >> 1);

        //this.colorFillPaintable.paint(graphics);
        
        graphics.setColor(this.basicColor.intValue());
        
        final int charHeight = this.myFont.DEFAULT_CHAR_HEIGHT;
        graphics.drawString(this.TITLE, halfWidth - beginWidth, charHeight, anchor);

        final int size = inputInfo.length;
        for (int index = 0; index < size; index++)
        {
            beginWidth = (graphics.getFont().stringWidth(inputInfo[index]) >> 1);

            graphics.drawString(inputInfo[index], halfWidth - beginWidth, (index + 3) * charHeight, anchor);
        }
    }
}
