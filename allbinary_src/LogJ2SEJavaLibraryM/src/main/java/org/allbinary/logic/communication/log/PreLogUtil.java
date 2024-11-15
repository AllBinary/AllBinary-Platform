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

import org.allbinary.logic.string.CommonStrings;

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
        put(specialMessage, object, functionName, null);
    }
    
    
    private final static String LOG_SUCCESS = "org.allbinary: ";

    public synchronized static void put(
        String specialMessage,
        Object object,
        String functionName,
        Throwable exception)
    {
        String className = CommonStrings.getInstance().EMPTY;
        
        if(object.getClass().getName() != null)
        {
            className = new String(object.getClass().getName());
        }
        
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);
        
        System.out.print(LOG_SUCCESS);
        System.out.println(message);
    }
    
    public synchronized static void put(
        String specialMessage,
        String className,
        String functionName)
    {
        put(specialMessage, className, functionName, null);
    }
    
    public synchronized static void put(
        String specialMessage,
        String className,
        String functionName,
        Throwable exception)
    {
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);
        
        System.out.print(LOG_SUCCESS);
        System.out.println(message);
    }
    
}
