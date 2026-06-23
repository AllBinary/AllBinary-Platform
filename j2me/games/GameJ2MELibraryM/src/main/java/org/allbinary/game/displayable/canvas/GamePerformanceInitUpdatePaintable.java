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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.InitUpdatePaintable;

public class GamePerformanceInitUpdatePaintable extends InitUpdatePaintable implements UpdateMyFontInterface
{    
    //private final String TEXT = "Implement Your Own GameCanvas Draw Method";

    //private String[] baseRefreshHelperStringArray = StringUtil.getArrayInstance();
    
    private final int halfHeight = DisplayInfoSingleton.getInstance().getLastHalfHeight();

    private final int[] yArray = {
            this.halfHeight + 30, 
            this.halfHeight + 30, 
            this.halfHeight + 30 + 15,
            this.halfHeight + 30 + 15,
            this.halfHeight + 30 + 30,
            this.halfHeight + 30 + 30,
            this.halfHeight + 30 + 45,
            this.halfHeight + 30 + 45
            };

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    //private String[] baseRefreshHelperStringArray = StringUtil.getArrayInstance();

    private char[][] baseRefreshHelperCharArray = new char[0][0];

    private int defaultStringWidth;
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.defaultStringWidth = MyFontProcessor.defaultStringWidth(font, 2);
        this.myFontProcessor = MyFontProcessor.getInstance();
    }

    @Override
    public void init()
    {
        
    }

    @Override
    public void update()
    {
        //baseRefreshHelperStringArray = GameStatisticsFactory.getInstance().toStringArray();
        this.baseRefreshHelperCharArray = GameStatisticsFactory.getInstance().to2DCharArray();
    }

    private final int RED = BasicColorFactory.getInstance().RED.intValue();
    
    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        
        graphics.setColor(this.RED);
        
        char[] charArray;
        char[] charArray2;
        int size2;
        int size3;
        final int size = this.baseRefreshHelperCharArray.length - 2;
        for(int index = size; index >= 0; index-=2)
        {
            charArray = this.baseRefreshHelperCharArray[index];
            charArray2 = this.baseRefreshHelperCharArray[index + 1];
            size2 = charArray.length;
            size3 = charArray2.length;

            graphics.drawChars(charArray, 0, size2, 0, this.yArray[index], 0);
            graphics.drawChars(charArray2, 0, size3, size2 * this.defaultStringWidth, this.yArray[index + 1], 0);
        }
    }
}
