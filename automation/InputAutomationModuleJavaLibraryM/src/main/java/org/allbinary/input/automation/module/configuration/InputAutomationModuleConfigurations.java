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

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.input.automation.module.InputAutomationModuleData;
import org.allbinary.input.automation.module.NewInputAutomationModulesData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InputAutomationModuleConfigurations
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private HashMap hashMap;
        
    public InputAutomationModuleConfigurations(final AbeClientInformationInterface abeClientInformation, final File file)
        throws Exception
    {
        this.setHashMap(new HashMap());
        
        final byte bytes[]= new byte[100000];
        final FileInputStream idFile = new FileInputStream(file);
        final int length = idFile.read(bytes);
        final String data = new String(bytes);
        final int endIndex = data.lastIndexOf('>');
        
        final Document document = DomDocumentHelper.create(data.substring(0, endIndex + 1));
        
        final NodeList nodeList = document.getElementsByTagName(NewInputAutomationModulesData.NAME);
        if(nodeList.getLength() > 0)
        {
            final NodeList nameNodeList = document.getElementsByTagName(InputAutomationModuleData.NAME);
            
            logUtil.put("Number Of Module(s) Specified: " + nameNodeList.getLength(), this,"Contructor");
            
            for(int index = 0; index < nameNodeList.getLength(); index++)
            {
                final Node node = nameNodeList.item(index);
                this.add(new InputAutomationModuleConfiguration(abeClientInformation, node));
            }
        }
    }

    public InputAutomationModuleConfigurations(final AbeClientInformationInterface abeClientInformation, final Document document)
        throws Exception
    {
        this.setHashMap(new HashMap());
        
        final NodeList nameNodeList = document.getElementsByTagName(InputAutomationModuleData.NAME);
        logUtil.put("Number Of Module(s) Specified: " + nameNodeList.getLength(), this,"Contructor");
        
        for(int index = 0; index < nameNodeList.getLength(); index++)
        {
            Node node = nameNodeList.item(index);
            this.add(new InputAutomationModuleConfiguration(abeClientInformation, node));
        }
    }

    public InputAutomationModuleConfigurations(final List<InputAutomationModuleConfiguration> inputAutomationModuleConfigurationList)
    {
        this.setHashMap(new HashMap());
        
        final int size = inputAutomationModuleConfigurationList.size();
        InputAutomationModuleConfiguration inputAutomationModuleConfiguration;
        for(int index = 0; index < size; index++) {
            inputAutomationModuleConfiguration = inputAutomationModuleConfigurationList.get(index);
            this.add(inputAutomationModuleConfiguration);
        }
    }
    
    public InputAutomationModuleConfigurations()
    {
        this.setHashMap(new HashMap());
    }
    
    public void add(final InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
    {
        this.getHashMap().put(inputAutomationModuleConfiguration.getClassName(), inputAutomationModuleConfiguration);
    }

    public void remove(final InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
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
