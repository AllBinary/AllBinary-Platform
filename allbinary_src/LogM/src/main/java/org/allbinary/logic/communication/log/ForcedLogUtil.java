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
package org.allbinary.logic.communication.log;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.basic.string.CommonStrings;

public class ForcedLogUtil
{
    //"Should not be called"
    public static void log(String message, Object object)
    {
        try
        {
            throw new Exception(message);
        }
        catch (Exception e)
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, object, "log", e));
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, object, "log", e);
        }
    }
}
