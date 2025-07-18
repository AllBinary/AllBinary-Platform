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

import org.allbinary.input.AllBinarySensorListener;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.Orientation;

public class NoGyroSensor extends AllBinaryOrientationSensor
implements AllBinarySensorListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public NoGyroSensor()
    {
        logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
    }
    
    public Orientation getOrientation() throws Exception
    {
        return Orientation.NONE;
    }
    
    public void onSensorChange()
    {
    }
}
