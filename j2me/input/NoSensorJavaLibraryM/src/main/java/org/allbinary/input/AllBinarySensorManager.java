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
package org.allbinary.input;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.accelerometer.AccelerometerSensorFactory;
import org.allbinary.input.gyro.GyroSensorFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class AllBinarySensorManager
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final String ORIENTATION_SENSOR_INPUT = "Orientation Sensor Input";
    public final String ORIENTATION_TYPE = "Orientation Type";

    private static final AllBinarySensorManager SINGLETON = new AllBinarySensorManager();

    private AllBinarySensorManager()
    {
        try
        {
            GyroSensorFactory.init();
            AccelerometerSensorFactory.init();
        } catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
        }
    }

    public static AllBinarySensorManager getInstance()
    {
        return SINGLETON;
    }

    //private final AllBinarySensor[] allBinarySensorArray = new AllBinarySensor[0];
    //public AllBinarySensor[] init()
    public void init()
    {
    }
    
    private final BasicArrayList list = new BasicArrayList();
    public BasicArrayList getSensorNamesList()
    {
        return list;
    }

    public void shutdown()
    {
    }
}
