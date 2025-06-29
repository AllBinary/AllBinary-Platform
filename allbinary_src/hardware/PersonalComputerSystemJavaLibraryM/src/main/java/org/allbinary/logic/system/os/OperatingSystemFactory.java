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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
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
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private GenericOperatingSystem GenericOperatingSystem;
    private boolean hasDetected = false;
    
    private OperatingSystemFactory()
    {
    }
    
    public synchronized GenericOperatingSystem getOperatingSystemInstance() //throws Exception
    {
        try
        {
            final OperatingSystems operatingSystems = OperatingSystems.getInstance();
            final SystemProperties systemProperties = SystemProperties.getInstance();
            final String osName = systemProperties.getName();
            final String osArch = systemProperties.getArch();
            final String osVersion = systemProperties.getVersion();
            
            if(!this.hasDetected)
            {
                LogUtil.put(LogFactory.getInstance("osName: " + osName, this, commonStrings.GET_INSTANCE));
                
                this.hasDetected = true;
                if(osName.indexOf(operatingSystems.LINUX) >= 0)
                {
                    if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Found a Linux OS", this, commonStrings.GET_INSTANCE));
                    }
                    
                    this.GenericOperatingSystem =
                        (GenericOperatingSystem) 
                        LinuxOperatingSystemFactory.getInstance().getOperatingSystemInstance();
                }
                else if(osName.indexOf(operatingSystems.WINDOWS) >= 0)
                {
                    if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Found a Windows OS", this, commonStrings.GET_INSTANCE));
                    }
                    this.GenericOperatingSystem =
                        (GenericOperatingSystem) 
                        WindowsOperatingSystemFactory.getInstance().getOperatingSystemInstance();
                }
                else if(osName.indexOf(operatingSystems.SOLARIS) >= 0)
                {
                    if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
                    {
                        LogUtil.put(LogFactory.getInstance("Found a Solaris OS", this, commonStrings.GET_INSTANCE));
                    }
                    
                    this.GenericOperatingSystem =
                        (GenericOperatingSystem) new Solaris();
                }
                else
                {
                    throw new Exception(new StringMaker().append("OS Not Supported: ").append(osName).toString());
                }
                
                Log log = LogFactory.getInstance(new StringMaker().append("OperatingSystem Info: ").append(StringUtil.getInstance().toString(this.GenericOperatingSystem)).toString(), this, commonStrings.GET_INSTANCE);
                System.out.println(log.toString());
                LogUtil.put(log);

            }
                        
            return this.GenericOperatingSystem;
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, commonStrings.GET_INSTANCE, e));
            }
            //throw e;
            return new NoOperatingSystem();
        }
    }
}
