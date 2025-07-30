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

public class GyroSensorFactory
{
    private static AllBinaryOrientationSensor allBinaryGyroSensor = AllBinaryOrientationSensor.NULL_ALLBINARY_ORIENTATION_SENSOR;

    public static void init() throws Exception
    {
       GyroSensorFactory.allBinaryGyroSensor = new NoGyroSensor();
    }

    public static AllBinaryOrientationSensor getInstance()
    {
        return GyroSensorFactory.allBinaryGyroSensor;
    }

}
