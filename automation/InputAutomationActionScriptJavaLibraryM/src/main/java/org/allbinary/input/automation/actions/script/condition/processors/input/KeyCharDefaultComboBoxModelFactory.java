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

import javax.swing.*;

import abbot.tester.KeyStrokeMap;
import org.allbinary.input.KeySingletonFactory;

public class KeyCharDefaultComboBoxModelFactory
{
   private KeyCharDefaultComboBoxModelFactory()
   {
   }
   
   public static DefaultComboBoxModel getInstance()
   {
      DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
      
      Integer[] keyArray = KeySingletonFactory.getArray();
      
      for(int index = 0; index < keyArray.length; index++)
      {
         Integer nextKey = keyArray[index];
         KeyStroke keyStroke = KeyStroke.getKeyStroke(nextKey.intValue(), 0);
         char keyChar = KeyStrokeMap.getChar(keyStroke);
         
         //logUtil.put("Key: " + nextKey + " = " + keyChar, 
           //    "KeyCharDefaultComboBoxModelFactory", commonStrings.GET_INSTANCE);
         
         defaultComboBoxModel.addElement(Character.toString(keyChar));
      }
      
      return defaultComboBoxModel;
   }
}
