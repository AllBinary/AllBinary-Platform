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
	private static final TestGameDemoNoButtonsBuilder testGameDemoNoButtonsBuilder = new TestGameDemoNoButtonsBuilder();
	
    public static BaseTouchInput getInstance(
            SensorGameUpdateProcessor sensorGameUpdateProcessor)
    {
    	return testGameDemoNoButtonsBuilder;
    }
}
