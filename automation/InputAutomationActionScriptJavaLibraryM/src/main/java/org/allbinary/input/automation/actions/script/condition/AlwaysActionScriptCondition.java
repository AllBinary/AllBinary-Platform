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
package org.allbinary.input.automation.actions.script.condition;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AlwaysActionScriptCondition
    extends BasicProfileActionScriptCondition
    implements AlwaysActionScriptConditionInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private AlwaysActionScriptConditionJPanel alwaysActionScriptConditionJPanel;
    
    private boolean isOn;

    private final static String NAME = "Always On/Off";

    public AlwaysActionScriptCondition(Node node)
        throws Exception
    {
        super(AlwaysActionScriptCondition.NAME, node);
        
        Node actionNode = DomSearchHelper.getNode(
            AlwaysActionScriptConditionData.NAME,
            node.getChildNodes());

        if(actionNode != null)
        {
            NodeList nodeList = actionNode.getChildNodes();
            
            for(int index = 0; index < nodeList.getLength(); index++)
            {
                Node childNode = nodeList.item(index);

                if(childNode.getNodeName().compareTo(AlwaysActionScriptConditionData.IS_ON) == 0)
                {
                    String booleanString = DomNodeHelper.getTextNodeValue(childNode);
                    this.setIsOn(new Boolean(booleanString).booleanValue());
                }
                else
                {
                    throw new Exception("Action Script Condition Unknown Node");
                }
            }
        }
        else
        {
            throw new Exception("Action Script Condition Node Null");
        }
        this.init();
    }
    
    public AlwaysActionScriptCondition()
    {
        super(AlwaysActionScriptCondition.NAME);
        this.init();
    }

    private void init()
    {
        this.alwaysActionScriptConditionJPanel = 
            new AlwaysActionScriptConditionJPanel(this);
    }

    public boolean isIsOn()
    {
        return isOn;
    }

    public void setIsOn(boolean isOn)
    {
        this.isOn = isOn;
    }

    public void showDialog()
    {
        this.alwaysActionScriptConditionJPanel.getAlwaysActionJDialog().setVisible(true);
    }
    
    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();

        hashMap.put(AlwaysActionScriptConditionData.IS_ON, Boolean.toString(this.isIsOn()));

        logUtil.put("HashMap: " + hashMap.toString(), this, "toHashMap()");

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = super.toXmlNode(document);
        
        node.appendChild(ModDomHelper.createNodeWithValueNodes(
            document, AlwaysActionScriptConditionData.NAME, this.toHashMap()));
        
        return node;
    }

    public boolean shouldProcess(Long frame)
    throws Exception
    {
        if(this.isIsOn())
        {
            return super.shouldProcess(frame);
        }
        return false;
    }
    
    public void log()
    {
        logUtil.put("Is On: " + this.isIsOn(), this, "log");
    }
}
