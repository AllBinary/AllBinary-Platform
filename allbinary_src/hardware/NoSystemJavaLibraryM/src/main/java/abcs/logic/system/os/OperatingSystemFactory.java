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

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

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
            //String osName = SystemProperties.getName();
            //String osArch = SystemProperties.getArch();
            //String osVersion = SystemProperties.getVersion();

            if(operatingSystemInterface == null)
            {
                operatingSystemInterface = new NoOperatingSystem();
            }

            final String osString = "OperatingSystem Info: " + operatingSystemInterface.toString();
            System.out.println(osString);
            LogUtil.put(LogFactory.getInstance(osString, this, "getInstance()"));

            //throw new Exception("OS Not Supported: " + osName);
            
            return operatingSystemInterface;
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
            //{
                LogUtil.put(LogFactory.getInstance(error, instance, "getInstance()", e));
            //}
            throw e;
        }
    }
}
