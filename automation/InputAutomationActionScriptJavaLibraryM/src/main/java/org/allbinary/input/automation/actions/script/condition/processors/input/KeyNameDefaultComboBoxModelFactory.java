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

import java.awt.event.KeyEvent;

import javax.swing.*;

import org.allbinary.input.KeySingletonFactory;

public class KeyNameDefaultComboBoxModelFactory
{
    private KeyNameDefaultComboBoxModelFactory()
    {
    }
 
    public static DefaultComboBoxModel getInstance()
    {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();

        Integer[] keyArray = KeySingletonFactory.getArray();

        for(int index = 0; index < keyArray.length; index++)
        {
            String keyAsString = KeyEvent.getKeyText(keyArray[index]);
            defaultComboBoxModel.addElement(keyAsString);
        }

        return defaultComboBoxModel;
    }
}
