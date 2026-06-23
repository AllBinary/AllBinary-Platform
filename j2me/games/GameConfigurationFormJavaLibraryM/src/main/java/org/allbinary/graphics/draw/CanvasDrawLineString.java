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
package org.allbinary.graphics.draw;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;

public class CanvasDrawLineString implements UpdateMyFontInterface {

    public static final CanvasDrawLineString NULL_CANVAS_DRAW_LINE_STRING = new CanvasDrawLineString(0, 0);

    private final DrawStringUtil drawStringUtil = DrawStringUtil.getInstance();

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    private int x;
    private int y;
    private int fontHeight = 0;

    public CanvasDrawLineString(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }

    public void paint(final Graphics graphics, final String string, final int line) {
        
        this.myFontProcessor.process(graphics);

        //Font font = graphics.getFont();
        //graphics.setFont(Font.getFont(font.getFace(), font.getStyle(), Font.SIZE_LARGE));
        this.drawStringUtil.drawCenterString(graphics, string, 0, string.length(), this.x, this.y + (line * this.fontHeight));
        //graphics.setFont(font);
    }
}
