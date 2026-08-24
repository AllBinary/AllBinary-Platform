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
package org.allbinary.animation.vector;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.math.AngleFactory;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class CircleAnimation extends Animation implements ColorCompositeInterface
{
    @JsProperty
    protected final int TOTAL_ANGLE = (int) AngleFactory.getInstance().TOTAL_ANGLE;
    
    @JsProperty
    protected int width;
    @JsProperty
    protected int height;

    @JsConstructor
    public CircleAnimation(int width, int height, BasicColor basicColor)
    {
        this.width = width;
        this.height = height;

        this.setBasicColorP(basicColor);
    }

    @Override
    @JsMethod
    public void nextFrame()
    {
    }
    
    @Override
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
        this.basicSetColorUtil.setBasicColorP3(
                graphics, this.getBasicColorP(), this.getColor());

        graphics.drawArc(x, y, this.width, this.height, 0, this.TOTAL_ANGLE);
    }
}
