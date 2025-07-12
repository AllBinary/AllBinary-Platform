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

package org.allbinary.input.automation.actions.script.condition.processors.input;


import java.util.Set;

import javax.swing.*;

import org.allbinary.input.automation.robot.InputRobotFactory;

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

        final Object[] nameArray = set.toArray();
        final int size = nameArray.length;
        for(int index = 0; index < size; index++)
        {
            defaultComboBoxModel.addElement((String) nameArray[index]);
        }
        
        return defaultComboBoxModel;
    }
}
