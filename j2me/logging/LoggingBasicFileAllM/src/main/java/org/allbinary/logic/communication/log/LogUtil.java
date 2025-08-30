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

import org.allbinary.log.FileLog;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

//ActualPlatform
public class LogUtil
{
    private static final LogUtil instance = new LogUtil();
    
    //ActualPlatform
    public static final LogUtil getInstance() {
        return instance;
    }

    //private final String LOG_SUCCESS = "org.allbinary: ";
    
    private LogUtil()
    {
    }

    //ActualPlatform
    public void put(Log log)
    {
        String specialMessage = log.getSpecialMessage();
        Object object = log.getObject();
        String functionName = log.getFunctionName();
        Object exception = log.getThrowable();

        put(specialMessage, object, functionName, exception);
    }

    //ActualPlatform
    public void put(
        String specialMessage,
        Object object,
        String functionName)
    {
//        String className = CommonStrings.getInstance().EMPTY;
//
//        if (object.getClass().getName() != null)
//        {
//            className = new String(new StringMaker().append(object.getClass().getName()).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(object.hashCode())).toString());
//        }

        FileLog.put(specialMessage, object, functionName);
    }

    //ActualPlatform
    public void put(
        String specialMessage,
        Object object,
        String functionName,
        Object exception)
    {
//        String className = CommonStrings.getInstance().EMPTY;
//
//        if (object.getClass().getName() != null)
//        {
//            className = new String(new StringMaker().append(object.getClass().getName()).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(object.hashCode())).toString());
//        }

        FileLog.put(specialMessage, object, functionName, (Throwable) exception);
    }
}
