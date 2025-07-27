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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;

public class ColorFillPaintable extends ColorFillBasePaintable
{
    protected final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
    public ColorFillPaintable(BasicColor basicColor)
    {
        super(basicColor);
    }

    @Override
    public void paint(Graphics graphics)
    {        
        graphics.setColor(this.colorP);
        //graphics.fillRect(0, 0, graphics.getClipWidth(), graphics.getClipHeight());
        graphics.fillRect(0, 0, this.displayInfoSingleton.getLastWidth(), this.displayInfoSingleton.getLastHeight());
    }
}
