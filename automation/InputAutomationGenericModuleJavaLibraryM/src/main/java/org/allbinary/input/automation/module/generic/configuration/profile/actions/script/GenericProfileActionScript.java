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
package org.allbinary.input.automation.module.generic.configuration.profile.actions.script;

import org.allbinary.input.automation.actions.script.ProfileActionScriptNodeInterface;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomSearchHelper;

import org.allbinary.input.automation.actions.script.condition.ProfileActionScriptConditionFactory;
import org.allbinary.input.automation.actions.script.condition.ProfileActionScriptConditionInterface;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActionData;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActionJPanel;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GenericProfileActionScript
    extends DefaultMutableTreeNode
    implements ProfileActionScriptNodeInterface
{
    private GenericProfileActionJPanel genericProfileActionJPanel;
    
    private Vector profileActionConditionInterfaceVector;

    private final static String NAME = "Root";
    
    public GenericProfileActionScript(
        GenericProfileActionJPanel genericProfileActionJPanel,
        Node node) throws Exception
    {
        super(NAME);

        this.genericProfileActionJPanel = genericProfileActionJPanel;
        
        this.setProfileActionConditionInterfaceVector(new Vector());

        Node actionScriptNode = DomSearchHelper.getNode(
            GenericProfileActionData.SCRIPT,
            node.getChildNodes());

        if(actionScriptNode != null)
        {
            NodeList nodeList = actionScriptNode.getChildNodes();

            for(int index = 0; index < nodeList.getLength(); index++)
            {
                Node actionConditionNode = nodeList.item(index);

                if(actionConditionNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    this.addCondition(
                        ProfileActionScriptConditionFactory.getInstance(
                            actionConditionNode));
                }
            }
        }
        else
        {
            throw new Exception("Profile Action Script Node Null");
        }
    }

    public GenericProfileActionScript(
        GenericProfileActionJPanel genericProfileActionJPanel) 
        throws Exception
    {
        this.genericProfileActionJPanel = genericProfileActionJPanel;
        this.setProfileActionConditionInterfaceVector(new Vector());
    }

    public void addCondition(
        ProfileActionScriptNodeInterface profileActionScriptNodeInterface)
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "addCondition"));
        this.getProfileActionConditionInterfaceVector().add(
            profileActionScriptNodeInterface);
        this.add(profileActionScriptNodeInterface);
    }

    public void removeCondition(
        ProfileActionScriptNodeInterface profileActionScriptNodeInterface)
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "removeCondition"));
        this.getProfileActionConditionInterfaceVector().remove(
            profileActionScriptNodeInterface);
        this.remove(profileActionScriptNodeInterface);
    }
    
    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();

        LogUtil.put(LogFactory.getInstance("HashMap: " + hashMap.toString(), this, "toHashMap()"));
        
        return hashMap;
    }
    
    public Vector getProfileActionConditionInterfaceVector()
    {
        return profileActionConditionInterfaceVector;
    }

    public void setProfileActionConditionInterfaceVector(
        Vector profileActionConditionInterfaceVector)
    {
        this.profileActionConditionInterfaceVector = 
            profileActionConditionInterfaceVector;
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(GenericProfileActionData.SCRIPT);

        Iterator iterator = this.getProfileActionConditionInterfaceVector().iterator();
        while(iterator.hasNext())
        {
            ProfileActionScriptConditionInterface profileActionConditionInterface =
                (ProfileActionScriptConditionInterface) iterator.next();

            node.appendChild(profileActionConditionInterface.toXmlNode(document));
        }

        return node;
    }
}
