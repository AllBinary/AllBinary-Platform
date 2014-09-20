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
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.logic.system.os.OperatingSystems;
import org.allbinary.logic.system.os.SystemProperties;

public class WindowsOperatingSystemFactory
{
    private WindowsOperatingSystemFactory()
    {
    }
    
    public static synchronized OperatingSystemInterface getInstance() throws Exception
    {
        try
        {
            OperatingSystemInterface operatingSystemInterface;
            String osName = SystemProperties.getName();
            
            if(osName.compareTo(OperatingSystems.WINDOWS_NT)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(OperatingSystems.WINDOWS2000)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(OperatingSystems.WINDOWS_XP)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            if(osName.compareTo(OperatingSystems.WINDOWS_VISTA)==0)
            {
                operatingSystemInterface =
                    (OperatingSystemInterface) new Windows();
            }
            else
            {
                if(OperatingSystems.isUnknownSpecificOSAllowed())
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
            String error = "Failed to get instance";
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
            {
                LogUtil.put(error, "WindowsOperatingSystemsFactory", "getInstance()", e);
            }
            throw e;
        }
    }
}
