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

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.StringUtil;
import allbinary.graphics.Anchor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;
import allbinary.graphics.paint.Paintable;

public class AboutPaintable extends Paintable
{
    private final String ABOUT = "About";
    
    private final String INFO[] = {
            "This game was developed",
            "using the AllBinary",
            "Game Development Kit.",
            StringUtil.getInstance().EMPTY_STRING,
            //StringUtil.getInstance()
            //"More info at: http://allbinary.no-ip.biz"
            "Comments or Questions:",
            "support@allbinary.com"
            };

    private final static String[] DEVELOPERS = {"Developed By:", "Travis Berthelot"};
    private final String[] developers;

    private final Paintable[] paintableArray =
    {
        this
    };
    
    public static AboutPaintable getInstance()
    {
        return new AboutPaintable(DEVELOPERS);
    }

    public static AboutPaintable getInstance(String[] developers)
    {
        return new AboutPaintable(developers);
    }

    private AboutPaintable(String[] developers)
    {
        this.developers = developers;
    }

    public Paintable[] getPaintableArrayInstance()
    {
        return paintableArray;
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        final int halfWidth = DisplayInfoSingleton.getInstance().getLastHalfWidth();
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        int beginWidth = (graphics.getFont().stringWidth(this.ABOUT) >> 1);
        
        graphics.drawString(this.ABOUT, halfWidth - beginWidth, 2 * charHeight, anchor);
        
        int infoSize = this.INFO.length;
        for(int index = 0; index < infoSize; index++)
        {
            beginWidth = (graphics.getFont().stringWidth(this.INFO[index]) >> 1);
        
            graphics.drawString(this.INFO[index], halfWidth - beginWidth,
                    (4 + index) * charHeight, anchor);
        }
        
        int size = this.developers.length;
        for(int index = 0; index < size; index++)
        {
            beginWidth = (graphics.getFont().stringWidth(this.developers[index]) >> 1);

            graphics.drawString(this.developers[index], halfWidth - beginWidth,
                    (5 + infoSize + index) * charHeight, anchor);
        }
    }
}
