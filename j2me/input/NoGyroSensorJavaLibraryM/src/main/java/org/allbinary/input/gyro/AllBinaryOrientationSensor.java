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

import org.allbinary.game.input.CompleteMotionGestureInputEventListenerInterface;
import org.allbinary.game.input.motion.action.NoCompleteMotionGestureInputEventListener;
import org.allbinary.input.AllBinarySensor;
import org.allbinary.logic.math.Orientation;

public class AllBinaryOrientationSensor extends AllBinarySensor
{
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
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
}
