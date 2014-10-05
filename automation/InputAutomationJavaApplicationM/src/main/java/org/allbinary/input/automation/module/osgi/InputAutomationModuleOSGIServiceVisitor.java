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
package org.allbinary.input.automation.module.osgi;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.automation.InputAutomationJFrame;
import org.allbinary.input.automation.configuration.InputAutomationConfigurationUtil;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import org.allbinary.osgi.OSGIServiceInterface;
import org.allbinary.osgi.OSGIServiceVisitorInterface;
import bundle.input.automation.module.InputAutomationModuleService;
import org.allbinary.logic.communication.log.LogFactory;

public class InputAutomationModuleOSGIServiceVisitor
    implements OSGIServiceVisitorInterface
{
    public InputAutomationModuleOSGIServiceVisitor()
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
            InputAutomationModuleService inputAutomationModuleService =
                (InputAutomationModuleService) osgiServiceInterface;
            
            InputAutomationModuleFactoryInterface inputAutomationModuleInterfaceArray[] =
                inputAutomationModuleService.getInputAutomationModuleInterfaceArray();
            
            for(int index = 0; index < inputAutomationModuleInterfaceArray.length; index++)
            {
                InputAutomationJFrame.getInstance().onAdd(
                    InputAutomationConfigurationUtil.getChangeEvent(
                    inputAutomationModuleInterfaceArray[index]));
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
