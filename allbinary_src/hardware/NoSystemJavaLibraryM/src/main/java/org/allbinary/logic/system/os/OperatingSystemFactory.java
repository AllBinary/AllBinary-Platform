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

import org.allbinary.logic.system.os.NoOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

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

    private OperatingSystemInterface operatingSystemInterface = new NoOperatingSystem();

    private OperatingSystemFactory()
    {
    }
    
    public synchronized OperatingSystemInterface getOperatingSystemInstance()
    {
        try
        {
            //String osName = SystemProperties.getName();
            //String osArch = SystemProperties.getArch();
            //String osVersion = SystemProperties.getVersion();

            final String osString = "OperatingSystem Info: " + operatingSystemInterface.toString();
            System.out.println(osString);
            LogUtil.put(LogFactory.getInstance(osString, this, "getInstance()"));

            //throw new Exception("OS Not Supported: " + osName);
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
            //{
                LogUtil.put(LogFactory.getInstance(error, instance, "getInstance()", e));
            //}
        }
        return operatingSystemInterface;
    }
}
