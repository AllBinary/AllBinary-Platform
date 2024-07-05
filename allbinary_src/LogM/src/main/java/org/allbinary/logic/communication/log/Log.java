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

import org.allbinary.logic.string.StringUtil;

public class Log
{
    public static final Throwable NULL_THROWABLE = new Throwable();
    public static final Object OBJECT = new Object();
    private static final String EMPTY = "Empty";

    private final String specialMessage;
    private final Object object;
    private final String functionName;
    private final Throwable exception;

    private final LogFormatUtil logFormatUtil = LogFormatUtil.getInstance();

    private Log()
    {
        final StringUtil stringUtil = StringUtil.getInstance();
        this.specialMessage = stringUtil.EMPTY_STRING;
        this.object = OBJECT;
        this.functionName = stringUtil.EMPTY_STRING;
        this.exception = NULL_THROWABLE;
    }
    
    public Log(
        String specialMessage,
        Object object,
        String functionName,
        Throwable exception)
    {
        this.specialMessage = specialMessage;
        this.object = object;
        this.functionName = functionName;
        this.exception = exception;
    }

    public Log(
        String specialMessage,
        Object object,
        String functionName)
    {
        this.specialMessage = specialMessage;
        this.object = object;
        this.functionName = functionName;
        this.exception = NULL_THROWABLE;

    }

    public String getSpecialMessage()
    {
        return specialMessage;
    }

    public Object getObject()
    {
        return object;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public Throwable getThrowable()
    {
        return exception;
    }

    public String toString()
    {
        String className = EMPTY;

        Class clazz = object.getClass();
        if (clazz.getName() != null)
        {
            className = clazz.getName();
        }

        return logFormatUtil.get(className, this.functionName, this.specialMessage, this.exception);
    }
}
