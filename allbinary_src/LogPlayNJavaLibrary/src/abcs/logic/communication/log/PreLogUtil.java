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
package abcs.logic.communication.log;

import playn.core.PlayN;

public class PreLogUtil
{
    
    public PreLogUtil()
    {
    }
    
    /*
    public synchronized static void put(Log log)
    {
        String specialMessage = log.getSpecialMessage();
        Object object = log.getObject();
        String className = log.getClassName();
        String functionName = log.getFunctionName();
        Throwable exception = log.getThrowable();
        
        if(className != null)
        {
            put(specialMessage, className, functionName, exception);
        }
        else
            if(object != null)
            {
            put(specialMessage, object, functionName, exception);
            }
            else
            {
            put(specialMessage, "This Should Never Happed", functionName, exception);
            }
    }
    */

    public synchronized static void put(
        String specialMessage,
        Object object,
        String functionName)
    {
        String className = EMPTY;
        
        if(object.getClass().getName() != null)
        {
            className = new String(object.getClass().getName());
        }
        
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage);

        PlayN.log().debug(LOG_SUCCESS + message);
    }

    private static final String EMPTY = "Empty";
    private final static String LOG_SUCCESS = "Logging Successful: ";

    public synchronized static void put(
        String specialMessage,
        Object object,
        String functionName,
        Throwable exception)
    {
        String className = EMPTY;
        
        if(object.getClass().getName() != null)
        {
            className = new String(object.getClass().getName());
        }
        
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        PlayN.log().error(LOG_SUCCESS + message, exception);
    }
    
    public synchronized static void put(
        String specialMessage,
        String className,
        String functionName)
    {        
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage);

        PlayN.log().debug(LOG_SUCCESS + message);
    }

    public synchronized static void put(
        String specialMessage,
        String className,
        String functionName,
        Throwable exception)
    {
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        PlayN.log().error(LOG_SUCCESS + message, exception);
    }
}
