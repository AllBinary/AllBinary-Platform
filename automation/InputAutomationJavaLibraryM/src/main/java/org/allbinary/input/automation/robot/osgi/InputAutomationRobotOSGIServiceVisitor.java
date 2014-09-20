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
package org.allbinary.input.automation.robot.osgi;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.osgi.OSGIServiceInterface;
import org.allbinary.osgi.OSGIServiceVisitorInterface;

import org.bundle.input.automation.robot.InputAutomationRobotServiceInterface;

public class InputAutomationRobotOSGIServiceVisitor
    implements OSGIServiceVisitorInterface
{
    public InputAutomationRobotOSGIServiceVisitor()
    {
    }
    
    public Object visit(Object object)
    {
        return this.visit((OSGIServiceInterface) object);
    }
    
    public Boolean visit(OSGIServiceInterface osgiServiceInterface)
    {
        try
        {
            LogUtil.put(new Log("Start", this, "visit"));
            
            InputAutomationRobotServiceInterface
                inputAutomationRobotServiceInterface =
                (InputAutomationRobotServiceInterface) osgiServiceInterface;
            
            InputRobotInterface inputRobotInterfaceArray[] =
                inputAutomationRobotServiceInterface.getInputRobotInterfaceArray();
            
            for(int index = 0; index < inputRobotInterfaceArray.length; index++)
            {
                LogUtil.put(new Log("Adding: " + 
                    inputRobotInterfaceArray[index].getName(), this, "visit"));
                InputRobotFactory.getInstance().add(inputRobotInterfaceArray[index]);
            }
            return Boolean.TRUE;
        }
        catch(Exception e)
        {
            LogUtil.put(new Log("Exception", this, "visit", e));
            return Boolean.FALSE;
        }
    }
    
}
