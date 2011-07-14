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
package org.allbinary.input.gyro;

public class OrientationData
{
    private static final OrientationData SINGLETON = new OrientationData();
    
    private OrientationData()
    {
        
    }
    
    public static final OrientationData getInstance()
    {
        return SINGLETON;
    }
    
    public final String ORIENTATION_SENSOR_INPUT = "Orientation Sensor Input";
    public final String ORIENTATION_TYPE = "Orientation Type";
}
