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

import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.Paintable;

public class HelpPaintable extends Paintable
{
    protected String TITLE = "Help Screen";
    private String[] inputInfo = StringUtil.getInstance().getArrayInstance();

    //protected ColorFillPaintable colorFillPaintable;
    
    protected BasicColor basicColor;
    //private int color;

    public HelpPaintable(String title, BasicColor backgroundBasicColor, BasicColor basicColor)
    {
        this.TITLE = title;
    
        //this.colorFillPaintable = new ColorFillPaintable(backgroundBasicColor);
        
        this.basicColor = basicColor;
        //this.color = basicColor.intValue();
    }
    
    public HelpPaintable(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        //this.color = basicColor.intValue();
    }

    public String[] getInputInfo()
    {
        return inputInfo;
    }
    
    public void setInputInfo(String[] inputInfo)
    {
        this.inputInfo = inputInfo;
    }
    
    public int getHeight()
    {
        return MyFont.getInstance().DEFAULT_CHAR_HEIGHT * (this.getInputInfo().length + 2);
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        int halfWidth = DisplayInfoSingleton.getInstance().getLastHalfWidth();

        int beginWidth = (graphics.getFont().stringWidth(this.TITLE) >> 1);

        //this.colorFillPaintable.paint(graphics);
        
        graphics.setColor(this.basicColor.intValue());
        
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        graphics.drawString(this.TITLE, halfWidth - beginWidth, charHeight, anchor);

        for (int index = 0; index < getInputInfo().length; index++)
        {
            beginWidth = (graphics.getFont().stringWidth(getInputInfo()[index]) >> 1);

            graphics.drawString(getInputInfo()[index],
                    halfWidth - beginWidth, (index + 3) * charHeight, anchor);
        }
    }
}
