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
package org.allbinary.view;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.GPoint;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ViewPositionBase extends GPoint
{
    @JsProperty
    public static final ViewPositionBase NULL_VIEW_POSITION = new ViewPositionBase(0, 0, 0);
    
    @JsConstructor
    protected ViewPositionBase(int x, int y, int z)
    {
        super(x, y, z);
    }
    
    @JsMethod
    public int getX2()
    {
        return this.getX();
    }

    @JsMethod
    public int getY2()
    {
        return this.getY();
    }

    @JsMethod
    public int getZ2()
    {
        return this.getZ();
    }
    
    @JsMethod
    public void setAllbinaryLayer(Object allbinaryLayer)
    {
    }
    
}
