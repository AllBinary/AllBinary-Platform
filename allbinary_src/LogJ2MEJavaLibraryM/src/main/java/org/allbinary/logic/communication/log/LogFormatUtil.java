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

import org.allbinary.time.TimeStampUtil;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.java.exception.ExceptionUtil;

public class LogFormatUtil
{
    private static final LogFormatUtil instance = new LogFormatUtil();

    public static LogFormatUtil getInstance()
    {
        return instance;
    }

    private final TimeStampUtil timeStampUtil = TimeStampUtil.getInstance();


    private final String NONE = "None";
    private final String LOG_ERROR = "\nLog-Error: ";
    private final String EMPTY = "Empty";
    private final String STACK_TRACE = "\nStack Trace: ";
    private final String TIME = "Time: ";
    private final String CLASS_NAME = "\nClass Name: ";
    private final String FUNCTION_CALL = "\nFunction Call: ";
    private final String SPECIAL_MESSAGE = "\nSpecial Msg: ";
    
    private LogFormatUtil()
    {
    }

    public synchronized String get(
        String className, String functionName, String specialMessage, Throwable exception)
    {
        StringMaker stringBuffer = get(className, functionName);

        stringBuffer.append(this.get(exception));

        stringBuffer.append(SPECIAL_MESSAGE);
        stringBuffer.append(specialMessage);
        stringBuffer.append(CommonSeps.getInstance().NEW_LINE);

        //"\nClassLoader: " +  hashCode +

        return stringBuffer.toString();
    }

    public synchronized String get(
        String className, String functionName, String specialMessage)
    {
        StringMaker stringBuffer = get(className, functionName);

        stringBuffer.append(SPECIAL_MESSAGE);
        stringBuffer.append(specialMessage);
        stringBuffer.append(CommonSeps.getInstance().NEW_LINE);

        //"\nClassLoader: " +  hashCode +

        return stringBuffer.toString();
    }

    //Date does not change as static
    //private final Calendar calendar = Calendar.getInstance();
    
    private synchronized StringMaker get(
        String className, String functionName)
    {
        if (functionName == null)
        {
            functionName = NONE;
        }

        //int hashCode = LogUtil.class.getClassLoader().getClass().hashCode();
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(TIME);
        stringBuffer.append(timeStampUtil.getAsString());
        stringBuffer.append(CLASS_NAME);
        stringBuffer.append(className);
        stringBuffer.append(FUNCTION_CALL);
        stringBuffer.append(functionName);

        //"\nClassLoader: " +  hashCode +

        return stringBuffer;
    }

    private final ExceptionUtil exceptionUtil = ExceptionUtil.getInstance();
    public synchronized String get(Throwable exception)
    {
        if (exception != null)
        {
            StringMaker stringBuffer = new StringMaker();
            stringBuffer.append(LOG_ERROR);

            if (exception.toString() != null)
            {
                stringBuffer.append(exception.toString());
            } else
            {
                stringBuffer.append(EMPTY);
            }

            stringBuffer.append(STACK_TRACE);
            stringBuffer.append(exceptionUtil.getStackTrace(exception));

            /*
            String exceptionMessage = ExceptionUtil.getStackTrace(exception);

            if(exceptionMessage == null)
            {
            exceptionMessage = EMPTY;
            }
             */

            //+
            //"\nLog-Error: " + exceptionMessage;

            return stringBuffer.toString();
        } else
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }

}
