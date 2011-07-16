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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.input;

import allbinary.input.automation.robot.InputRobotFactory;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;

public class InputAutomationTypeDefaultComboBoxModelFactory
{
    private InputAutomationTypeDefaultComboBoxModelFactory()
    {
    }
    
    public static DefaultComboBoxModel getInstance()
        throws Exception
    {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        
        Set set = InputRobotFactory.getInstance().get().keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            defaultComboBoxModel.addElement(
                (String) iterator.next());
        }
        
        return defaultComboBoxModel;
    }
}
