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

import org.osgi.framework.BundleContext;

import bundle.input.automation.module.InputAutomationModuleServiceInterface;
import org.allbinary.osgi.service.InputAutomationServiceConsumer;

public class InputAutomationModuleServiceConsumer
    extends InputAutomationServiceConsumer
{
    public InputAutomationModuleServiceConsumer(BundleContext bundleContext)
    {
        super(InputAutomationModuleServiceInterface.class.getName(), 
            bundleContext, new InputAutomationModuleOSGIServiceVisitor());
    }
}
