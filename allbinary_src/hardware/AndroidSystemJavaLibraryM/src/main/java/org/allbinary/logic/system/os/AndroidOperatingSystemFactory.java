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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidOperatingSystemFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final AndroidOperatingSystemFactory instance = new AndroidOperatingSystemFactory();
    
    public static AndroidOperatingSystemFactory getInstance()
    {
        return instance;
    }

    private AndroidOperatingSystemFactory()
    {
    }

    public GenericOperatingSystem getOperatingSystemInstance() throws Exception
    {
        try
        {
            GenericOperatingSystem GenericOperatingSystem;
            final SystemProperties systemProperties = SystemProperties.getInstance();
            String osName = systemProperties.getName();

            if(osName.compareTo(OperatingSystems.getInstance().ANDROID)==0)
            {
                GenericOperatingSystem = new AndroidOperatingSystem();
            }
            else
            {
                if(OperatingSystems.getInstance().isUnknownSpecificOSAllowed())
                {
                    GenericOperatingSystem = new AndroidOperatingSystem();
                }
                else
                {
                    throw new Exception("Specific Android OS Not Supported: " + osName);
                }
            }

            return GenericOperatingSystem;
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put("Failed to get OperatingSystem returning NoOperatingSystem", this, commonStrings.GET_INSTANCE, e);
            return new NoOperatingSystem();
        }
    }
}
