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
package org.allbinary.media;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.time.TimeDelayHelper;

public class AllBinaryVisualDebugVibration extends AllBinaryVibrationME
     implements UpdateMyFontInterface
{
    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(Integer.MAX_VALUE);

    private final String VIBRATING = "Vibrating";

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
     
    private int anchor = Anchor.TOP_LEFT;
    private int width;

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.width = font.stringWidth(this.VIBRATING);
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void vibrate(final int duration, final int type, final int volume)
    {
        this.timeDelayHelper.delay = duration;
    }
    
    public void paint(Graphics graphics)
    {
        if (this.timeDelayHelper.isTimeTNT())
        {
            this.timeDelayHelper.delay = Integer.MAX_VALUE;

            this.myFontProcessor.process(graphics);
            graphics.drawString(this.VIBRATING, this.displayInfoSingleton.getLastHalfWidth() - (width >> 1), 0, this.anchor);
        }
    }
}
