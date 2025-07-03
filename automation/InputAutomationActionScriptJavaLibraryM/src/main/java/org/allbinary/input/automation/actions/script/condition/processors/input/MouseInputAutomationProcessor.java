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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class MouseInputAutomationProcessor
{
    private MouseInputAutomationProcessor()
    {
    }
    
    public static void process(
        MouseActionScriptInputInterface mouseActionScriptInputInterface)
        throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        LogUtil.put(LogFactory.getInstance(
            "Start - Processing Mouse Input at: " +
            mouseActionScriptInputInterface.getPoint() +
            " clicks: " + mouseActionScriptInputInterface.getButtonClicks(),
            "MouseInputAutomationProcessor", commonStrings.PROCESS));

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
        
        //LogUtil.put(LogFactory.getInstance(this.commonStrings.END, "MouseInputAutomationProcessor", commonStrings.PROCESS));
    }
}
