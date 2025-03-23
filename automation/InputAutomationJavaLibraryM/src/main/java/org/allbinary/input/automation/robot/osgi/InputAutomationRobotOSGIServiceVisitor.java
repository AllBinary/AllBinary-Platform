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

import bundle.input.automation.robot.InputAutomationRobotServiceInterface;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.osgi.OSGIServiceInterface;
import org.allbinary.osgi.OSGIServiceVisitorInterface;

public class InputAutomationRobotOSGIServiceVisitor
    implements OSGIServiceVisitorInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public InputAutomationRobotOSGIServiceVisitor()
    {
    }
    
    public Object visit(final Object object)
    {
        return this.visit((OSGIServiceInterface) object);
    }
    
    public Boolean visit(final OSGIServiceInterface osgiServiceInterface)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "visit"));
            
            final InputAutomationRobotServiceInterface
                inputAutomationRobotServiceInterface =
                (InputAutomationRobotServiceInterface) osgiServiceInterface;
            
            final InputRobotInterface[] inputRobotInterfaceArray =
                inputAutomationRobotServiceInterface.getInputRobotInterfaceArray();
            
            for(int index = 0; index < inputRobotInterfaceArray.length; index++)
            {
                LogUtil.put(LogFactory.getInstance("Adding: " + 
                    inputRobotInterfaceArray[index].getName(), this, "visit"));
                InputRobotFactory.getInstance().add(inputRobotInterfaceArray[index]);
            }
            return Boolean.TRUE;
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "visit", e));
            return Boolean.FALSE;
        }
    }
    
}
