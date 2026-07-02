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

import org.allbinary.logic.NullUtil;
import org.allbinary.string.CommonStrings;

//ActualPlatform
public class PreLogUtil
{
    
    //ActualPlatform
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
            PreLogUtil.putOE(specialMessage, className, functionName, exception);
        }
        else
            if(object != null)
            {
            PreLogUtil.putOE(specialMessage, object, functionName, exception);
            }
            else
            {
            PreLogUtil.putOE(specialMessage, "This Should Never Happed", functionName, exception);
            }
    }
    */
    
    //ActualPlatform
    public static void put(
        final String specialMessage,
        final Object object,
        final String functionName)
    {
        PreLogUtil.putOE(specialMessage, object, functionName, NullUtil.getInstance().NULL_OBJECT);
    }    
    
    private final static String LOG_SUCCESS = "org.allbinary: ";

    //ActualPlatform
    public static void putOE(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception)
    {
        String className = CommonStrings.getInstance().EMPTY;
        
        if(object.getClass().getName() != null)
        {
            className = new String(object.getClass().getName());
        }
        
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);
        
        System.out.print(PreLogUtil.LOG_SUCCESS);
        System.out.println(message);
    }
    
    //ActualPlatform
    public static void putS(
        String specialMessage,
        String className,
        String functionName)
    {
        PreLogUtil.putSE(specialMessage, className, functionName, NullUtil.getInstance().NULL_OBJECT);
    }
    
    //ActualPlatform
    public static void putSE(
        final String specialMessage,
        final String className,
        final String functionName,
        final Object exception)
    {
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);
        
        System.out.print(PreLogUtil.LOG_SUCCESS);
        System.out.println(message);
    }
    
}
