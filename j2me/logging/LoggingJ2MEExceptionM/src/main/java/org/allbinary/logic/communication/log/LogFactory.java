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

public class LogFactory
{
    public static final Log LOG = new Log(null, null, null);
    
    public static final Log getInstance(
            String specialMessage,
            Object object,
            String functionName,
            Throwable exception)
    {
        return new Log(specialMessage, object, functionName, exception);
    }

    public static final Log getInstance(
            String specialMessage,
            Object object,
            String functionName)
    {
        return LOG;
        //return new Log(specialMessage, object, functionName);
    }
}
