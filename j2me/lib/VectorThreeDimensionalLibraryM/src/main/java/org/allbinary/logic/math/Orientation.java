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

import org.allbinary.logic.string.StringMaker;

public class Orientation
{
    public static Orientation NONE = new Orientation(0, 0, 0);
    private static Orientation SINGLETON = new Orientation(0, 0, 0);
    
    public BasicDecimal yaw = new BasicDecimal(0);
    public BasicDecimal pitch = new BasicDecimal(0);
    public BasicDecimal roll = new BasicDecimal(0);
    
    public static final Orientation getInstance(int yaw, int pitch, int roll)
    {
        SINGLETON.yaw.setint(yaw);
        SINGLETON.pitch.setint(pitch);
        SINGLETON.roll.setint(roll);
        
        return SINGLETON;
    }
    
    private Orientation(int yaw, int pitch, int roll)
    {
        this.yaw.setint(yaw);
        this.pitch.setint(pitch);
        this.roll.setint(roll);
    }
    
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
