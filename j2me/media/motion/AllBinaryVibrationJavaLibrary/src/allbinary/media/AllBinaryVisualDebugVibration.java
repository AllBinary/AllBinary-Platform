/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/29/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.media;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.Anchor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;
import allbinary.time.TimeDelayHelper;

public class AllBinaryVisualDebugVibration extends AllBinaryVibrationME
{
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(
            Integer.MAX_VALUE);

    public void vibrate(int duration, int type, int volume)
    {
        timeDelayHelper.setDelay(duration);
    }

    private final String VIBRATING = "Vibrating";

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        if (timeDelayHelper.isTime())
        {
            this.timeDelayHelper.setDelay(Integer.MAX_VALUE);
            
            DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton
                    .getInstance();
            
            int width = MyFont.getInstance().DEFAULT_CHAR_WIDTH * VIBRATING.length();

            graphics.drawString(VIBRATING, 
                    displayInfoSingleton.getLastHalfWidth() - (width >> 1), 0, anchor);
        }
    }
}
