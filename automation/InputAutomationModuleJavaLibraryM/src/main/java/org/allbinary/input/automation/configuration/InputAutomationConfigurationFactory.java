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

import org.allbinary.logic.communication.log.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.allbinary.logic.communication.log.LogFactory;

public class InputAutomationConfigurationFactory
{
    private static InputAutomationConfiguration inputAutomationConfiguration = null;
    
    private InputAutomationConfigurationFactory()
    {
    }
    
    public static void init()
    throws Exception
    {
        final File file = InputAutomationConfiguration.getFile();
        if(file.isFile())
        {
            LogUtil.put(LogFactory.getInstance("LoadingConfiguration", "InputAutomationConfiguration", "init"));
            final JAXBContext jaxbContext = JAXBContext.newInstance(InputAutomationConfiguration.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final JAXBElement<InputAutomationConfiguration> root = unmarshaller.unmarshal(new StreamSource(new FileInputStream(file)), InputAutomationConfiguration.class);
            inputAutomationConfiguration = (InputAutomationConfiguration) 
                    //unmarshaller.unmarshal(file);
                    root.getValue();
            
            LogUtil.put(LogFactory.getInstance("LoadedConfiguration", "InputAutomationConfiguration", "init"));
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("New Configuration", "InputAutomationConfiguration", "init"));
            inputAutomationConfiguration = new InputAutomationConfiguration();
        }
    }
    
    public static InputAutomationConfiguration getInstance()
    {
        return inputAutomationConfiguration;
    }
}
