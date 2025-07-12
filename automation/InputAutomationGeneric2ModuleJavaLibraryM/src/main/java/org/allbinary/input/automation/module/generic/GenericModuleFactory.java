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

import org.allbinary.input.automation.module.AbstractInputAutomationFactory;
import org.allbinary.input.automation.module.InputAutomationActionInterface;
import org.allbinary.input.automation.module.InputAutomationModuleData;
import org.allbinary.input.automation.module.generic.configuration.GenericModuleConfigurationJPanel;
import org.allbinary.input.automation.module.generic.configuration.profile.GenericProfile;
import org.allbinary.input.automation.module.generic.constraints.NoImageComparatorConstraints;
import org.allbinary.input.automation.module.generic.constraints.NoMotionRectangleConstraints;
import org.allbinary.thread.RunnableInterface;

public class GenericModuleFactory 
    extends AbstractInputAutomationFactory
    //implements InputAutomationModuleFactoryInterface
{
    private static String NAME = "Generic" + InputAutomationModuleData.MODULE_NAME_END;
        
    public GenericModuleFactory() throws Exception
    {
        super(NAME, new GenericModuleConfigurationJPanel());
    }
        
    public synchronized RunnableInterface getInstance() throws Exception
    {
        GenericProfile genericProfile =
            ((GenericModuleConfigurationJPanel) 
            this.getConfigurationJPanel()).getSelectedGenericProfile();
        
        InputAutomationActionInterface inputAutomationActionInterface =
            (InputAutomationActionInterface) new GenericInputAutomationAction();

        return (RunnableInterface)
            new GenericInputAutomationWorker(
                inputAutomationActionInterface, 
                genericProfile,
                new NoMotionRectangleConstraints(), 
                new NoImageComparatorConstraints());
    }
}
