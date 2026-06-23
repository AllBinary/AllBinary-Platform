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

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.FontDebugFactory;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class StartIntermissionPaintable extends InitUpdatePaintable 
    implements UpdateMyFontInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final FontDebugFactory fontDebugFactory = FontDebugFactory.getInstance();
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();
    
    protected final AllBinaryGameCanvas gameCanvas;
    protected final String[] stringArray;
    
    protected final int fontSize;
    protected final Font font;

    public final int[] lastWidth;
    
    private final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    private MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    private BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
    private int color;

    protected int[] lineYOffsetArray = NullUtil.getInstance().NULL_INT_ARRAY;
        
    private boolean hasChanged = true;
     
    private int anchor = Anchor.TOP_LEFT;
    
    protected int fontHeight;

    public StartIntermissionPaintable(final AllBinaryGameCanvas gameCanvas, final String[] stringArray, final BasicColor basicColor, final Font font)
    {
        this.gameCanvas = gameCanvas;
        this.stringArray = stringArray;
        this.lastWidth = new int[this.stringArray.length];
        this.setBasicColorP(basicColor);
        this.color = basicColor.intValue();
        
        this.fontSize = font.getSize();
        this.font = font;
    }    

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        //this.logUtil.putF("Intermission Processing: ", this, "draw");

        this.myFontProcessor.process(graphics);
        
        final Font existingFont = graphics.getFont();
        this.fontDebugFactory.setFont(this.font, graphics);
        
        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        this.basicSetColorUtil.setBasicColorP3(graphics, this.basicColor, this.color);

        int beginWidth;
        for(int index = this.lineYOffsetArray.length - 1; index >= 0; index--)
        {
            if(this.hasChanged) {
                this.lastWidth[index] = (graphics.getFont().stringWidth(this.stringArray[index]) >> 1);
            }
            beginWidth = this.lastWidth[index];
            
            graphics.drawString(this.stringArray[index], 
                    displayInfo.getLastHalfWidth() - beginWidth, 
                    displayInfo.getLastHalfHeight() - this.lineYOffsetArray[index], this.anchor);
        }
        
        this.hasChanged = false;
        this.fontDebugFactory.setFont(existingFont, graphics);
    }

    private final String BEGIN_LEVEL = "Begin Level ";
    
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
    
    @Override
    public void update()
    {
        int level = this.gameCanvas.getLayerManager().getGameInfo().getCurrentLevel();
        this.stringArray[0] = new StringMaker().append(this.BEGIN_LEVEL).appendint(level).toString();

        for(int index = this.stringArray.length - 1; index >= 1; index--)
        {
            this.stringArray[index] = this.EMPTY_STRING;
        }
    }

    @Override
    public void setBasicColorP(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

}
