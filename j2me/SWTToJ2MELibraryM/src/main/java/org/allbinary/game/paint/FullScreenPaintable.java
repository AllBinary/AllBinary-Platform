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
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.Paintable;

//SwtToJ2ME
public class FullScreenPaintable extends Paintable implements UpdateMyFontInterface
{
    
    public static FullScreenPaintable getInstance()
    {
        return new FullScreenPaintable();
    }

    private final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    private final String FULLSCREEN_TEXT = "F11 - Toggle Fullscreen";
    
    private int anchor = Anchor.TOP_LEFT;
    private int Y;
    private int beginWidth;

    private FullScreenPaintable()
    {
    }

    @Override    
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.Y = 4 * font.getHeight();
        this.beginWidth = (font.stringWidth(this.FULLSCREEN_TEXT) >> 1);
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);

        final int halfWidth = this.displayInfo.getLastHalfWidth();
        //int height = graphics.getClipHeight();
        final int height = this.displayInfo.getLastHeight();

        graphics.drawString(this.FULLSCREEN_TEXT, halfWidth - this.beginWidth, height - this.Y, this.anchor);
    }

}
