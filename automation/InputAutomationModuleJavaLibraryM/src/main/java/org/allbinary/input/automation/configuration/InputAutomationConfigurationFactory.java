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
package org.allbinary.input.automation.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfiguration;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class InputAutomationConfigurationFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static InputAutomationConfiguration inputAutomationConfiguration = null;
    
    private InputAutomationConfigurationFactory()
    {
    }
    
    public static void init(final AbeClientInformationInterface abeClientInformation)
    throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final String INPUT_AUTOMATION_CONFIGURATION = "InputAutomationConfiguration";

        final File file = InputAutomationConfiguration.getFile();
        if(file.isFile())
        {
            logUtil.put("LoadingConfiguration", INPUT_AUTOMATION_CONFIGURATION, commonStrings.INIT);
            final JAXBContext jaxbContext = JAXBContext.newInstance(InputAutomationConfiguration.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final JAXBElement<InputAutomationConfiguration> root = unmarshaller.unmarshal(new StreamSource(new FileInputStream(file)), InputAutomationConfiguration.class);
            inputAutomationConfiguration = (InputAutomationConfiguration) 
                    //unmarshaller.unmarshal(file);
                    root.getValue();
            
            final List<InputAutomationModuleConfiguration> inputAutomationModuleConfigurationList = 
                    inputAutomationConfiguration.getInputAutomationModuleConfigurationList();
            
            logUtil.put("isInstalled: " + inputAutomationConfiguration.isInstalled(), INPUT_AUTOMATION_CONFIGURATION, commonStrings.INIT);

            final int size = inputAutomationModuleConfigurationList.size();
            InputAutomationModuleConfiguration inputAutomationModuleConfiguration;
            for (int index = 0; index < size; index++) {
                inputAutomationModuleConfiguration = inputAutomationModuleConfigurationList.get(index);
                inputAutomationModuleConfiguration.init(abeClientInformation);
            }
            
            logUtil.put("LoadedConfiguration", INPUT_AUTOMATION_CONFIGURATION, commonStrings.INIT);
        }
        else
        {
            logUtil.put("New Configuration", INPUT_AUTOMATION_CONFIGURATION, commonStrings.INIT);
            inputAutomationConfiguration = new InputAutomationConfiguration();
        }
    }
    
    public static InputAutomationConfiguration getInstance()
    {
        return inputAutomationConfiguration;
    }
}
