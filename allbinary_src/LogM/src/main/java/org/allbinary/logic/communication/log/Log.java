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
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class Log
{
    private final NullUtil nullUtil = NullUtil.getInstance();
    private final LogFormatUtil logFormatUtil = LogFormatUtil.getInstance();

    private final String specialMessage;
    private final Object object;
    private final String functionName;
    private final Object exception;

    public Log()
    {
        final StringUtil stringUtil = StringUtil.getInstance();
        
        this.specialMessage = stringUtil.EMPTY_STRING;
        this.object = nullUtil.NULL_OBJECT;
        this.functionName = stringUtil.EMPTY_STRING;
        this.exception = nullUtil.NULL_OBJECT;
    }
    
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

    public Log(
        final String specialMessage,
        final Object object,
        final String functionName)
    {
        this.specialMessage = specialMessage;
        this.object = object;
        this.functionName = functionName;
        this.exception = nullUtil.NULL_OBJECT;

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

    public Object getThrowable()
    {
        return exception;
    }

    public String toString()
    {
        String className = CommonStrings.getInstance().EMPTY;

        Class clazz = object.getClass();
        if (clazz.getName() != null)
        {
            className = clazz.getName();
        }

        return logFormatUtil.get(className, this.functionName, this.specialMessage, this.exception);
    }
}
