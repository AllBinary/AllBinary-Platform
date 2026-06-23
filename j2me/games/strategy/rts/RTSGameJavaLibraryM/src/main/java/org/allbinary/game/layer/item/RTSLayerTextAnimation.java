/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.layer.item;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;

public class RTSLayerTextAnimation extends Animation 
    implements UpdateMyFontInterface
{
    
    private final Image image;
    private final String text;

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    //private final int adjustedCostX;
    
    private int fontHeight = 0;
    
    public RTSLayerTextAnimation(final String text, final Image image)
    {   
        //this.adjustedCostX = 2 + (text.length() * (MyFont.MYFONT.DEFAULT_CHAR_WIDTH - 1));
        
        this.text = text;
        this.image = image;
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void paintXY(Graphics graphics, int x, int y)
    {
        this.myFontProcessor.process(graphics);

        super.paintXY(graphics, x, y);

        final int adjustedCostY = this.image.getHeight() - this.fontHeight;
        graphics.drawString(this.text, x, y + adjustedCostY, 0);
        // + this.adjustedCostX
    }
}
