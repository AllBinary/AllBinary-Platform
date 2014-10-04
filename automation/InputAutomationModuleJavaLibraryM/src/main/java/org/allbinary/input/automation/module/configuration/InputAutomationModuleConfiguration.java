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
package org.allbinary.input.automation.module.configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.business.DynamicObjectData;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.automation.InputAutomationData;
import org.allbinary.input.automation.module.InputAutomationModuleData;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import org.allbinary.logic.communication.log.LogFactory;

public class InputAutomationModuleConfiguration
{
    private String name;
    private String className;
    private InputAutomationModuleFactoryInterface inputAutomationModuleInterface;
    
    public InputAutomationModuleConfiguration(
        Node node)
        throws Exception
    {
        this.init(node);
    }
    
    public InputAutomationModuleConfiguration(String name, String className)
    throws Exception
    {
        this.setName(name);
        this.setClassName(className);
        
        this.init();
    }

    public InputAutomationModuleConfiguration(
        InputAutomationModuleFactoryInterface inputAutomationModuleInterface)
        throws Exception
    {
        this.inputAutomationModuleInterface = inputAutomationModuleInterface;
        this.setName(this.inputAutomationModuleInterface.getName());
        this.setClassName(
            this.inputAutomationModuleInterface.getClass().getName());
    }
                
    public void init(Node node)
    throws Exception
    {
        NodeList nodeList = node.getChildNodes();
        
        if(nodeList!=null)
        {
            Node classNameNode =
                DomSearchHelper.getNode(DynamicObjectData.NAME, nodeList);
            
            if(classNameNode!=null)
            {
                this.setClassName(DomNodeHelper.getTextNodeValue(classNameNode));
                
                LogUtil.put(LogFactory.getInstance("ClassName : " + getClassName(), this, "init"));
                
                                    /*
                    JarFile jarFile = new JarFile(fileName);
                    jarFile.entries();
                    JarEntry jarEntry =
                    jarEntry.getAttributes().
                                     */
                
                this.setName(getInputAutomationModuleInterface().getName());
                
                this.init();
                
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("Class Node Null", this,"init"));
            }
        }
        else
        {
            LogUtil.put(LogFactory.getInstance(InputAutomationData.NAME + " Node Has No Children", this,"Contructor"));
        }
    }
    
    public void init()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Name : " + getName(), this, "init"));
        this.setInputAutomationModuleInterface(
            (InputAutomationModuleFactoryInterface)
            AbeFactory.getInstance(getClassName()));
    }
    
    public Node toDomNode(Document document)
    throws Exception
    {
        Node node = document.createElement(InputAutomationModuleData.NAME);
        Node classNameNode = ModDomHelper.createTextNode(document,
            DynamicObjectData.NAME, this.getClassName());
        node.appendChild(classNameNode);
        
        return node;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getClassName()
    {
        return className;
    }
    
    public void setClassName(String className)
    {
        this.className = className;
    }
    
    public InputAutomationModuleFactoryInterface getInputAutomationModuleInterface()
    {
        return inputAutomationModuleInterface;
    }
    
    public void setInputAutomationModuleInterface(InputAutomationModuleFactoryInterface inputAutomationModuleInterface)
    {
        this.inputAutomationModuleInterface = inputAutomationModuleInterface;
    }
}
