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

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;

import bundle.input.automation.module.InputAutomationModuleService;

public abstract class InputAutomationModuleServiceFactory
    implements ServiceFactory
{
    private InputAutomationModuleFactoryInterface inputAutomationModuleInterfaceArray[];
    
    public InputAutomationModuleServiceFactory()
    {
    }
        
    public Object getService(
        Bundle bundle, ServiceRegistration registration)
    {
        LogUtil.put(new Log("Start", this, "getService"));

        return new InputAutomationModuleService(
            this.getInputAutomationModuleInterfaceArray());
    }
    
    public void ungetService(
        Bundle bundle,
        ServiceRegistration registration,
        Object service)
    {
    }

    public InputAutomationModuleFactoryInterface[] getInputAutomationModuleInterfaceArray()
    {
        return inputAutomationModuleInterfaceArray;
    }

    public void setInputAutomationModuleInterfaceArray(InputAutomationModuleFactoryInterface[] inputAutomationModuleInterfaceArray)
    {
        this.inputAutomationModuleInterfaceArray = inputAutomationModuleInterfaceArray;
    }
}
