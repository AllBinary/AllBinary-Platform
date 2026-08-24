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
package org.allbinary.logic.math;

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class Orientation
{
    @JsProperty
    public static Orientation NONE = new Orientation(0, 0, 0);
    private static Orientation SINGLETON = new Orientation(0, 0, 0);
    
    @JsProperty
    public BasicDecimal yaw = new BasicDecimal(0);
    @JsProperty
    public BasicDecimal pitch = new BasicDecimal(0);
    @JsProperty
    public BasicDecimal roll = new BasicDecimal(0);
    
    @JsMethod
    public static final Orientation getInstance(int yaw, int pitch, int roll)
    {
        Orientation.SINGLETON.yaw.setint(yaw);
        Orientation.SINGLETON.pitch.setint(pitch);
        Orientation.SINGLETON.roll.setint(roll);
        
        return Orientation.SINGLETON;
    }
    
    @JsConstructor
    private Orientation(int yaw, int pitch, int roll)
    {
        this.yaw.setint(yaw);
        this.pitch.setint(pitch);
        this.roll.setint(roll);
    }
    
    @JsMethod
    public String toString()
    {
       StringMaker stringBuffer = new StringMaker();
       
       stringBuffer.append("Y: ");
       stringBuffer.append(this.yaw.toString());
       stringBuffer.append(" P: ");
       stringBuffer.append(this.pitch.toString());
       stringBuffer.append(" R: ");
       stringBuffer.append(this.roll.toString());
       
       return stringBuffer.toString();
    }   
    
}
