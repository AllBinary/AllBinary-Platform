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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.InitUpdatePaintable;

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
    
    @Override
    public void init()
    {
        
    }

    @Override
    public void update()
    {
        //baseRefreshHelperStringArray = GameStatisticsFactory.getInstance().toStringArray();
        baseRefreshHelperCharArray = GameStatisticsFactory.getInstance().toCharArray();
    }

    private final int RED = BasicColorFactory.getInstance().RED.intValue();
    
    @Override
    public void paint(Graphics graphics)
    {
        final MyFont myFont = MyFont.getInstance();
        
        graphics.setColor(RED);
        
        char[] charArray;
        char[] charArray2;
        int size2;
        int size3;
        final int size = baseRefreshHelperCharArray.length - 2;
        for(int index = size; index >= 0; index-=2)
        {
            charArray = baseRefreshHelperCharArray[index];
            charArray2 = baseRefreshHelperCharArray[index + 1];
            size2 = charArray.length;
            size3 = charArray2.length;

            graphics.drawChars(charArray, 0, size2, 0, yArray[index], 0);
            graphics.drawChars(charArray2, 0, size3, size2 * myFont.stringWidth(2), yArray[index + 1], 0);
        }
    }
}
