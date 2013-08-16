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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AndroidOperatingSystemFactory {

    private static final AndroidOperatingSystemFactory instance = new AndroidOperatingSystemFactory();
    
    public static AndroidOperatingSystemFactory getInstance()
    {
        return instance;
    }

    private AndroidOperatingSystemFactory()
    {
    }

    public OperatingSystemInterface getOperatingSystemInstance() throws Exception
    {
        try
        {
            OperatingSystemInterface operatingSystemInterface;
            String osName = SystemProperties.getName();

            if(osName.compareTo(OperatingSystems.getInstance().ANDROID)==0)
            {
                operatingSystemInterface = new AndroidOperatingSystem();
            }
            else
            {
                if(OperatingSystems.getInstance().isUnknownSpecificOSAllowed())
                {
                    operatingSystemInterface = new AndroidOperatingSystem();
                }
                else
                {
                    throw new Exception("Specific Android OS Not Supported: " + osName);
                }
            }

            return operatingSystemInterface;
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Failed to get OperatingSystem returning NoOperatingSystem", this, CommonStrings.getInstance().GET_INSTANCE, e));
            return new NoOperatingSystem();
        }
    }
}
