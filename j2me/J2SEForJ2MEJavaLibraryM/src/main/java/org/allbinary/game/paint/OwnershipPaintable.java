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
package org.allbinary.game.paint;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.communication.log.LogUtil;

//J2SEForJ2ME
public class OwnershipPaintable extends Paintable implements UpdateMyFontInterface
{
    
    public static OwnershipPaintable getInstance()
    {
        //return SINGLETON;
        return new OwnershipPaintable();
    }
    
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    private final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();    

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);

    //private final static OwnershipPaintable SINGLETON = new OwnershipPaintable();
    
    private final String COPYRIGHT = "AllBinary Copyright (c) 2011";
    //private final String COPYRIGHT = "Copyright (c) 2011";
    //private final String COMPANY = "AllBinary";
    
    private BasicColor basicColor = BasicColorFactory.getInstance().WHITE;
    private int color = this.basicColor.intValue();
    private int anchor = Anchor.TOP_LEFT;
    private int COPYRIGHT_Y;
    private int beginWidth;

    private OwnershipPaintable()
    {
        
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.COPYRIGHT_Y = 2 * font.getHeight();
        this.beginWidth = (font.stringWidth(this.COPYRIGHT) >> 1);
        this.myFontProcessor = MyFontProcessor.getInstance();
    }

    @Override
    public void setBasicColorP(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        this.color = basicColor.intValue();
    }

    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    @Override
    public void paint(final Graphics graphics)
    {
        //this.logUtil.putF(this.commonStrings.START, this, canvasStrings.PAINT);
        
        this.myFontProcessor.process(graphics);
        
        graphics.setColor(this.color);

        final int halfWidth = this.displayInfo.getLastHalfWidth();
        //int height = graphics.getClipHeight();
        final int height = this.displayInfo.getLastHeight();
        
        graphics.drawString(this.COPYRIGHT, halfWidth - this.beginWidth, height - COPYRIGHT_Y, this.anchor);

        /*
        beginWidth = (font.stringWidth(COMPANY) >> 1);

        graphics.drawString(COMPANY, halfWidth - beginWidth,
                height - COMPANY_Y, anchor);
        */        
    }

}
