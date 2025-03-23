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
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.KeySingletonFactory;
import org.allbinary.input.KeyUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;

public class KeyboardActionScriptInput extends BasicProfileActionScriptInput
      implements KeyboardActionScriptInputInterface
{
   private Integer[] keyArray = {KeySingletonFactory.getInstance(KeyEvent.VK_0)};
   private static final String NAME = "Keyboard";
   private KeyboardActionScriptInputJPanel keyboardActionScriptInputJPanel;
   
   private boolean press;
   private boolean release;
   private int delayBetweenKeys;
   
   public KeyboardActionScriptInput(Node node) throws Exception
   {
      super(NAME, node);
      
      LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR));
      
      Node actionNode = DomSearchHelper.getNode(
            KeyboardActionScriptInputData.NAME, node.getChildNodes());
      
      if (actionNode != null)
      {
         NodeList nodeList = actionNode.getChildNodes();
         
         Vector vector = new Vector();
         
         for (int index = 0; index < nodeList.getLength();
         index++)
         {
            Node childNode = nodeList.item(index);
            
            if (childNode.getNodeName().
                  startsWith(KeyboardActionScriptInputData.KEY))
            {
               String keyString =
                     DomNodeHelper.getTextNodeValue(childNode);
               vector.add(Integer.valueOf(keyString));
            }
            else if (childNode.getNodeName().
                  compareTo(KeyboardActionScriptInputData.DELAY_BETWEEN_KEYS) == 0)
            {
               String time =
                     DomNodeHelper.getTextNodeValue(childNode);
               this.setDelayBetweenKeys(Integer.valueOf(time).intValue());
            }
            else if (childNode.getNodeName().
                  compareTo(KeyboardActionScriptInputData.PRESS) == 0)
            {
               String value = DomNodeHelper.getTextNodeValue(childNode);
               this.setPress(Boolean.valueOf(value));
            }
            else if (childNode.getNodeName().
                  compareTo(KeyboardActionScriptInputData.RELEASE) == 0)
            {
               String value = DomNodeHelper.getTextNodeValue(childNode);
               this.setRelease(Boolean.valueOf(value));
            }
            else
            {
               throw new Exception("Action Script Input Unknown Node");
            }
         }
         this.setKeyArray((Integer[]) vector.toArray(new Integer[vector.size()]));
      }
      else
      {
         throw new Exception("Action Script Input Node Null");
      }
      
      this.setAllowsChildren(false);
      
      if(!this.isPress() && !this.isRelease())
      {
         this.setNormal();
      }
      
      this.keyboardActionScriptInputJPanel =
            new KeyboardActionScriptInputJPanel(this);
   }
   
   public KeyboardActionScriptInput() throws Exception
   {
      super(NAME);

      if(!this.isPress() && !this.isRelease())
      {
         this.setNormal();
      }
      
      this.setAllowsChildren(false);
      
      this.setTime(120);
      
      this.keyboardActionScriptInputJPanel =
            new KeyboardActionScriptInputJPanel(this);
   }
   
   public int getDelayBetweenKeys()
   {
      return this.delayBetweenKeys;
   }
   
   public void setDelayBetweenKeys(int delayBetweenKeys)
   {
      this.delayBetweenKeys = delayBetweenKeys;
   }
   
   public boolean isPress()
   {
      return this.press;
   }
   
   public void setPress(boolean press)
   {
      this.press = press;
   }
   
   public boolean isRelease()
   {
      return this.release;
   }
   
   public void setRelease(boolean release)
   {
      this.release = release;
   }
   
   public boolean isNormal()
   {
      return this.isRelease() && this.isPress();
   }
   
   public void setNormal()
   {
      this.setPress(true);
      this.setRelease(true);
   }
   
   public Integer[] getKeyArray()
   {
      return keyArray;
   }
   
   public void setKeyArray(Integer[] keyArray)
   {
      if(keyArray != null)
      LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + keyArray.length, "KeyboardActionScriptInput", "setKeyArray"));
      
      this.keyArray = keyArray;
   }
   
   public void showDialog()
   {
      this.keyboardActionScriptInputJPanel.getKeyActionJDialog().
            setVisible(true);
   }
   
   public String getText()
   {
      return getText(this.getKeyArray());
   }
   
   public void setText(String text)
   {
      LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + text, "KeyboardActionScriptInput", "setText"));
      Integer[] integerArray =
            KeyboardActionScriptInput.integerArrayValue(text);
      this.setKeyArray(integerArray);
   }
   
   private static String getText(Integer[] integerArray)
   {
      int index = 0;
      StringBuffer stringBuffer = new StringBuffer();
      
      while(index < integerArray.length)
      {
         Integer nextInteger = integerArray[index];

         if(KeyUtil.isNormallyDisplayed(nextInteger))
         {
            stringBuffer.append(KeyEvent.getKeyText(nextInteger));
         }
         else
         {
            stringBuffer.append("&#" + nextInteger + ";");
         }
         
         index++;
      }
      
      return stringBuffer.toString();
   }
   
   private static Integer[] integerArrayValue(String text)
   {
      LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + text, "KeyboardActionScriptInput", "integerArrayValue"));
      
      Vector vector = new Vector();
      int index = 0;
      while (index < text.length())
      {
         char aChar = text.charAt(index);
         if (aChar == '&')
         {
            if (text.charAt(index + 1) == '#')
            {
               int endIndex = text.indexOf(';', index + 1);
               if (endIndex != -1)
               {
                  String nextCharString = text.substring(index + 2, endIndex);

                  LogUtil.put(LogFactory.getInstance("Next Char String: " + nextCharString, "KeyboardActionScriptInput", "integerArrayValue"));
                  
                  vector.add(Integer.valueOf(
                        nextCharString.substring(index, nextCharString.length())));
                  
                  index = index + nextCharString.length() + 3;
                  continue;
               }
            }
         }
         
         vector.add(
             KeySingletonFactory.getHashtable().get(
                 String.valueOf(aChar)));

         index++;
      }

      Integer[] integerArray = (Integer[]) 
         vector.toArray(new Integer[vector.size()]);
      
      return integerArray;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      //super.toHashMap().put(KeyActionScriptInputData.NAME, hashMap);
      for (int index = 0; index < this.getKeyArray().length;
      index++)
      {
         hashMap.put(KeyboardActionScriptInputData.KEY + index,
               Integer.toString(this.getKeyArray()[index]));
      }
      
      hashMap.put(KeyboardActionScriptInputData.DELAY_BETWEEN_KEYS,
            Integer.toString(this.getDelayBetweenKeys()));

      hashMap.put(KeyboardActionScriptInputData.PRESS, Boolean.toString(this.isPress()));

      hashMap.put(KeyboardActionScriptInputData.RELEASE, Boolean.toString(this.isRelease()));
      
      LogUtil.put(LogFactory.getInstance("HashMap: " + hashMap.toString(), this, "toHashMap()"));
      
      return hashMap;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = super.toXmlNode(document);
      
      node.appendChild(ModDomHelper.createNodeWithValueNodes(document,
            KeyboardActionScriptInputData.NAME, this.toHashMap()));
      
      return node;
   }
   
   public void process(Long frame) throws Exception
   {
      KeyboardInputAutomationProcessor.process(this);
   }
   
   public void log()
   {
      LogUtil.put(LogFactory.getInstance(this.toString(), this, "log"));
   }
   
   public String toString()
   {
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append(super.toString());
      stringBuffer.append(" Text: ");
      stringBuffer.append(this.getText());
      stringBuffer.append(" isPress: ");
      stringBuffer.append(this.isPress());
      stringBuffer.append(" isRelease: ");
      stringBuffer.append(this.isRelease());
      stringBuffer.append(" Between Key Delay: ");
      stringBuffer.append(this.getDelayBetweenKeys());
      
      return stringBuffer.toString();
   }
}