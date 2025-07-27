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

public class OverScanColorFillPaintable extends ColorFillPaintable
{
    public OverScanColorFillPaintable(BasicColor basicColor)
    {
        super(basicColor);
    }

    @Override
    public void paint(Graphics graphics)
    {        
        graphics.setColor(this.colorP);
        //graphics.fillRect(-graphics.getClipX(), -graphics.getClipY(), graphics.getClipWidth() + (graphics.getClipX() * 2), graphics.getClipHeight() + (graphics.getClipY() * 2));
        graphics.fillRect(-graphics.getClipX(), -graphics.getClipY(), this.displayInfoSingleton.getLastWidth() + (graphics.getClipX() * 2), this.displayInfoSingleton.getLastHeight() + (graphics.getClipY() * 2));
    }
}
