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
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.Paintable;

public class FullScreenPaintable extends Paintable
{
    private final String FULLSCREEN_TEXT = "F11 - Toggle Fullscreen";
    
    public static FullScreenPaintable getInstance()
    {
        return new FullScreenPaintable();
    }

    private FullScreenPaintable()
    {
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        final int halfWidth = displayInfo.getLastHalfWidth();
        //int height = graphics.getClipHeight();
        final int height = displayInfo.getLastHeight();        

        //final int charHeight = myFont.DEFAULT_CHAR_HEIGHT;
        final Font font = graphics.getFont();

        final int beginWidth = (font.stringWidth(this.FULLSCREEN_TEXT) >> 1);

        final MyFont myFont = MyFont.getInstance();
        final int Y = 4 * myFont.DEFAULT_CHAR_HEIGHT;
        graphics.drawString(this.FULLSCREEN_TEXT, halfWidth - beginWidth, height - Y, anchor);
    }

}
