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

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.input.automation.InputAutomationJFrame;
import allbinary.input.automation.configuration.InputAutomationConfigurationUtil;
import allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import allbinary.osgi.OSGIServiceInterface;
import allbinary.osgi.OSGIServiceVisitorInterface;
import bundle.input.automation.module.InputAutomationModuleService;

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
            LogUtil.put(new Log("Exception", this, "visit", e));
            return Boolean.FALSE;
        }
    }
    
}
