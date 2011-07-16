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
package allbinary.input.automation.robot.osgi;

import allbinary.input.automation.robot.InputRobotInterface;

public class InputAutomationRobotInterfaceWrapper
{
    private InputRobotInterface inputRobotInterface;
    
    public InputAutomationRobotInterfaceWrapper(
        InputRobotInterface inputRobotInterface)
    {
        this.setInputRobotInterface(inputRobotInterface);
    }
    
    public InputRobotInterface getInputRobotInterface()
    {
        return inputRobotInterface;
    }

    public void setInputRobotInterface(InputRobotInterface inputRobotInterface)
    {
        this.inputRobotInterface = inputRobotInterface;
    }
}
