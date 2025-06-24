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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.automation.actions.script.condition.processors.BasicProfileActionScriptProcessor;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;

public class BasicProfileActionScriptInput
    extends BasicProfileActionScriptProcessor
    implements ProfileActionScriptInputInterface
{
    private InputRobotInterface inputRobotInterface;
    private int time;
    
    public BasicProfileActionScriptInput(
        final String label, final Node node) 
        throws Exception
    {
        super(label, node);
        
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START + label, this, CommonStrings.getInstance().CONSTRUCTOR));
        
        final Node actionNode = DomSearchHelper.getNode(
            GenericProfileActionScriptInputData.TYPE,
            node.getChildNodes());
        
        final String inputTypeString = DomNodeHelper.getTextNodeValue(actionNode);
        
        final Node timeNode = DomSearchHelper.getNodeNoThrow(
            GenericProfileActionScriptInputData.DELAY,
            node.getChildNodes());

        if(timeNode != null)
        {
            final String delayString = DomNodeHelper.getTextNodeValue(timeNode);
            this.setTime(Integer.valueOf(delayString).intValue());
        }
        else
        {
            this.setTime(0);
        }

        this.setInputRobotInterface(InputRobotFactory.getInstance().get(inputTypeString));
    }
    
    public BasicProfileActionScriptInput(final String label)
        throws Exception
    {
        super(label);
        
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START + label, this, CommonStrings.getInstance().CONSTRUCTOR));
        
        final InputRobotFactory inputRobotFactory = InputRobotFactory.getInstance();
        final Hashtable hashtable = (Hashtable) inputRobotFactory.get();
        final Set set = hashtable.keySet();
        final Iterator iterator = set.iterator();
        this.setInputRobotInterface(inputRobotFactory.get((String) iterator.next()));
        
        this.setTime(0);
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }
    
    /*
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(GenericProfileActionScriptConditionData.NAME);

        return node;
    }
     */
    
    public HashMap BasicProfileActionScriptInput_toHashMap()
    {
        HashMap hashMap = new HashMap();

        hashMap.put(GenericProfileActionScriptInputData.TYPE, this.getInputRobotInterface().getName());
        hashMap.put(GenericProfileActionScriptInputData.DELAY, Integer.toString(this.getTime()));

        LogUtil.put(LogFactory.getInstance("HashMap: " + hashMap.toString(), this, "toHashMap()"));

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = ModDomHelper.createNodeWithValueNodes(
            document, GenericProfileActionScriptInputData.NAME, 
            this.BasicProfileActionScriptInput_toHashMap());

        return node;
    }

    public void setInputRobotInterface(InputRobotInterface inputRobotInterface)
    {
        this.inputRobotInterface = inputRobotInterface;
    }
    
    public InputRobotInterface getInputRobotInterface()
    {
        return inputRobotInterface;
    }
    
   public String toString()
   {
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append(super.toString());
      stringBuffer.append(" Input Type: ");
      if(this.getInputRobotInterface() != null) {
          stringBuffer.append(this.getInputRobotInterface().getName());
      } else {
          stringBuffer.append(StringUtil.getInstance().NULL_STRING);
      }
      stringBuffer.append(" Time: ");
      stringBuffer.append(this.getTime());
      
      return stringBuffer.toString();
   }
    
}
