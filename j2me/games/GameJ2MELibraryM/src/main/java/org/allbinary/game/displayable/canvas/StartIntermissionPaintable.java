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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.logic.string.StringMaker;

public class StartIntermissionPaintable extends InitUpdatePaintable
{
    protected AllBinaryGameCanvas gameCanvas;
    protected String[] stringArray;
    
    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();
    
    private BasicColor basicColor;
    private int color;

    private final int[] lineArray;
    
    public StartIntermissionPaintable(AllBinaryGameCanvas gameCanvas, String[] stringArray, int[] lineArray, BasicColor basicColor)
    {
        this.gameCanvas = gameCanvas;
        this.stringArray = stringArray;
        this.setBasicColor(basicColor);
        this.color = basicColor.intValue();
        this.lineArray = lineArray;
    }
     
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        //LogUtil.put(LogFactory.getInstance("Intermission Processing: ", this, "draw"));
        
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        basicSetColorUtil.setBasicColor(graphics, this.basicColor, this.color);

        int beginWidth;
        for(int index = this.stringArray.length - 1; index >= 0; index--)
        {
            beginWidth = (graphics.getFont().stringWidth(this.stringArray[index]) >> 1);
            
            graphics.drawString(this.stringArray[index], 
                    displayInfo.getLastHalfWidth() - beginWidth, 
                    displayInfo.getLastHalfHeight() - lineArray[index], anchor);
        }
    }

    private final String BEGIN_LEVEL = "Begin Level ";
    
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
    
    public void update()
    {
        int level = gameCanvas.getLayerManager().getGameInfo().getCurrentLevel();
        this.stringArray[0] = new StringMaker().append(BEGIN_LEVEL).append(level).toString();

        for(int index = this.stringArray.length - 1; index >= 1; index--)
        {
            this.stringArray[index] = this.EMPTY_STRING;
        }
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }
}
