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
package bundle.input.automation.module.generic;

import org.allbinary.input.automation.module.generic.InputAutomationGenericModuleServiceFactory;
import org.allbinary.input.automation.module.osgi.InputAutomationModuleBundleActivator;

public class InputAutomationGenericModuleBundleActivator
    extends InputAutomationModuleBundleActivator
{
    public InputAutomationGenericModuleBundleActivator()
    throws Exception
    {
        
    }

    public void init()
    throws Exception
    {
        InputAutomationGenericModuleServiceFactory 
            inputAutomationGenericModuleServiceFactory = 
            InputAutomationGenericModuleServiceFactory.getInstance();
        
        this.setInputAutomationModuleInterface(
            inputAutomationGenericModuleServiceFactory.getInputAutomationModuleInterfaceArray());
        
        this.setServiceFactory(
            inputAutomationGenericModuleServiceFactory);
    }
}
