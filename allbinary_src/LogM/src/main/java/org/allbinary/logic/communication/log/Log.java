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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFormatUtil;

public class Log
{
    
    private final LogFormatUtil logFormatUtil = LogFormatUtil.getInstance();

    private final String specialMessage;
    private final Object object;
    private final String functionName;
    private final Object exception;
    
    public Log(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception)
    {
        this.specialMessage = specialMessage;
        this.object = object;
        this.functionName = functionName;
        this.exception = exception;
    }

    public String getSpecialMessage()
    {
        return this.specialMessage;
    }

    public Object getObject()
    {
        return this.object;
    }

    public String getFunctionName()
    {
        return this.functionName;
    }

    public Object getThrowable()
    {
        return this.exception;
    }

    public String toString()
    {
        String className = CommonStrings.getInstance().EMPTY;

        Class clazz = this.object.getClass();
        if (clazz.getName() != null)
        {
            className = clazz.getName();
        }

        return this.logFormatUtil.get(className, this.functionName, this.specialMessage, this.exception);
    }
}
