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
package org.allbinary.game.layer.hud.basic.time;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.logic.math.PrimitiveLongSingleton;

public class TimeHudWidget extends BasicHud
{
    public static TimeHudWidget getInstance(int location, int direction, BasicColor basicColor, Timer timer)
            throws Exception
    {
        return new TimeHudWidget(location, direction, 2, basicColor, timer);
    }

    private final String TIME_STRING = "Time ";
    private final char[] TIME_CHAR_ARRAY = { 'T', 'i', 'm', 'e', ' ' };

    private int offset;

    // private String string = StringUtil.getInstance();
    private char[] string = PrimitiveLongSingleton.getInstance().ZERO;
    private int totalDigits = 1;

    private final Timer timer;

    public TimeHudWidget(final int location, final int direction, final int bufferZone, final BasicColor basicColor, final Timer timer)
    {
        super(location, direction, bufferZone, basicColor);

        this.timer = timer;

        this.set();
        
        this.updateMaxHeight = 14;

//        if (direction == 0)
//        {
//            throw new Exception(BasicHudFactory.getInstance().DIRECTION_EXCEPTION);
//        }
    }

//    public TimeHudWidget(int location, int direction, int maxWidth, BasicColor basicColor,
//            Timer timer) throws Exception
//    {
//        this(location, direction, 14, maxWidth, 2, basicColor, timer);
//    }

    public void updateMeasurement(final Graphics graphics) {
        
        final Font font = graphics.getFont();
        this.updateMaxWidth = font.getSize() * 5;
        this.offset = font.stringWidth(this.TIME_STRING) + MyFontProcessor.defaultStringWidth(font, 3);
        
        super.updateMeasurement(graphics);
    }
    
    public void update()
    {
        this.timer.update();
        this.set();
    }

    private void set()
    {
        // this.string = trackTimer;
        this.string = this.timer.getTimeChars();

        this.totalDigits = this.timer.getCurrentTotalDigits();
    }

    /*
    public void setTrackTimer(Timer trackTimer)
    {
        this.trackTimer = trackTimer;
    }
    */

    public Timer getTimer()
    {
        return this.timer;
    }

    public void paint(Graphics graphics)
    {
        super.paintDX(graphics, this.TIME_CHAR_ARRAY, 0, this.TIME_CHAR_ARRAY.length, this.string, 0, this.totalDigits, this.offset);
        // super.paint(graphics, TIME_STRING, string, offset);
    }

}
