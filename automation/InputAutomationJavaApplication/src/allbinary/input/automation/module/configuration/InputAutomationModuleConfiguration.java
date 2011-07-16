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
package allbinary.input.automation.module.configuration;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.loader.AbeFactory;
import allbinary.business.DynamicObjectData;
import allbinary.data.tree.dom.ModDomHelper;
import allbinary.input.automation.InputAutomationData;
import allbinary.input.automation.module.InputAutomationModuleData;
import allbinary.input.automation.module.InputAutomationModuleFactoryInterface;

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
                
                LogUtil.put("ClassName : " + getClassName(), this, "init");
                
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
                LogUtil.put("Class Node Null", this,"init");
            }
        }
        else
        {
            LogUtil.put(InputAutomationData.NAME + " Node Has No Children", this,"Contructor");
        }
    }
    
    public void init()
    throws Exception
    {
        LogUtil.put("Name : " + getName(), this, "init");
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
