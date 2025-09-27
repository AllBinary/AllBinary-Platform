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

import org.allbinary.logic.system.os.android.AndroidOperatingSystemFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

public class OperatingSystemFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final OperatingSystemFactory instance = new OperatingSystemFactory();

    public static OperatingSystemFactory getInstance()
    {
        return instance;
    }

    private GenericOperatingSystem genericOperatingSystem = NoOperatingSystem.NO_OPERATING_SYSTEM;

    private boolean hasDetected = false;
    
    private OperatingSystemFactory()
    {
    }
    
    public synchronized GenericOperatingSystem getOperatingSystemInstance()
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();

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
                    logUtil.put("Found a Linux OS", this, commonStrings.GET_INSTANCE);

                    genericOperatingSystem = 
                        AndroidOperatingSystemFactory.getInstance().getOperatingSystemInstance();
                    
                    //PreLogUtil.put(log.toString());
                    logUtil.put(new StringMaker().append("Operating System Info: ").append(genericOperatingSystem.toString()).toString(), this, commonStrings.GET_INSTANCE);
                }
                else
                {
                    throw new Exception(new StringMaker().append("OS Not Supported: ").append(osName).toString());
                }                
            }            
        }
        catch(Exception e)
        {
            genericOperatingSystem = NoOperatingSystem.NO_OPERATING_SYSTEM;
            
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
        }
        
        return genericOperatingSystem;
    }
}
