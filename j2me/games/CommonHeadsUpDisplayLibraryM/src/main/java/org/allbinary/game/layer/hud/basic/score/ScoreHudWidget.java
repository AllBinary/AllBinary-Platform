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
package org.allbinary.game.layer.hud.basic.score;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import org.allbinary.game.layer.hud.basic.NumberStringHud;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

public class ScoreHudWidget extends NumberStringHud {

    public static ScoreHudWidget getInstance(final int maxscore, final int location, final int direction) throws Exception {
        return new ScoreHudWidget(maxscore, location, direction, 2, BasicColorFactory.getInstance().GREY);
    }

    public ScoreHudWidget(int maxscore, int location, int direction, int bufferZone, BasicColor basicColor) {
        super("Pts ", maxscore, location, direction, bufferZone, basicColor);
        
        this.updateMaxHeight = 14;
    }

//    public ScoreHudWidget(int maxscore, int location, int direction, int maxWidth)
//        throws Exception
//    {
//        this(maxscore, location, direction, 14, maxWidth, 2, BasicColorFactory.getInstance().GREY);
//    }

    @Override
    public void updateMeasurement(final Graphics graphics) {

        final Font font = graphics.getFont();
        this.updateMaxWidth = font.getSize() * 5;
        
        super.updateMeasurement(graphics);
    }

}
