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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.data.tree.dom.ModDomHelper;

import allbinary.input.automation.robot.InputRobotFactory;
import allbinary.input.automation.robot.InputRobotInterface;
import allbinary.input.automation.module.generic.configuration.profile.actions.script.condition.processors.BasicProfileActionScriptProcessor;

abstract public class BasicProfileActionScriptInput
    extends BasicProfileActionScriptProcessor
    implements ProfileActionScriptInputInterface
{
    private InputRobotInterface inputRobotInterface;
    private int time;
    
    public BasicProfileActionScriptInput(
        String label, Node node) 
        throws Exception
    {
        super(label, node);
              
        Node actionNode = DomSearchHelper.getNode(
            GenericProfileActionScriptInputData.TYPE,
            node.getChildNodes());
        
        String inputTypeString = 
            DomNodeHelper.getTextNodeValue(actionNode);
        
        Node timeNode = DomSearchHelper.getNodeNoThrow(
            GenericProfileActionScriptInputData.DELAY,
            node.getChildNodes());

        if(timeNode != null)
        {
        String delayString = 
            DomNodeHelper.getTextNodeValue(timeNode);
        this.setTime(Integer.valueOf(delayString).intValue());
        }
        else
        {
            this.setTime(0);
        }
        
        Hashtable hashtable = (Hashtable) InputRobotFactory.getInstance().get();
        this.setInputRobotInterface(
              (InputRobotInterface) hashtable.get(inputTypeString));
    }
    
    public BasicProfileActionScriptInput(String label)
        throws Exception
    {
        super(label);
        
        Hashtable hashtable = (Hashtable) InputRobotFactory.getInstance().get();
        Set set = hashtable.keySet();
        Iterator iterator = set.iterator();
        this.setInputRobotInterface(
              (InputRobotInterface) hashtable.get(iterator.next()));
        
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

        LogUtil.put(new Log("HashMap: " + hashMap.toString(), this, "toHashMap()"));

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
      stringBuffer.append(this.getInputRobotInterface().getName());
      stringBuffer.append(" Time: ");
      stringBuffer.append(this.getTime());
      
      return stringBuffer.toString();
   }
    
}
