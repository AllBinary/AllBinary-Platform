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
package allbinary.input.automation.module.osgi;

import org.osgi.framework.BundleContext;

import allbinary.osgi.service.InputAutomationServiceConsumer;
import bundle.input.automation.module.InputAutomationModuleServiceInterface;

public class InputAutomationModuleServiceConsumer
    extends InputAutomationServiceConsumer
{
    public InputAutomationModuleServiceConsumer(BundleContext bundleContext)
    {
        super(InputAutomationModuleServiceInterface.class.getName(), 
            bundleContext, new InputAutomationModuleOSGIServiceVisitor());
    }
}
