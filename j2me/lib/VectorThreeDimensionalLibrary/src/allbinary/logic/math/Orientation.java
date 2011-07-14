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
package allbinary.logic.math;

public class Orientation
{
    public static Orientation NONE = new Orientation(0, 0, 0);
    private static Orientation SINGLETON = new Orientation(0, 0, 0);
    
    public BasicDecimal yaw = new BasicDecimal();
    public BasicDecimal pitch = new BasicDecimal();
    public BasicDecimal roll = new BasicDecimal();
    
    public static final Orientation getInstance(int yaw, int pitch, int roll)
    {
        SINGLETON.yaw.set(yaw);
        SINGLETON.pitch.set(pitch);
        SINGLETON.roll.set(roll);
        
        return SINGLETON;
    }
    
    private Orientation(int yaw, int pitch, int roll)
    {
        this.yaw.set(yaw);
        this.pitch.set(pitch);
        this.roll.set(roll);
    }
    
    public String toString()
    {
       StringBuilder stringBuffer = new StringBuilder();
       
       stringBuffer.append("Y: ");
       stringBuffer.append(yaw.toString());
       stringBuffer.append(" P: ");
       stringBuffer.append(pitch.toString());
       stringBuffer.append(" R: ");
       stringBuffer.append(roll.toString());
       
       return stringBuffer.toString();
    }   
    
}
