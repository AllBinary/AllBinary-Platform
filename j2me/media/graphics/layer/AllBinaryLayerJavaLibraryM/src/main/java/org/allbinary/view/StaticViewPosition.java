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
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class StaticViewPosition extends ViewPosition
{
    @JsConstructor
    public StaticViewPosition(int x, int y, int z)
    {
        super(x, y, z);
    }
    
    @Override
    @JsMethod
    public int getX()
    {
        return this.getRawX();
    }

    @Override
    @JsMethod
    public int getY()
    {
        return this.getRawY();
    }

    @Override
    @JsMethod
    public int getZ()
    {
        return this.getRawZ();
    }    
}