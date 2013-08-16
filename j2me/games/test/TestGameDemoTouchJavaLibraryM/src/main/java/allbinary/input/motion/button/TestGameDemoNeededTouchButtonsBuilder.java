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
package allbinary.input.motion.button;

import org.allbinary.input.gyro.SensorGameUpdateProcessor;

public class TestGameDemoNeededTouchButtonsBuilder
{
    public static BaseTouchInput getInstance(
            SensorGameUpdateProcessor sensorGameUpdateProcessor)
    {
        if(sensorGameUpdateProcessor.isAnySensor())
        {
            return new TestGameDemoNeededWithSensorTouchButtonsBuilder();
        }
        else
        if(TouchScreenFactory.getInstance().isMultiTouch())
        {
            return new TestGameDemoNeededMultiTouchButtonsBuilder();
        }
        else
            //It would be better to find out if a keyboard is available to turn off the buttons.
        {
            return new TestGameDemoNeededMultiTouchButtonsBuilder();
        }
    }
}
