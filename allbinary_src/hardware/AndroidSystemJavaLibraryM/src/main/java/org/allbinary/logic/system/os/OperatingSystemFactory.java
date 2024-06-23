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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class OperatingSystemFactory
{
    private static final OperatingSystemFactory instance = new OperatingSystemFactory();

    public static OperatingSystemFactory getInstance()
    {
        return instance;
    }
    
    private GenericOperatingSystem GenericOperatingSystem;
    private boolean hasDetected = false;
    
    private OperatingSystemFactory()
    {
    }
    
    public synchronized GenericOperatingSystem getOperatingSystemInstance()
    {
        try
        {
            final SystemProperties systemProperties = SystemProperties.getInstance();
            String osName = systemProperties.getName();
            //String osArch = SystemProperties.getArch();
            //String osVersion = SystemProperties.getVersion();
            
            if(!hasDetected)
            {
                hasDetected = true;
                if(osName.indexOf(OperatingSystems.getInstance().ANDROID) >= 0)
                {
                    LogUtil.put(LogFactory.getInstance("Found a Linux OS", this, CommonStrings.getInstance().GET_INSTANCE));

                    GenericOperatingSystem = 
                        AndroidOperatingSystemFactory.getInstance().getOperatingSystemInstance();
                    
                    //PreLogUtil.put(log.toString());
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("Operating System Info: ").append(GenericOperatingSystem.toString()).toString(), this, CommonStrings.getInstance().GET_INSTANCE));
                }
                else
                {
                    throw new Exception(new StringMaker().append("OS Not Supported: ").append(osName).toString());
                }                
            }            
        }
        catch(Exception e)
        {
            GenericOperatingSystem = new NoOperatingSystem();
            
            String error = "Failed to get instance";
            LogUtil.put(LogFactory.getInstance(error, this, CommonStrings.getInstance().GET_INSTANCE, e));
        }
        
        return GenericOperatingSystem;
    }
}
