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

import abbot.tester.KeyStrokeMap;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;

import allbinary.input.KeySingletonFactory;
import javax.swing.KeyStroke;

public class KeyCharDefaultComboBoxModelFactory
{
   private KeyCharDefaultComboBoxModelFactory()
   {
   }
   
   public static DefaultComboBoxModel getInstance()
   {
      DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
      
      Integer keyArray[] = KeySingletonFactory.getArray();
      
      for(int index = 0; index < keyArray.length; index++)
      {
         Integer nextKey = keyArray[index];
         KeyStroke keyStroke = KeyStroke.getKeyStroke(nextKey.intValue(), 0);
         char keyChar = KeyStrokeMap.getChar(keyStroke);
         
         //LogUtil.put(new Log("Key: " + nextKey + " = " + keyChar, 
           //    "KeyCharDefaultComboBoxModelFactory", "getInstance"));
         
         defaultComboBoxModel.addElement(Character.toString(keyChar));
      }
      
      return defaultComboBoxModel;
   }
}
