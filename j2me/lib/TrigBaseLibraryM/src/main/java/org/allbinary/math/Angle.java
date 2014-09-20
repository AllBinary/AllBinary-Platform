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

    protected Angle(short angle)
    {
        //this.setValue(SmallIntegerSingletonFactory.getInstance(angle));
        this.setValue(angle);
    }

    //public Integer getValue()
    public short getValue()
    {
        return value;
    }

    //private void setValue(Integer value)
    private void setValue(short value)
    {
        this.value = value;
    }

}
