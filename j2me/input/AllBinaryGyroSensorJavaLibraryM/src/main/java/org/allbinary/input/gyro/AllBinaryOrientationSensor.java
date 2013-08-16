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

import org.allbinary.input.AllBinarySensor;

import abcs.logic.basic.NotImplemented;
import allbinary.game.input.CompleteMotionGestureInputEventListenerInterface;
import allbinary.game.input.motion.action.NoCompleteMotionGestureInputEventListener;
import allbinary.logic.math.Orientation;
import allbinary.logic.math.PrimitiveFloatUtil;

public class AllBinaryOrientationSensor extends AllBinarySensor
{
    protected float[] sensorDataFloatArray = PrimitiveFloatUtil.getArrayInstance();
    
    protected CompleteMotionGestureInputEventListenerInterface listener =
        NoCompleteMotionGestureInputEventListener.getInstance();

    public void setListener(
            CompleteMotionGestureInputEventListenerInterface completeMotionGestureInputEventListenerInterface)
    {
        this.listener = completeMotionGestureInputEventListenerInterface;
    }
    
    protected AllBinaryOrientationSensor()
    {
        
    }
    
    public Orientation getOrientation() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
}
