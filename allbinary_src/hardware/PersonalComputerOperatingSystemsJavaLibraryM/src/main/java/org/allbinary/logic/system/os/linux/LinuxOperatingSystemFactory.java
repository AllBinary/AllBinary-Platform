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
package org.allbinary.logic.system.os.linux;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.system.os.NoOperatingSystem;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystems;
import org.allbinary.logic.system.os.SystemProperties;
import org.allbinary.string.CommonStrings;

public class LinuxOperatingSystemFactory
{
    private static final LinuxOperatingSystemFactory instance = new LinuxOperatingSystemFactory();
    
    public static LinuxOperatingSystemFactory getInstance()
    {
        return instance;
    }

    private LinuxOperatingSystemFactory()
    {
    }

    public GenericOperatingSystem getOperatingSystemInstance()
    {
        try
        {
            final OperatingSystems operatingSystems = OperatingSystems.getInstance();
            final String osName = SystemProperties.getInstance().getName();
            GenericOperatingSystem operatingSystemInterface;
            
            if(osName.compareTo(operatingSystems.LINUX)==0)
            {
                operatingSystemInterface =
                     new Linux();
            }
            else
            {
                if(operatingSystems.isUnknownSpecificOSAllowed())
                {
                    operatingSystemInterface =
                         new Linux();
                }
                else
                {
                    throw new Exception("Specific Linux OS Not Supported: " + osName);
                }
            }
            
            return operatingSystemInterface;
        }
        catch(Exception e)
        {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e));
            }
            return new NoOperatingSystem();
        }
    }
}
