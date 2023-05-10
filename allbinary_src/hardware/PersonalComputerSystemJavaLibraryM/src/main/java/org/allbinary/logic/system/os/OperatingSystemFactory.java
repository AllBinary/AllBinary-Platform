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

import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigType;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.system.os.linux.LinuxOperatingSystemFactory;
import org.allbinary.logic.system.os.solaris.Solaris;
import org.allbinary.logic.system.os.windows.WindowsOperatingSystemFactory;

public class OperatingSystemFactory
{
    private static final OperatingSystemFactory instance = new OperatingSystemFactory();

    /**
     * @return the instance
     */
    public static OperatingSystemFactory getInstance()
    {
        return instance;
    }
    
    private OperatingSystemInterface operatingSystemInterface;
    private boolean hasDetected = false;
    
    private OperatingSystemFactory()
    {
    }
    
    public synchronized OperatingSystemInterface getOperatingSystemInstance() throws Exception
    {
        try
        {
            final OperatingSystems operatingSystems = OperatingSystems.getInstance();
            final String osName = SystemProperties.getName();
            final String osArch = SystemProperties.getArch();
            final String osVersion = SystemProperties.getVersion();
            
            if(!this.hasDetected)
            {
                this.hasDetected = true;
                if(osName.indexOf(operatingSystems.LINUX) >= 0)
                {
                    if(LogConfigTypes.LOGGING.contains(LogConfigType.FACTORYERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Found a Linux OS", "OperatingSystemsFactory", "getInstance()"));
                    }
                    
                    this.operatingSystemInterface =
                        (OperatingSystemInterface) 
                        LinuxOperatingSystemFactory.getInstance();
                }
                else if(osName.indexOf(operatingSystems.WINDOWS) >= 0)
                {
                    if(LogConfigTypes.LOGGING.contains(LogConfigType.FACTORYERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Found a Windows OS", "OperatingSystemsFactory", "getInstance()"));
                    }
                    this.operatingSystemInterface =
                        (OperatingSystemInterface) 
                        WindowsOperatingSystemFactory.getInstance();
                }
                else if(osName.indexOf(operatingSystems.SOLARIS) >= 0)
                {
                    if(LogConfigTypes.LOGGING.contains(LogConfigType.FACTORYERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Found a Solaris OS", "OperatingSystemsFactory", "getInstance()"));
                    }
                    
                    this.operatingSystemInterface =
                        (OperatingSystemInterface) new Solaris();
                }
                else
                {
                    throw new Exception(new StringMaker().append("OS Not Supported: ").append(osName).toString());
                }
            }
            
            Log log = LogFactory.getInstance(new StringMaker().append("OperatingSystem Info: ").append(this.operatingSystemInterface).toString(), "OperatingSystemFactory", "getInstance()");
            System.out.println(log.toString());
            LogUtil.put(log);
            
            return this.operatingSystemInterface;
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";
            if(LogConfigTypes.LOGGING.contains(LogConfigType.FACTORYERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "getInstance()", e));
            }
            throw e;
        }
    }
}
