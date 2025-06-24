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


import java.util.Vector;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.tree.TreePath;
import org.allbinary.input.automation.actions.script.JTreeInterfaceFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.input.automation.actions.script.ProfileActionScriptItem;
import org.allbinary.input.automation.actions.script.ProfileActionScriptNodeInterface;
import org.allbinary.input.automation.actions.script.condition.processors.ProfileActionScriptProcessorInterface;
import org.allbinary.input.automation.actions.script.condition.processors.input.GenericProfileActionScriptInputData;
import org.allbinary.input.automation.actions.script.condition.processors.input.KeyboardActionScriptInput;
import org.allbinary.input.automation.actions.script.condition.processors.input.MouseActionScriptInput;
import org.allbinary.input.automation.actions.script.condition.processors.input.ProfileActionScriptInputFactory;
import org.allbinary.input.automation.module.actions.script.condition.processors.output.GenericProfileActionScriptOutputData;
import org.allbinary.input.automation.module.actions.script.condition.processors.output.ImageActionScriptOutput;
import org.allbinary.input.automation.module.actions.script.condition.processors.output.ProfileActionScriptOutputFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;

public class BasicProfileActionScriptCondition
    extends ProfileActionScriptItem
    implements ProfileActionScriptConditionInterface
{
    //TWB - add inherited dialog boxes and remove inherited default nodes
    //private BasicProfileActionScriptProcessorNode basicProfileActionScriptProcessorNode;
    private Vector profileActionProcessorInterfaceVector;
    private Vector profileActionConditionInterfaceVector;
    
    public BasicProfileActionScriptCondition(
        String label, Node node)
        throws Exception
    {
        super(label, node);
        
        this.init();
        
        NodeList nodeList = node.getChildNodes();
        for(int index = 0; index < nodeList.getLength(); index++)
        {
            Node actionItemNode = nodeList.item(index);
            
            if(actionItemNode.getNodeType() == Node.ELEMENT_NODE)
            {
                if(actionItemNode.getNodeName().compareTo(
                    GenericProfileActionScriptConditionData.NAME) == 0)
                {
                    this.addCondition(
                        ProfileActionScriptConditionFactory.getInstance(
                        actionItemNode));
                }
                else
                    /*
                    if(actionItemNode.getNodeName().compareTo(
                    GenericProfileActionScriptProcessorData.NAME) == 0)
                    {
                        //basicProfileActionScriptProcessorNode = new BasicProfileActionScriptProcessorNode(node);
                        this.addProcessorNodes(actionItemNode);
                    }
                    */
            if(actionItemNode.getNodeName().compareTo(
                GenericProfileActionScriptInputData.NAME) == 0)
            {
                this.addProcessor(
                    ProfileActionScriptInputFactory.getInstance(
                        actionItemNode));
            }
            else
            if(actionItemNode.getNodeName().compareTo(
                GenericProfileActionScriptOutputData.NAME) == 0)
            {
                this.addProcessor(
                    ProfileActionScriptOutputFactory.getInstance(
                        actionItemNode));
            }
                    
            }
        }
    }
    
    public void addProcessorNodes(Node node)
    throws Exception
    {
        NodeList nodeList = node.getChildNodes();
        for(int index = 0; index < nodeList.getLength(); index++)
        {
            Node processorActionItemNode = nodeList.item(index);
            
            if(processorActionItemNode.getNodeName().compareTo(
                GenericProfileActionScriptInputData.NAME) == 0)
            {
                this.addProcessor(
                    ProfileActionScriptInputFactory.getInstance(
                    processorActionItemNode));
            }
            else
            if(processorActionItemNode.getNodeName().compareTo(
                GenericProfileActionScriptOutputData.NAME) == 0)
            {
                this.addProcessor(
                    ProfileActionScriptOutputFactory.getInstance(
                    processorActionItemNode));
            }
        }
    }
    
    public BasicProfileActionScriptCondition(String label)
    {
        super(label);
        
        this.init();
    }
    
    private void init()
    {
        //this.basicProfileActionScriptProcessorNode = new BasicProfileActionScriptProcessorNode();
        this.setProfileActionProcessorInterfaceVector(new Vector());
        this.setProfileActionConditionInterfaceVector(new Vector());
        this.getJPopupMenu().add(this.getConditionJPopupMenu());
        this.getJPopupMenu().add(this.getInputJPopupMenu());
        this.getJPopupMenu().add(this.getOutputJPopupMenu());
    }
    
    private JMenu getConditionJPopupMenu()
    {
        JMenu jMenu = new JMenu(NEW_CONDITION);
        
        JMenuItem jMenuItemColorAt = new JMenuItem(NEW_COLOR_AT);
        JMenuItem jMenuItemAlways = new JMenuItem(NEW_ALWAYS);
        JMenuItem jMenuItemTimeInterval = new JMenuItem(NEW_TIME_INTERVAL);
        
        jMenuItemColorAt.addActionListener(this);
        jMenuItemAlways.addActionListener(this);
        jMenuItemTimeInterval.addActionListener(this);
        
        jMenu.add(jMenuItemColorAt);
        jMenu.add(jMenuItemAlways);
        jMenu.add(jMenuItemTimeInterval);
        
        return jMenu;
    }
    
    private JMenu getInputJPopupMenu()
    {
        JMenu jMenu = new JMenu(NEW_INPUT);
        
        JMenuItem jMenuItemKey = new JMenuItem(NEW_KEY_INPUT);
        JMenuItem jMenuItemMouse = new JMenuItem(NEW_MOUSE_INPUT);
        JMenuItem jMenuItemJoystick = new JMenuItem(NEW_JOYSTICK_INPUT);
        
        jMenuItemKey.addActionListener(this);
        jMenuItemMouse.addActionListener(this);
        jMenuItemJoystick.addActionListener(this);
        
        jMenu.add(jMenuItemKey);
        jMenu.add(jMenuItemMouse);
        jMenu.add(jMenuItemJoystick);
        
        return jMenu;
    }
    
    private JMenu getOutputJPopupMenu()
    {
        JMenu jMenu = new JMenu(NEW_OUTPUT);
        
        JMenuItem jMenuItemImage = new JMenuItem(NEW_IMAGE_OUTPUT);
        
        jMenuItemImage.addActionListener(this);
        
        jMenu.add(jMenuItemImage);
        
        return jMenu;
    }
    
    public void addProcessor
        (ProfileActionScriptProcessorInterface profileActionProcessorInterface)
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "addInput"));
        this.getProfileActionProcessorInterfaceVector().add(
            profileActionProcessorInterface);
        this.add(profileActionProcessorInterface);
    }
    
    public void removeProcessor(
        ProfileActionScriptProcessorInterface profileActionProcessorInterface)
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "removeInput"));
        this.getProfileActionProcessorInterfaceVector().remove(
            profileActionProcessorInterface);
        this.remove(profileActionProcessorInterface);
    }
    
    public void removeCondition(
        ProfileActionScriptNodeInterface profileActionScriptNodeInterface)
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "removeCondition"));
        this.getProfileActionConditionInterfaceVector().remove(
            profileActionScriptNodeInterface);
        this.remove(profileActionScriptNodeInterface);
    }
    
    
    public void addCondition(
        ProfileActionScriptNodeInterface profileActionScriptNodeInterface)
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "addCondition"));
        this.getProfileActionConditionInterfaceVector().add(
            profileActionScriptNodeInterface);
        this.add(profileActionScriptNodeInterface);
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(
            GenericProfileActionScriptConditionData.NAME);
        
        final Vector profileActionConditionInterfaceVector = this.getProfileActionConditionInterfaceVector();
         
        final int size = profileActionConditionInterfaceVector.size();
        for (int index = 0; index < size; index++)
        {
            ProfileActionScriptConditionInterface profileActionConditionInterface =
                (ProfileActionScriptConditionInterface) profileActionConditionInterfaceVector.get(index);
            
            node.appendChild(profileActionConditionInterface.toXmlNode(document));
        }
        
        final Vector profileActionProcessorInterfaceVector = this.getProfileActionProcessorInterfaceVector();
        final int size2 = profileActionProcessorInterfaceVector.size();
        for (int index = 0; index < size2; index++)
        {
            ProfileActionScriptProcessorInterface profileActionProcessorInterface =
                (ProfileActionScriptProcessorInterface) profileActionProcessorInterfaceVector.get(index);
            
            node.appendChild(profileActionProcessorInterface.toXmlNode(document));
        }
        
        return node;
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
    
    public void actionPerformed(ActionEvent actionEvent)
    {
        try
        {
            super.actionPerformed(actionEvent);
            
            if(actionEvent.getActionCommand().compareTo(DELETE) == 0)
            {
                if(this.getParent() instanceof ProfileActionScriptNodeInterface)
                {
                    ProfileActionScriptNodeInterface
                        profileActionScriptNodeInterface =
                        (ProfileActionScriptNodeInterface)
                        this.getParent();
                    
                    profileActionScriptNodeInterface.removeCondition(this);
                    JTreeInterfaceFactory.getInstance().getJTreeInterface().updateJTree();
                }
            }
            else
                if(actionEvent.getActionCommand().compareTo(NEW_COLOR_AT) == 0)
                {
                this.addCondition(new ColorAtActionScriptCondition());
                this.updateTree();
                }
                else
                    if(actionEvent.getActionCommand().compareTo(NEW_ALWAYS) == 0)
                    {
                this.addCondition(new AlwaysActionScriptCondition());
                this.updateTree();
                    }
                    else
                        if(actionEvent.getActionCommand().compareTo(NEW_TIME_INTERVAL) == 0)
                        {
                this.addCondition(new TimeIntervalActionScriptCondition());
                this.updateTree();
                        }
                        else
                            if(actionEvent.getActionCommand().compareTo(NEW_KEY_INPUT) == 0)
                            {
                this.addProcessor(new KeyboardActionScriptInput());
                this.updateTree();
                            }
                            else
                                if(actionEvent.getActionCommand().compareTo(NEW_MOUSE_INPUT) == 0)
                                {
                this.addProcessor(new MouseActionScriptInput());
                this.updateTree();
                                }
                                else
                                    if(actionEvent.getActionCommand().compareTo(NEW_IMAGE_OUTPUT) == 0)
                                    {
                this.addProcessor(new ImageActionScriptOutput());
                this.updateTree();
                                    }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Error", this, "actionPerformed", e));
        }
    }
    
    public Vector getProfileActionProcessorInterfaceVector()
    {
        return profileActionProcessorInterfaceVector;
    }
    
    public void setProfileActionProcessorInterfaceVector(Vector profileActionProcessorInterfaceVector)
    {
        this.profileActionProcessorInterfaceVector = profileActionProcessorInterfaceVector;
    }
    
    private void updateTree()
    {
        JTreeInterfaceFactory.getInstance().getJTreeInterface().updateJTree();
        TreePath treePath = new TreePath(this);
        JTreeInterfaceFactory.getInstance().getJTreeInterface().getActionScriptJTree().expandPath(treePath);
    }
    
    public boolean shouldProcess(Long frame)
    throws Exception
    {
        final Vector profileActionConditionInterfaceVector = this.getProfileActionConditionInterfaceVector();
        final int size = profileActionConditionInterfaceVector.size();
        for (int index = 0; index < size; index++)
        {
            ProfileActionScriptConditionInterface profileActionScriptConditionInterface =
                (ProfileActionScriptConditionInterface) profileActionConditionInterfaceVector.get(index);
            
            if(!profileActionScriptConditionInterface.shouldProcess(frame))
            {
                return false;
            }
        }
        return true;
    }
    
    //Actually push the automated input into the application
    public void process(Long frame)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
            new StringMaker().append("Start - Processing ").append(
            this.getProfileActionProcessorInterfaceVector().size()).append(
            " inputs").toString(), this, "process"));
        
        final Vector profileActionProcessorInterfaceVector = this.getProfileActionProcessorInterfaceVector();
        final int size = profileActionProcessorInterfaceVector.size();
        for (int index = 0; index < size; index++)
        {
            ProfileActionScriptProcessorInterface profileActionScriptProcessorInterface =
                (ProfileActionScriptProcessorInterface) profileActionProcessorInterfaceVector.get(index);
            
            profileActionScriptProcessorInterface.process(frame);
        }
    }
}
