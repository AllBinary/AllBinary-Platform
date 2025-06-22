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
import org.allbinary.string.CommonStrings;

public class InputAutomationModuleOSGIServiceVisitor
    implements OSGIServiceVisitorInterface
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public InputAutomationModuleOSGIServiceVisitor()
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
            final InputAutomationModuleService inputAutomationModuleService =
                (InputAutomationModuleService) osgiServiceInterface;
            
            final InputAutomationModuleFactoryInterface[] inputAutomationModuleInterfaceArray =
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
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "visit", e));
            return Boolean.FALSE;
        }
    }
    
}
