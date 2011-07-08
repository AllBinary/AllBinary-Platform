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
package abcs.logic.system.os.solaris;

import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.os.OperatingSystemInterface;
import abcs.logic.system.os.OperatingSystems;
import abcs.logic.system.os.SystemProperties;

public class SolarisOperatingSystemFactory
{
    private SolarisOperatingSystemFactory()
    {
    }
    
    public static synchronized OperatingSystemInterface getInstance() throws Exception
    {
        try
        {
            OperatingSystemInterface operatingSystemInterface;
            String osName = SystemProperties.getName();
            
            if(osName.compareTo(OperatingSystems.SOLARIS)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Solaris();
            }
            else
            {
                if(OperatingSystems.isUnknownSpecificOSAllowed())
                {
                    operatingSystemInterface =
                        (OperatingSystemInterface) new Solaris();
                }
                else
                {
                    throw new Exception("Specific Solaris OS Not Supported: " + osName);
                }
            }
            
            return operatingSystemInterface;
        }
        catch(Exception e)
        {
            String error = "Failed to get instance";
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
            {
                LogUtil.put(error, "SolarisOperatingSystemsFactory", "getInstance()", e);
            }
            throw e;
        }
    }
}
