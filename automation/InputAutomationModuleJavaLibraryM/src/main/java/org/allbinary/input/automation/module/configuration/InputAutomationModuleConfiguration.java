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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="INPUT_AUTOMATION_MODULE")
@XmlType(name="InputAutomationModuleConfiguration")
public class InputAutomationModuleConfiguration
{
    @XmlElement(name="DYNAMICCOMPONENT_NAME")
    private String className;
    
    private String name;

    private InputAutomationModuleFactoryInterface inputAutomationModuleInterface;
    
    public InputAutomationModuleConfiguration() throws Exception {
    }

    public InputAutomationModuleConfiguration(final AbeClientInformationInterface abeClientInformation, final Node node)
        throws Exception
    {
        this.init(abeClientInformation, node);
    }
    
    public InputAutomationModuleConfiguration(final AbeClientInformationInterface abeClientInformation, final String name, final String className)
    throws Exception
    {
        this.setName(name);
        this.setClassName(className);
        
        this.init(abeClientInformation);
    }

    public InputAutomationModuleConfiguration(
        InputAutomationModuleFactoryInterface inputAutomationModuleInterface)
        throws Exception
    {
        this.setInputAutomationModuleInterface(inputAutomationModuleInterface);
        this.setClassName(this.inputAutomationModuleInterface.getClass().getName());
    }
                
    public void init(final AbeClientInformationInterface abeClientInformation, final Node node)
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
                
                                    /*
                    JarFile jarFile = new JarFile(fileName);
                    jarFile.entries();
                    JarEntry jarEntry =
                    jarEntry.getAttributes().
                                     */
                
                this.init(abeClientInformation);
                
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
    
    public void init(final AbeClientInformationInterface abeClientInformation)
    {
        try {
            LogUtil.put(LogFactory.getInstance("Name: " + getName(), this, "init"));
            LogUtil.put(LogFactory.getInstance("ClassName: " + className, this, "init"));

            this.setInputAutomationModuleInterface(
                    (InputAutomationModuleFactoryInterface) AbeFactory.getInstance(abeClientInformation, getClassName()));

        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "init", e));
            throw new RuntimeException();
        }
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
        LogUtil.put(LogFactory.getInstance("Name: " + name, this, "setName"));
        this.name = name;
    }
    
    public String getClassName()
    {
        return className;
    }

    public void setClassName(final String className)
    {
        LogUtil.put(LogFactory.getInstance("ClassName : " + className, this, "setClassName"));
        this.className = className;
    }
    
    public InputAutomationModuleFactoryInterface getInputAutomationModuleInterface()
    {
        return inputAutomationModuleInterface;
    }
    
    public void setInputAutomationModuleInterface(InputAutomationModuleFactoryInterface inputAutomationModuleInterface)
    {
        LogUtil.put(LogFactory.getInstance("InputAutomationModuleFactoryInterface : " + inputAutomationModuleInterface, this, "setInputAutomationModuleInterface"));
        this.inputAutomationModuleInterface = inputAutomationModuleInterface;
        this.setName(this.inputAutomationModuleInterface.getName());
    }
}
