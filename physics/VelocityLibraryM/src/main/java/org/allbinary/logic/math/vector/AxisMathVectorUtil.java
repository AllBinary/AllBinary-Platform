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
package org.allbinary.logic.math.vector;

import jsinterop.annotations.JsType;

import org.allbinary.math.NoDecimalTrigTable;
import jsinterop.annotations.JsMethod;


@JsType
public class AxisMathVectorUtil
{
    private static final AxisMathVectorUtil instance = new AxisMathVectorUtil();

    @JsMethod
    public static AxisMathVectorUtil getInstance()
    {
        return AxisMathVectorUtil.instance;
    }
    
    private final NoDecimalTrigTable noDecimalTrigTable = NoDecimalTrigTable.getInstance();
    
    @JsMethod
    public long calculateX(long magnitude, int angle)
    {
        return magnitude * this.noDecimalTrigTable.cos(angle) / 10;
    }
    
    @JsMethod
    public long calculateY(long magnitude, int angle)
    {
       return magnitude * this.noDecimalTrigTable.sin(angle) / 10;
    }    

    //Since I use z as altitude going up and down then otherAngle is swayAngle and not tiltAngle
    //as such it is hard to say what the otherAngle is since I do not have normal orientation of the axi.
    @JsMethod
    public long calculateZ(long magnitude, int otherAngle)
    {
       return magnitude * this.noDecimalTrigTable.sin(otherAngle) / 10;
    }    
}
