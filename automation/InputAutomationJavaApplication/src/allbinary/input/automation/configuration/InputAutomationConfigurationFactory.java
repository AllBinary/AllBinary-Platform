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
package allbinary.input.automation.configuration;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class InputAutomationConfigurationFactory
{
    private static InputAutomationConfiguration inputAutomationConfiguration = null;
    
    private InputAutomationConfigurationFactory()
    {
    }
    
    public static void init()
    throws Exception
    {
        File file = InputAutomationConfiguration.getFile();
        if(file.isFile())
        {
            LogUtil.put(new Log("Loaded Configuration", "InputAutomationConfiguration", "init"));
            JAXBContext jaxbContext = JAXBContext.newInstance(
                InputAutomationConfiguration.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            inputAutomationConfiguration =
                (InputAutomationConfiguration) unmarshaller.unmarshal(file);
        }
        else
        {
            LogUtil.put(new Log("New Configuration", "InputAutomationConfiguration", "init"));
            inputAutomationConfiguration = new InputAutomationConfiguration();
        }
    }
    
    public static InputAutomationConfiguration getInstance()
    {
        return inputAutomationConfiguration;
    }
}
