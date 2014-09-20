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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class OperatingSystemFactory
{
    private static final OperatingSystemFactory instance = new OperatingSystemFactory();

    public static OperatingSystemFactory getInstance()
    {
        return instance;
    }
    
    private OperatingSystemInterface operatingSystemInterface;
    private boolean hasDetected = false;
    
    private OperatingSystemFactory()
    {
    }
    
    public synchronized OperatingSystemInterface getOperatingSystemInstance()
    {
        try
        {
            String osName = SystemProperties.getName();
            //String osArch = SystemProperties.getArch();
            //String osVersion = SystemProperties.getVersion();
            
            if(!hasDetected)
            {
                hasDetected = true;
                if(osName.indexOf(OperatingSystems.getInstance().ANDROID) >= 0)
                {
                    LogUtil.put(LogFactory.getInstance("Found a Linux OS", this, CommonStrings.getInstance().GET_INSTANCE));

                    operatingSystemInterface = 
                        AndroidOperatingSystemFactory.getInstance().getOperatingSystemInstance();
                    
                    //PreLogUtil.put(log.toString());
                    LogUtil.put(LogFactory.getInstance("Operating System Info: " + operatingSystemInterface.toString(), this, CommonStrings.getInstance().GET_INSTANCE));
                }
                else
                {
                    throw new Exception("OS Not Supported: " + osName);
                }                
            }            
        }
        catch(Exception e)
        {
            operatingSystemInterface = new NoOperatingSystem();
            
            String error = "Failed to get instance";
            LogUtil.put(LogFactory.getInstance(error, this, CommonStrings.getInstance().GET_INSTANCE, e));
        }
        
        return operatingSystemInterface;
    }
}
