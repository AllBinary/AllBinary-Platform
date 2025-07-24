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
package org.allbinary.logic.system.os.windows;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.NoOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystems;
import org.allbinary.logic.system.os.SystemProperties;
import org.allbinary.string.CommonStrings;

public class WindowsOperatingSystemFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final WindowsOperatingSystemFactory instance = new WindowsOperatingSystemFactory();
    
    public static WindowsOperatingSystemFactory getInstance()
    {
        return instance;
    }

    private WindowsOperatingSystemFactory()
    {
    }
    
    public GenericOperatingSystem getOperatingSystemInstance()
    {
        try
        {
            final OperatingSystems operatingSystems = OperatingSystems.getInstance();
            final String osName = SystemProperties.getInstance().getName();
            GenericOperatingSystem operatingSystemInterface;
            
            if(osName.compareTo(operatingSystems.WINDOWS_NT)==0)
            {
                operatingSystemInterface =
                     new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_10)==0)
            {
                operatingSystemInterface =
                     new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_11)==0)
            {
                operatingSystemInterface =
                     new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS2000)==0)
            {
                operatingSystemInterface =
                     new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_XP)==0)
            {
                operatingSystemInterface =
                     new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_VISTA)==0)
            {
                operatingSystemInterface =
                     new Windows();
            }
            else
            {
                if(operatingSystems.isUnknownSpecificOSAllowed())
                {
                    operatingSystemInterface =
                         new Windows();
                }
                else
                {
                    throw new Exception("Specific Windows OS Not Supported: " + osName);
                }
            }
            
            return operatingSystemInterface;
        }
        catch(Exception e)
        {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().FACTORYERROR))
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
            }
            return NoOperatingSystem.NO_OPERATING_SYSTEM;
        }
    }
}
