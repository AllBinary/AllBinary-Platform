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
package org.allbinary.logic.system.hardware;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.hardware.android.AndroidHardware;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystems;
import org.allbinary.string.CommonStrings;

public class HardwareFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final HardwareFactory instance = new HardwareFactory();

    public static HardwareFactory getInstance()
    {
        return instance;
    }

    private HardwareFactory()
    {
    }

    public HardwareInterface getInstance(GenericOperatingSystem os) throws Exception
    {
        try
        {
            if (os.getName().compareTo(OperatingSystems.getInstance().ANDROID) == 0)
            {
                return new AndroidHardware();
            }
            throw new Exception("No Hardware Imp for: " + os.getName());
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put("Ignoring Exception Returning NoHardware", this, commonStrings.GET_INSTANCE, e);
            return new NoHardware();
        }
    }
}
