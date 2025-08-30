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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.Log;

//ActualPlatform
public class LogFactory
{
    //ActualPlatform
    public static final Log getInstance(
            String specialMessage,
            Object object,
            Object exception)
    {
        return new Log(specialMessage, object, StringUtil.getInstance().EMPTY_STRING, exception);
    }

    //ActualPlatform
    public static final Log getInstance(
            String specialMessage,
            Object object,
            String functionName,
            Object exception)
    {
        return new Log(specialMessage, object, functionName, exception);
    }

    //ActualPlatform
    public static final Log getInstance(
            String specialMessage,
            Object object,
            String functionName)
    {
        return new Log(specialMessage, object, functionName);
    }
}
