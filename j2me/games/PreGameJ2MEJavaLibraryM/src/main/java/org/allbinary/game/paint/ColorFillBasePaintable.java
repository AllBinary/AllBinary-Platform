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
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.graphics.paint.Paintable;

public class ColorFillBasePaintable extends Paintable 
implements ColorCompositeInterface
{
    protected BasicColor basicColor;
    protected int color;
    
    public ColorFillBasePaintable(BasicColor basicColor)
    {
       this.setBasicColor(basicColor);       
    }
    
    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        this.setColor(this.getBasicColor().intValue());
    }

    /**
     * @return the basicColor
     */
    public BasicColor getBasicColor()
    {
        return basicColor;
    }
         
    /**
     * @return the color
     */
    protected int getColor()
    {
        return color;
    }

    /**
     * @param color
     *            the color to set
     */
    protected void setColor(int color)
    {
        this.color = color;
    }
    
    public void paint(Graphics graphics)
    {        
    }    
}
