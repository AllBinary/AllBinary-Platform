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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.time.TimeDelayHelper;

public class AllBinaryVisualDebugVibration extends AllBinaryVibrationME
{
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(Integer.MAX_VALUE);

    public void vibrate(final int duration, final int type, final int volume)
    {
        this.timeDelayHelper.delay = duration;
    }

    private final String VIBRATING = "Vibrating";

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        if (timeDelayHelper.isTime())
        {
            this.timeDelayHelper.delay = Integer.MAX_VALUE;
            
            final DisplayInfoSingleton displayInfoSingleton = 
                    DisplayInfoSingleton.getInstance();
            
            final MyFont myFont = MyFont.getInstance();
            final int width = myFont.stringWidth(VIBRATING);

            graphics.drawString(VIBRATING, 
                    displayInfoSingleton.getLastHalfWidth() - (width >> 1), 0, anchor);
        }
    }
}
