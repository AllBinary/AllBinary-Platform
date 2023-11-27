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
package org.allbinary.input.automation.module.generic;

import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import org.allbinary.input.automation.module.osgi.InputAutomationModuleServiceFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class InputAutomationGenericModuleServiceFactory
    extends InputAutomationModuleServiceFactory
{
    private static InputAutomationGenericModuleServiceFactory
        inputAutomationGenericModuleServiceFactory =
        new InputAutomationGenericModuleServiceFactory();
    
    public InputAutomationGenericModuleServiceFactory()
    {
        try
        {
            InputAutomationModuleFactoryInterface[] inputAutomationModuleInterfaceArray =
                new InputAutomationModuleFactoryInterface[1];
            inputAutomationModuleInterfaceArray[0] = new GenericModuleFactory();
            
            this.setInputAutomationModuleInterfaceArray(
                inputAutomationModuleInterfaceArray);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, this.commonStrings.CONSTRUCTOR, e));
        }
    }
    
    public static InputAutomationGenericModuleServiceFactory getInstance()
    {
        return inputAutomationGenericModuleServiceFactory;
    }
}
