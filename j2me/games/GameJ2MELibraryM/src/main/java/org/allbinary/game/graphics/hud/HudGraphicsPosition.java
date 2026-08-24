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
package org.allbinary.game.graphics.hud;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class HudGraphicsPosition
{
    @JsProperty
    public static final HudGraphicsPosition NULL_HUD_GRAPHICS_POSITION = new HudGraphicsPosition(PointFactory.getInstance().ZERO_ZERO, 9);
    
    private int anchor;
    private GPoint point = PointFactory.getInstance().ZERO_ZERO;
    
    @JsConstructor
    public HudGraphicsPosition(GPoint point, int anchor)
    {
       this.setAnchor(anchor);
       this.setPoint(point);
    }

    @JsMethod
    public int getAnchor()
    {
       return this.anchor;
    }

    @JsMethod
    private void setAnchor(int anchor)
    {
       this.anchor = anchor;
    }

    @JsMethod
    public GPoint getPoint()
    {
        return this.point;
    }

    @JsMethod
    public void setPoint(GPoint point)
    {
        this.point = point;
    }
}