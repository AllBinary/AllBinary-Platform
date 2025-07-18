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

import org.allbinary.business.DynamicObjectData;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.automation.InputAutomationData;
import org.allbinary.input.automation.module.InputAutomationModuleData;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.loader.AbeFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="INPUT_AUTOMATION_MODULE")
@XmlType(name="InputAutomationModuleConfiguration")
public class InputAutomationModuleConfiguration
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

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
                logUtil.put("Class Node Null", this,this.commonStrings.INIT);
            }
        }
        else
        {
            logUtil.put(InputAutomationData.NAME + " Node Has No Children", this,"Contructor");
        }
    }
    
    public void init(final AbeClientInformationInterface abeClientInformation)
    {
        try {
            logUtil.put("Name: " + getName(), this, this.commonStrings.INIT);
            logUtil.put("ClassName: " + className, this, this.commonStrings.INIT);

            this.setInputAutomationModuleInterface(
                    (InputAutomationModuleFactoryInterface) AbeFactory.getInstance().getInstance().getInstance(abeClientInformation, getClassName()));

        } catch(Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, this.commonStrings.INIT, e);
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
        logUtil.put("Name: " + name, this, "setName");
        this.name = name;
    }
    
    public String getClassName()
    {
        return className;
    }

    public void setClassName(final String className)
    {
        logUtil.put("ClassName : " + className, this, "setClassName");
        this.className = className;
    }
    
    public InputAutomationModuleFactoryInterface getInputAutomationModuleInterface()
    {
        return inputAutomationModuleInterface;
    }
    
    public void setInputAutomationModuleInterface(InputAutomationModuleFactoryInterface inputAutomationModuleInterface)
    {
        logUtil.put("InputAutomationModuleFactoryInterface : " + inputAutomationModuleInterface, this, "setInputAutomationModuleInterface");
        this.inputAutomationModuleInterface = inputAutomationModuleInterface;
        this.setName(this.inputAutomationModuleInterface.getName());
    }
}
