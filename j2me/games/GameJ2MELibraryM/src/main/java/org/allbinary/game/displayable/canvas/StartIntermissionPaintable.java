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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.FontDebugFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.logic.string.StringMaker;

public class StartIntermissionPaintable extends InitUpdatePaintable
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final FontDebugFactory fontDebugFactory = FontDebugFactory.getInstance();
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();
    
    protected AllBinaryGameCanvas gameCanvas;
    protected final String[] stringArray;
    
    private BasicColor basicColor;
    private int color;

    private final int[] lineArray;
    
    protected final int fontSize;
    protected final Font font;
    
    private boolean hasChanged = true;

    public final int[] lastWidth;
    
    public StartIntermissionPaintable(final AllBinaryGameCanvas gameCanvas, final String[] stringArray, final int[] lineArray, final BasicColor basicColor) {
        this(gameCanvas, stringArray,  lineArray, basicColor, Font.getDefaultFont());
    }
    
    public StartIntermissionPaintable(final AllBinaryGameCanvas gameCanvas, final String[] stringArray, final int[] lineArray, final BasicColor basicColor, final Font font)
    {
        this.gameCanvas = gameCanvas;
        this.stringArray = stringArray;
        this.lastWidth = new int[this.stringArray.length];
        this.setBasicColor(basicColor);
        this.color = basicColor.intValue();
        this.lineArray = lineArray;
        
        this.fontSize = font.getSize();
        this.font = font;
    }
     
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        //logUtil.put("Intermission Processing: ", this, "draw");
        
        final Font existingFont = graphics.getFont();
        
        fontDebugFactory.setFont(this.font, graphics);
        
        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        basicSetColorUtil.setBasicColor(graphics, this.basicColor, this.color);

        int beginWidth;
        for(int index = this.stringArray.length - 1; index >= 0; index--)
        {
            if(hasChanged) {
                this.lastWidth[index] = (graphics.getFont().stringWidth(this.stringArray[index]) >> 1);
            }
            beginWidth = this.lastWidth[index];
            
            graphics.drawString(this.stringArray[index], 
                    displayInfo.getLastHalfWidth() - beginWidth, 
                    displayInfo.getLastHalfHeight() - lineArray[index], anchor);
        }
        
        hasChanged = false;
        fontDebugFactory.setFont(existingFont, graphics);
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
