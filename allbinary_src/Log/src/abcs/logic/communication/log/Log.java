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

public class Log
{
    private String specialMessage;
    private Object object;
    private String functionName;
    private Throwable exception;

    public Log(
        String specialMessage,
        Object object,
        String functionName,
        Throwable exception)
    {
        this.setSpecialMessage(specialMessage);
        this.setObject(object);
        this.setFunctionName(functionName);
        this.setThrowable(exception);
    }

    public Log(
        String specialMessage,
        Object object,
        String functionName)
    {
        this.setSpecialMessage(specialMessage);
        this.setObject(object);
        this.setFunctionName(functionName);
    }

    public String getSpecialMessage()
    {
        return specialMessage;
    }

    public void setSpecialMessage(String specialMessage)
    {
        this.specialMessage = specialMessage;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public Throwable getThrowable()
    {
        return exception;
    }

    public void setThrowable(Throwable exception)
    {
        this.exception = exception;
    }

    private static final String EMPTY = "Empty";

    public String toString()
    {
        String className = EMPTY;

        Class clazz = object.getClass();
        if (clazz.getName() != null)
        {
            className = clazz.getName();
        }

        return LogFormatUtil.getInstance().get(className, this.getFunctionName(), this.getSpecialMessage(), this.getThrowable());
    }
}
