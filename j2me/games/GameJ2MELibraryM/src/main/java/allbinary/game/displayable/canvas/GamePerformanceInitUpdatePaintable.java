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
package allbinary.game.displayable.canvas;

import allbinary.canvas.GameStatisticsFactory;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.font.MyFont;
import allbinary.graphics.paint.InitUpdatePaintable;
import javax.microedition.lcdui.Graphics;

public class GamePerformanceInitUpdatePaintable extends InitUpdatePaintable
{
    //private final String TEXT = "Implement Your Own GameCanvas Draw Method";

    //private String[] baseRefreshHelperStringArray = StringUtil.getArrayInstance();
    
    private final int halfHeight = DisplayInfoSingleton.getInstance().getLastHalfHeight();

    private final int[] yArray = {
            halfHeight + 30, 
            halfHeight + 30, 
            halfHeight + 30 + 15,
            halfHeight + 30 + 15,
            halfHeight + 30 + 30,
            halfHeight + 30 + 30,
            halfHeight + 30 + 45,
            halfHeight + 30 + 45
            };
    
    //private String[] baseRefreshHelperStringArray = StringUtil.getArrayInstance();

    private char[][] baseRefreshHelperCharArray = new char[0][0];
    
    public void init()
    {
        
    }

    public void update()
    {
        //baseRefreshHelperStringArray = GameStatisticsFactory.getInstance().toStringArray();
        baseRefreshHelperCharArray = GameStatisticsFactory.getInstance().toCharArray();
    }

    private final int RED = BasicColorFactory.getInstance().RED.intValue();
    
    public void paint(Graphics graphics)
    {
        graphics.setColor(RED);
        
        for(int index = baseRefreshHelperCharArray.length - 2; index >= 0; index-=2)
        {
            graphics.drawChars(baseRefreshHelperCharArray[index], 0, 
                    baseRefreshHelperCharArray[index].length, 0, yArray[index], 0);
            
            graphics.drawChars(baseRefreshHelperCharArray[index + 1], 0, 
                    baseRefreshHelperCharArray[index + 1].length, (baseRefreshHelperCharArray[index].length * MyFont.getInstance().DEFAULT_CHAR_WIDTH * 2), yArray[index + 1], 0);
        }
    }
}
