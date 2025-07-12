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
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

public class OperatingSystemFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final OperatingSystemFactory instance = new OperatingSystemFactory();

    /**
     * @return the instance
     */
    public static OperatingSystemFactory getInstance()
    {
        return instance;
    }

    private GenericOperatingSystem GenericOperatingSystem = new NoOperatingSystem();

    private OperatingSystemFactory()
    {
    }
    
    public synchronized GenericOperatingSystem getOperatingSystemInstance()
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            //String osName = SystemProperties.getName();
            //String osArch = SystemProperties.getArch();
            //String osVersion = SystemProperties.getVersion();

            final String osString = new StringMaker().append("OperatingSystem Info: ").append(GenericOperatingSystem.toString()).toString();
            System.out.println(osString);
            logUtil.put(osString, this, commonStrings.GET_INSTANCE);

            //throw new Exception("OS Not Supported: ").append(osName);
        }
        catch(Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
            //{
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
            //}
        }
        return GenericOperatingSystem;
    }
}
