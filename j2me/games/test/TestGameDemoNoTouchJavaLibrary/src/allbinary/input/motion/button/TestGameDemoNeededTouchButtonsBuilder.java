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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import org.allbinary.input.gyro.SensorGameUpdateProcessor;

public class TestGameDemoNeededTouchButtonsBuilder
extends BaseTouchInput
{
    private static final TestGameDemoNeededTouchButtonsBuilder instance = 
            new TestGameDemoNeededTouchButtonsBuilder();
    
    public static BaseTouchInput getInstance(
            SensorGameUpdateProcessor sensorGameUpdateProcessor)
    {
        return instance;
    }
    
    public void build()
    {
       LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "build"));
    }
}
