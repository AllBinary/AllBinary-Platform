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
package org.allbinary.logic.system.os;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.os.linux.LinuxOperatingSystemFactory;
import org.allbinary.logic.system.os.solaris.Solaris;
import org.allbinary.logic.system.os.windows.WindowsOperatingSystemFactory;

public class OperatingSystemFactory
{
    private static OperatingSystemInterface operatingSystemInterface;
    private static boolean hasDetected = false;
    
    private OperatingSystemFactory()
    {
    }
    
    public static synchronized OperatingSystemInterface getInstance() throws Exception
    {
        try
        {
            String osName = SystemProperties.getName();
            String osArch = SystemProperties.getArch();
            String osVersion = SystemProperties.getVersion();
            
            if(!OperatingSystemFactory.hasDetected)
            {
                OperatingSystemFactory.hasDetected = true;
                if(osName.indexOf(OperatingSystems.LINUX) >= 0)
                {
                    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
                    {
                        LogUtil.put(new Log("Found a Linux OS", "OperatingSystemsFactory", "getInstance()"));
                    }
                    
                    OperatingSystemFactory.operatingSystemInterface =
                        (OperatingSystemInterface) 
                        LinuxOperatingSystemFactory.getInstance();
                }
                else if(osName.indexOf(OperatingSystems.WINDOWS) >= 0)
                {
                    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
                    {
                        LogUtil.put(new Log("Found a Windows OS", "OperatingSystemsFactory", "getInstance()"));
                    }
                    OperatingSystemFactory.operatingSystemInterface =
                        (OperatingSystemInterface) 
                        WindowsOperatingSystemFactory.getInstance();
                }
                else if(osName.indexOf(OperatingSystems.SOLARIS) >= 0)
                {
                    if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
                    {
                        LogUtil.put(new Log("Found a Solaris OS", "OperatingSystemsFactory", "getInstance()"));
                    }
                    
                    OperatingSystemFactory.operatingSystemInterface =
                        (OperatingSystemInterface) new Solaris();
                }
                else
                {
                    throw new Exception("OS Not Supported: " + osName);
                }
            }
            
            Log log = new Log("OperatingSystem Info: " + OperatingSystemFactory.operatingSystemInterface, "OperatingSystemFactory", "getInstance()");
            System.out.println(log.toString());
            LogUtil.put(log);
            
            return OperatingSystemFactory.operatingSystemInterface;
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
            {
                LogUtil.put(error, "OperatingSystemsFactory", "getInstance()", e);
            }
            throw e;
        }
    }
}
