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
package abcs.logic.system.os;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

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
    
    public synchronized OperatingSystemInterface getOperatingSystemInstance() throws Exception
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
                }
                else
                {
                    throw new Exception("OS Not Supported: " + osName);
                }
            }
            
            Log log = LogFactory.getInstance("OperatingSystem Info: " + operatingSystemInterface, this, CommonStrings.getInstance().GET_INSTANCE);
            //PreLogUtil.put(log.toString());
            LogUtil.put(log);
            
            return operatingSystemInterface;
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";

            LogUtil.put(LogFactory.getInstance(error, this, CommonStrings.getInstance().GET_INSTANCE, e));
            throw e;
        }
    }
}
