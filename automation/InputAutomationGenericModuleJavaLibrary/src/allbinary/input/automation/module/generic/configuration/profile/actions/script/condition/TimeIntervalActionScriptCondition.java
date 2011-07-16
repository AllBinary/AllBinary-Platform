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
package allbinary.input.automation.module.generic.configuration.profile.actions.script.condition;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import allbinary.data.tree.dom.ModDomHelper;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.time.TimeHelper;

public class TimeIntervalActionScriptCondition
    extends BasicProfileActionScriptCondition
    implements TimeIntervalActionScriptConditionInterface
{
    private TimeIntervalActionScriptConditionJPanel timeIntervalActionScriptConditionJPanel;
    
    private TimeHelper timeHelper;

    private final static String NAME = "Time Interval";
    
    public TimeIntervalActionScriptCondition(Node node)
        throws Exception
    {
        super(TimeIntervalActionScriptCondition.NAME, node);
        
        Node actionNode = DomSearchHelper.getNode(
            TimeIntervalActionScriptConditionData.NAME,
            node.getChildNodes());

        if(actionNode != null)
        {
            NodeList nodeList = actionNode.getChildNodes();
            
            for(int index = 0; index < nodeList.getLength(); index++)
            {
                Node childNode = nodeList.item(index);

                if(childNode.getNodeName().compareTo(TimeIntervalActionScriptConditionData.TIME) == 0)
                {
                    String interval = DomNodeHelper.getTextNodeValue(childNode);
                    this.setTimeHelper(new TimeHelper(Integer.valueOf(interval)));
                }
                else
                {
                    throw new Exception("Time Interval Action Script Condition Unknown Node");
                }
            }
        }
        else
        {
            throw new Exception("Time Interval Action Script Condition Node Null");
        }
        this.init();
    }
    
    public TimeIntervalActionScriptCondition()
    {
        super(TimeIntervalActionScriptCondition.NAME);

        this.setTimeHelper(new TimeHelper(0));
        
        this.init();
    }

    private void init()
    {
        this.timeIntervalActionScriptConditionJPanel = 
            new TimeIntervalActionScriptConditionJPanel(this);
    }

    public void showDialog()
    {
        this.timeIntervalActionScriptConditionJPanel.getTimeIntervalActionJDialog().setVisible(true);
    }
    
    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();

        hashMap.put(TimeIntervalActionScriptConditionData.TIME, 
            Integer.toString(this.getTimeHelper().getDelay()));

        LogUtil.put("HashMap: " + hashMap.toString(), this, "toHashMap()");

        return hashMap;
    }

    public Node toXmlNode(Document document) throws Exception
    {
        Node node = super.toXmlNode(document);
        
        node.appendChild(ModDomHelper.createNodeWithValueNodes(
            document, TimeIntervalActionScriptConditionData.NAME, this.toHashMap()));
        
        return node;
    }
    
    public void log()
    {
        LogUtil.put(new Log("Time Interval: " + this.getTimeHelper().getDelay(), this, "log"));
    }

    public TimeHelper getTimeHelper()
    {
        return timeHelper;
    }

    public void setTimeHelper(TimeHelper timeHelper)
    {
        this.timeHelper = timeHelper;
    }
    
    public boolean shouldProcess(Long frame)
    throws Exception
    {
        TimeHelper timeHelper = this.getTimeHelper();
        if(timeHelper.isTime())
        {
            timeHelper.setStartTime();
            return super.shouldProcess(frame);
        }
        return false;
    }
}
