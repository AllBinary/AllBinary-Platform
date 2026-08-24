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
package org.allbinary.game.health;

import jsinterop.annotations.JsType;

import org.allbinary.animation.Animation;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class HealthBarAnimation extends Animation
{
    private final BasicColorFactory basicColorFactory = 
            BasicColorFactory.getInstance();
    
    @JsProperty
    protected int x2;

    @JsProperty
    protected int thickness;

    private int location;

    @JsProperty
    protected AllBinaryLayer allbinaryLayer;

    @JsConstructor
    public HealthBarAnimation(final AllBinaryLayer layerInterface, final int location)
    throws Exception
    {
        this.allbinaryLayer = layerInterface;

        this.location = location;

        final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();

        if (this.location != basicHudFactory.TOPLEFT
                && this.location != basicHudFactory.BOTTOMLEFT)
        {
            throw new Exception("Location Not Valid");
        }

        if (layerInterface.getWidth() > 40)
        {
            this.thickness = 3;
        }
        else if (layerInterface.getWidth() > 20)
        {
            this.thickness = 2;
        }
        else
        {
            this.thickness = 1;
        }
    }

    @JsMethod
    public void onHealthChange(final int newX2)
    {
        this.x2 = newX2;
        
        int quarter = (this.allbinaryLayer.getWidth() >> 2);
        if (this.x2 > quarter * 3)
        {
            this.basicColor = this.basicColorFactory.GREEN;
            this.colorP = this.basicColor.intValue();
        }
        else if (this.x2 > quarter * 2)
        {
            this.basicColor = this.basicColorFactory.YELLOW;
            this.colorP = this.basicColor.intValue();
        }
        else if (this.x2 > quarter)
        {
            this.basicColor = this.basicColorFactory.ORANGE;
            this.colorP = this.basicColor.intValue();
        }
        else
        {
            this.basicColor = this.basicColorFactory.RED;
            this.colorP = this.basicColor.intValue();
        }
    }

    @JsMethod
    protected int getY()
    {
        final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();
        
        if (this.location == basicHudFactory.TOPLEFT)
        {
            return this.allbinaryLayer.getViewPosition().getY() - 4;
        }
        else if (this.location == basicHudFactory.BOTTOMLEFT)
        {
            return this.allbinaryLayer.getViewPosition().getY2() + 4;
        }
        return -1;
    }
}