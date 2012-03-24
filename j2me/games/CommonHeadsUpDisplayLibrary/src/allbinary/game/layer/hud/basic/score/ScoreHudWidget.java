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
package allbinary.game.layer.hud.basic.score;

import allbinary.game.layer.hud.basic.NumberStringHud;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;

public class ScoreHudWidget extends NumberStringHud
{
    public ScoreHudWidget(int maxscore, int location, int direction, int maxHeight, int maxWidth,
            int bufferZone, BasicColor basicColor) throws Exception
    {
        super("Pts ", maxscore, location, direction, maxHeight, maxWidth, bufferZone, basicColor);
    }

    public ScoreHudWidget(int maxscore, int location, int direction, int maxWidth) 
        throws Exception
    {
        this(maxscore, location, direction, 14, maxWidth, 2, BasicColorFactory.getInstance().GREY);
    }

    public ScoreHudWidget(int maxscore, int location, int direction) throws Exception
    {
        this(maxscore, location, direction, 14, 40, 2, BasicColorFactory.getInstance().GREY);
    }
}
