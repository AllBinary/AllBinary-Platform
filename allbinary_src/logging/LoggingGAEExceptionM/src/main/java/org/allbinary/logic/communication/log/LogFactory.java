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

//ActualPlatform

import org.allbinary.logic.NullUtil;

public class LogFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    //ActualPlatform
    public static final Log getInstance(
            final String specialMessage,
            final Object object,
            final String functionName,
            final Object exception)
    {
        /*
        if(object instanceof String)
        {
            ForcedLogUtil.log("Don't use a String", object);
        }
        */

        return new Log(specialMessage, object, functionName, exception);
    }

    //ActualPlatform
    public static final Log getInstanceF(
            final String specialMessage,
            final Object object,
            final String functionName)
    {
        /*
        if(object instanceof String)
        {
            ForcedLogUtil.log("Don't use a String", object);
        }
        */

        return new Log(specialMessage, object, functionName, NullUtil.getInstance().NULL_OBJECT);
    }
}
