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
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.Paintable;

//HTMLForJ2ME
public class AboutPaintable extends Paintable implements UpdateMyFontInterface
{
    
    public static AboutPaintable getInstance(String[] info, String[] developers)
    {
        return new AboutPaintable(info, developers);
    }
    
    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
    
    private final String ABOUT = CanvasStrings.getInstance().ABOUT;
    
    private final String[] info;

    private final String[] developers;

    private final Paintable[] paintableArray =
    {
        this
    };
    
    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);

    private int charHeight;
    private int aboutBeginWidth;
    private int[] infoBeginWidth;
    private int[] developersBeginWidth;
    
    private int anchor = Anchor.TOP_LEFT;
    
    private AboutPaintable(String[] info, String[] developers)
    {
        this.info = info;
        this.infoBeginWidth = new int[this.info.length];
        this.developers = developers;
        this.developersBeginWidth = new int[this.developers.length];
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.charHeight = font.getHeight();
        this.aboutBeginWidth = (font.stringWidth(this.ABOUT) >> 1);
        
        final int infoSize = this.info.length;
        for(int index = 0; index < infoSize; index++)
        {
            this.infoBeginWidth[index] = (font.stringWidth(this.info[index]) >> 1);
        }

        final int size = this.developers.length;
        for(int index = 0; index < size; index++)
        {
            this.developersBeginWidth[index] = (font.stringWidth(this.developers[index]) >> 1);
        }

        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    public Paintable[] getPaintableArrayInstance()
    {
        return this.paintableArray;
    }
    
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);

        final int halfWidth = this.displayInfoSingleton.getLastHalfWidth();
        
        graphics.drawString(this.ABOUT, halfWidth - this.aboutBeginWidth, 2 * this.charHeight, this.anchor);
        
        final int infoSize = this.info.length;
        for(int index = 0; index < infoSize; index++)
        {
            graphics.drawString(this.info[index], halfWidth - this.infoBeginWidth[index], (4 + index) * this.charHeight, this.anchor);
        }
        
        final int size = this.developers.length;
        for(int index = 0; index < size; index++)
        {
            graphics.drawString(this.developers[index], halfWidth - this.developersBeginWidth[index], (5 + infoSize + index) * this.charHeight, this.anchor);
        }

    }
}
