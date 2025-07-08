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

public class PreLogUtil
{
    
    public PreLogUtil()
    {
    }
    
    /*
    public static void put(Log log)
    {
        String specialMessage = log.getSpecialMessage();
        Object object = log.getObject();
        String className = log.getClassName();
        String functionName = log.getFunctionName();
        Object exception = log.getThrowable();
        
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
    public static void put(
        String specialMessage,
        Object object,
        String functionName)
    {
        put(specialMessage, object, functionName, null);
    }
    
    private static final String LABEL = "org.allbinary";
    //private final static String LOG_SUCCESS = "org.allbinary: ";

    public static void put(
        String specialMessage,
        Object object,
        String functionName,
        Object exception)
    {
        String className = LABEL;
        
        String actualClassName = object.getClass().getName();
        if(actualClassName != null)
        {
            className = actualClassName;
        }
        
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);
        
        //android.util.Log.i(className, LOG_SUCCESS + message);
        android.util.Log.i(LABEL, message);
        //android.util.Log.i(className, message);
        //System.out.print(LOG_SUCCESS);
        //System.out.println(message);
    }
    
    public static void put(
        String specialMessage,
        String className,
        String functionName)
    {
        put(specialMessage, className, functionName, null);
    }
    
    public static void put(
        String specialMessage,
        String className,
        String functionName,
        Object exception)
    {
        String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);
        
        //android.util.Log.i(className, LOG_SUCCESS + message);
        android.util.Log.i(className, message);
        //System.out.print(LOG_SUCCESS);
        //System.out.println(message);
    }
    
}
