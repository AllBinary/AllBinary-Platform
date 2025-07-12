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

import org.allbinary.canvas.Processor;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.SensorFeatureFactory;
import org.allbinary.game.input.motion.action.NoCompleteMotionGestureInputEventListener;
import org.allbinary.game.input.motion.action.SingleKeyPressGameKeyFromMotionGestureEventListener;
import org.allbinary.input.accelerometer.AccelerometerSensorFactory;

public class AccelerometerSensorUpdateProcessor extends Processor
{
    public void process() throws Exception
    {
        SensorFeatureFactory sensorFeatureFactory = 
            SensorFeatureFactory.getInstance();

        // Stop/Start sensor input
        if (Features.getInstance().isFeature(
                sensorFeatureFactory.ORIENTATION_SENSORS))
        {
            AccelerometerSensorFactory.getInstance().setListener(
                    new SingleKeyPressGameKeyFromMotionGestureEventListener());
        }
        else if (Features.getInstance().isFeature(
                sensorFeatureFactory.SIMULATED_ORIENTATION_SENSORS))
        {
            AccelerometerSensorFactory.getInstance().setListener(
                    new SingleKeyPressGameKeyFromMotionGestureEventListener());
        }        
        else if (Features.getInstance().isFeature(
                sensorFeatureFactory.NO_ORIENTATION))
        {
            AccelerometerSensorFactory.getInstance().setListener(
                    NoCompleteMotionGestureInputEventListener.getInstance());
        }
        else
        {
            throw new Exception("Sensor Feature Not Set");
        }
    }
}
