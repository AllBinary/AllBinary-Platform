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
package abcs.logic.system.hardware;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.hardware.android.AndroidHardware;
import abcs.logic.system.os.OperatingSystemInterface;
import abcs.logic.system.os.OperatingSystems;

public class HardwareFactory
{
    private static final HardwareFactory instance = new HardwareFactory();

    public static HardwareFactory getInstance()
    {
        return instance;
    }

    private HardwareFactory()
    {
    }

    public HardwareInterface getInstance(OperatingSystemInterface os) throws Exception
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
            LogUtil.put(LogFactory.getInstance("Ignoring Exception Returning NoHardware", this, CommonStrings.getInstance().GET_INSTANCE, e));
            return new NoHardware();
        }
    }
}
