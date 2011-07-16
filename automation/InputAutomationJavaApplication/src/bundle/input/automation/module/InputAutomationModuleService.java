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
package bundle.input.automation.module;

import allbinary.input.automation.module.InputAutomationModuleFactoryInterface;

public class InputAutomationModuleService 
    implements InputAutomationModuleServiceInterface
{
    private InputAutomationModuleFactoryInterface inputAutomationModuleInterfaceArray[];
    
    public InputAutomationModuleService(
        InputAutomationModuleFactoryInterface inputAutomationModuleInterfaceArray[])
    {
        this.setInputAutomationModuleInterfaceArray(
            inputAutomationModuleInterfaceArray);
    }

    public InputAutomationModuleFactoryInterface[] getInputAutomationModuleInterfaceArray()
    {
        return inputAutomationModuleInterfaceArray;
    }

    public void setInputAutomationModuleInterfaceArray(
        InputAutomationModuleFactoryInterface[] inputAutomationModuleInterfaceArray)
    {
        this.inputAutomationModuleInterfaceArray = inputAutomationModuleInterfaceArray;
    }
    
}
