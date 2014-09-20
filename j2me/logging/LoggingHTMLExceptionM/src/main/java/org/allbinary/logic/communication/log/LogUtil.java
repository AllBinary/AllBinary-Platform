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

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogFormatUtil;
import playn.core.PlayN;

public class LogUtil
{

    private final static String EMPTY = "Empty";
    private final static String LOG_SUCCESS = "Logging Successful: ";

    private LogUtil()
    {
    }

    public synchronized static void put(Log log)
    {
        if (log == null)
        {
            return;
        }

        Throwable exception = log.getThrowable();

        if (exception == null)
        {
            return;
        }

        String specialMessage = log.getSpecialMessage();
        Object object = log.getObject();
        String functionName = log.getFunctionName();

        put(specialMessage, object, functionName, exception);
    }

    private synchronized static void put(
            String specialMessage,
            Object object,
            String functionName)
    {
    }

    //TWB - Public or Private?
    private synchronized static void put(
            String specialMessage,
            Object object,
            String functionName,
            Throwable exception)
    {
        String className = EMPTY;

        if (object.getClass().getName() != null)
        {
            className = new String(object.getClass().getName());
        }

        String message = LogFormatUtil.getInstance().get(
                className, functionName, specialMessage, exception);

        PlayN.log().error(LOG_SUCCESS + message, exception);
    }
}
