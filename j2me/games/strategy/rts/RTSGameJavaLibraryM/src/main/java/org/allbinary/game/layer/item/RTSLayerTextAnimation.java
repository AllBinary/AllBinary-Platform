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

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.font.MyFont;

public class RTSLayerTextAnimation
extends Animation
{
    private final MyFont myFont = MyFont.getInstance();
    
    private final Image image;
    private final String text;
    
    //private final int adjustedCostX;
    
    public RTSLayerTextAnimation(final String text, final Image image)
    {   
        //this.adjustedCostX = 2 + (text.length() * (MyFont.MYFONT.DEFAULT_CHAR_WIDTH - 1));
        
        this.text = text;
        this.image = image;
    }

    @Override
    public void paint(Graphics graphics, int x, int y)
    {
        super.paint(graphics, x, y);

        final int adjustedCostY = image.getHeight() - this.myFont.DEFAULT_CHAR_HEIGHT;
        graphics.drawString(text, x, y + adjustedCostY, 0);
        // + this.adjustedCostX
    }
}
