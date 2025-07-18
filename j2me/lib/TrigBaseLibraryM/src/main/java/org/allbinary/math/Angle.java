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
package org.allbinary.math;


public class Angle
{
    //private Integer value;
    private short value;

    //protected 
    public Angle(final short angle)
    {
        //this.value = SmallIntegerSingletonFactory.getInstance(angle);
        this.value = angle;
    }

    public short getValue()
    {
        return value;
    }

}
