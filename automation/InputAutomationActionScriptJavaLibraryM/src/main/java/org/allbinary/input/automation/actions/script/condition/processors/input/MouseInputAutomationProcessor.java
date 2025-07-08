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
package org.allbinary.input.automation.actions.script.condition.processors.input;

import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

public class MouseInputAutomationProcessor
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private MouseInputAutomationProcessor()
    {
    }
    
    public static void process(
        MouseActionScriptInputInterface mouseActionScriptInputInterface)
        throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(
            new StringMaker().append("Start - Processing Mouse Input at: ")
                .append(mouseActionScriptInputInterface.getPoint().toString())
                .append(" clicks: ").append(mouseActionScriptInputInterface.getButtonClicks()).toString(),
            "MouseInputAutomationProcessor", commonStrings.PROCESS);

        InputRobotInterface inputRobotInterface = 
            mouseActionScriptInputInterface.getInputRobotInterface();

        if(mouseActionScriptInputInterface.getPoint().x != -1 &&
           mouseActionScriptInputInterface.getPoint().y != -1)
        {
            inputRobotInterface.mouseMove(
                mouseActionScriptInputInterface.getPoint());
        }

        Thread.sleep(mouseActionScriptInputInterface.getTime());

        if(mouseActionScriptInputInterface.getButtonClicks() != 0)
        {
            inputRobotInterface.mousePress(
                mouseActionScriptInputInterface.getButtonClicks());
            
            Thread.sleep(mouseActionScriptInputInterface.getTime());
            
            inputRobotInterface.mouseRelease(
                mouseActionScriptInputInterface.getButtonClicks());
        }

        Thread.sleep(mouseActionScriptInputInterface.getTime());
        
        //logUtil.put(this.commonStrings.END, "MouseInputAutomationProcessor", commonStrings.PROCESS);
    }
}
