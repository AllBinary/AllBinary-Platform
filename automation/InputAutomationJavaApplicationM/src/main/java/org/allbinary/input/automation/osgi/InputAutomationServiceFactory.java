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
package org.allbinary.input.automation.osgi;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.automation.InputAutomationJFrame;

public class InputAutomationServiceFactory
    implements ServiceFactory
{
    private static InputAutomationServiceFactory inputAutomationServiceFactory = 
        new InputAutomationServiceFactory();
    
    private InputAutomationServiceFactory()
    {
    }
    
    public static InputAutomationServiceFactory getInstance()
    {
        return inputAutomationServiceFactory;
    }
    
    public Object getService(
        Bundle bundle, ServiceRegistration registration)
    {
        LogUtil.put(new Log("Start", this, "getService"));

        return InputAutomationJFrame.getInstance();
        
        //return InputAutomationModuleConfigurationsSingletonFactory.getInstance();
        
        //ServiceReference sref = registration.getReference();
        /*
        try
        {
            
        }
        catch (FactoryConfigurationError fce)
        {
            fce.printStackTrace();
            return null;
        }*/
    }
    
    public void ungetService(
        Bundle bundle,
        ServiceRegistration registration,
        Object service)
    {
    }    
}
