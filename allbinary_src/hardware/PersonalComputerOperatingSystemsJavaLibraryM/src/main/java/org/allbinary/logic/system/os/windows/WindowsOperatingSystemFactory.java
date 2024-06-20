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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.system.os.NoOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.logic.system.os.OperatingSystems;
import org.allbinary.logic.system.os.SystemProperties;

public class WindowsOperatingSystemFactory
{
    private static final WindowsOperatingSystemFactory instance = new WindowsOperatingSystemFactory();
    
    public static WindowsOperatingSystemFactory getInstance()
    {
        return instance;
    }

    private WindowsOperatingSystemFactory()
    {
    }
    
    public OperatingSystemInterface getOperatingSystemInstance()
    {
        try
        {
            final OperatingSystems operatingSystems = OperatingSystems.getInstance();
            final String osName = SystemProperties.getInstance().getName();
            OperatingSystemInterface operatingSystemInterface;
            
            if(osName.compareTo(operatingSystems.WINDOWS_NT)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_10)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_11)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS2000)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_XP)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(operatingSystems.WINDOWS_VISTA)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            {
                if(operatingSystems.isUnknownSpecificOSAllowed())
                {
                    operatingSystemInterface =
                        (OperatingSystemInterface) new Windows();
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
                String error = "Failed to get instance";
                LogUtil.put(LogFactory.getInstance(error, this, "getOperatingSystemInstance()", e));
            }
            return new NoOperatingSystem();
        }
    }
}
