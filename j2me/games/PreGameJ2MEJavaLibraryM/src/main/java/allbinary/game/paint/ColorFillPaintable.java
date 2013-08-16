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
package allbinary.game.paint;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.ColorCompositeInterface;
import allbinary.graphics.paint.Paintable;

public class ColorFillPaintable extends Paintable 
implements ColorCompositeInterface
{
    private BasicColor basicColor;
    private int color;

    public ColorFillPaintable(BasicColor basicColor)
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

    public void paint(Graphics graphics)
    {        
        graphics.setColor(getColor());
        graphics.fillRect(0, 0, graphics.getClipWidth(), graphics.getClipHeight());
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
}
