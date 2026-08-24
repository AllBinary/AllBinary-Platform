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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.graphics.paint.Paintable;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ColorFillBasePaintable extends Paintable 
implements ColorCompositeInterface
{
    @JsProperty
    protected BasicColor basicColor = BasicColorFactory.getInstance().WHITE;
    @JsProperty
    protected int colorP;
    
    @JsConstructor
    public ColorFillBasePaintable(BasicColor basicColor)
    {
       this.setBasicColorP(basicColor);       
    }
    
    @Override
    @JsMethod
    public void setBasicColorP(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        this.setColor(this.getBasicColorP().intValue());
    }

    /**
     * @return the basicColor
     */
    @Override
    @JsMethod
    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }
         
    /**
     * @return the color
     */
    @JsMethod
    protected int getColor()
    {
        return this.colorP;
    }

    /**
     * @param color
     *            the color to set
     */
    @JsMethod
    protected void setColor(int color)
    {
        this.colorP = color;
    }
    
    @Override
    @JsMethod
    public void paint(Graphics graphics)
    {        
    }
    
}
