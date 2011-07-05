/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 05/19/06
 *
 *
 * Modified By         When       ?
 *
 */
package org.allbinary.input.gyro;

import org.allbinary.input.AllBinarySensorListener;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.math.Orientation;

public class NoGyroSensor extends AllBinaryOrientationSensor
implements AllBinarySensorListener
{
    public NoGyroSensor()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
    }
    
    public Orientation getOrientation() throws Exception
    {
        return Orientation.NONE;
    }
    
    public void onSensorChange()
    {
    }
}
