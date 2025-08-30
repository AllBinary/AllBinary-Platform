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

//NoPlatform
public class PreLogUtil
{
    
    public PreLogUtil()
    {
    }
    
    /*
    public static void put(Log log)
    {
        throw new RuntimeException();
    }
    */
    
    public static void put(
        final String specialMessage,
        final Object object,
        final String functionName)
    {
        put(specialMessage, object, functionName, NullUtil.getInstance().NULL_OBJECT);
    }    
    
    public static void put(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception)
    {
        throw new RuntimeException();
    }
    
    public static void put(
        String specialMessage,
        String className,
        String functionName,
        Object exception)
    {
        throw new RuntimeException();
    }
    
}
