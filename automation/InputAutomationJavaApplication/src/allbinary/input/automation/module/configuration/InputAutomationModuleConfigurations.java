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

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogUtil;
import allbinary.input.automation.module.InputAutomationModuleData;
import allbinary.input.automation.module.NewInputAutomationModulesData;

public class InputAutomationModuleConfigurations
{
    private HashMap hashMap;
        
    public InputAutomationModuleConfigurations(File file)
        throws Exception
    {
        this.setHashMap(new HashMap());
        
        byte bytes[]= new byte[100000];
        FileInputStream idFile = new FileInputStream(file);
        int length = idFile.read(bytes);
        String data = new String(bytes);
        int endIndex = data.lastIndexOf('>');
        
        Document document = DomDocumentHelper.create(
            data.substring(0, endIndex + 1));
        
        NodeList nodeList = document.getElementsByTagName(NewInputAutomationModulesData.NAME);
        if(nodeList.getLength() > 0)
        {
            NodeList nameNodeList = document.getElementsByTagName(InputAutomationModuleData.NAME);
            
            LogUtil.put("Number Of Module(s) Specified: " + nameNodeList.getLength(), this,"Contructor");
            
            for(int index = 0; index < nameNodeList.getLength(); index++)
            {
                Node node = nameNodeList.item(index);
                this.add(new InputAutomationModuleConfiguration(node));
            }
        }
    }

    public InputAutomationModuleConfigurations(Document document)
        throws Exception
    {
        this.setHashMap(new HashMap());
        
        NodeList nameNodeList = document.getElementsByTagName(InputAutomationModuleData.NAME);
        LogUtil.put("Number Of Module(s) Specified: " + nameNodeList.getLength(), this,"Contructor");
        
        for(int index = 0; index < nameNodeList.getLength(); index++)
        {
            Node node = nameNodeList.item(index);
            this.add(new InputAutomationModuleConfiguration(node));
        }
    }

    public InputAutomationModuleConfigurations()
    {
        this.setHashMap(new HashMap());
    }
    
    public void add(
        InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
    {
        this.getHashMap().put(inputAutomationModuleConfiguration.getClassName(), inputAutomationModuleConfiguration);
    }

    public void remove(
        InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
    {
        this.getHashMap().remove(inputAutomationModuleConfiguration.getClassName());
    }
    
    public HashMap getHashMap()
    {
        return hashMap;
    }

    public void setHashMap(HashMap hashMap)
    {
        this.hashMap = hashMap;
    }
}
