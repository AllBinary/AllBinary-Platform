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

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.time.TimeStampUtil;

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

    public String get(
        String className, String functionName, String specialMessage, Throwable exception)
    {
        StringBuffer stringBuffer = get(new StringBuffer(), className, functionName);

        stringBuffer.append(SPECIAL_MESSAGE);
        stringBuffer.append(specialMessage);
        stringBuffer.append(CommonSeps.getInstance().NEW_LINE);

        //"\nClassLoader: " +  hashCode +

        return stringBuffer.toString();
    }

    public String get(
        String className, String functionName, String specialMessage)
    {   
        StringBuffer stringBuffer = get(new StringBuffer(), className, functionName);

        stringBuffer.append(SPECIAL_MESSAGE);
        stringBuffer.append(specialMessage);
        stringBuffer.append(CommonSeps.getInstance().NEW_LINE);

        //"\nClassLoader: " +  hashCode +

        return stringBuffer.toString();
    }

    //Date does not change as static
    //private final Calendar calendar = Calendar.getInstance();
    
    private StringBuffer get(StringBuffer stringBuffer,
        String className, String functionName)
    {
        if (functionName == null)
        {
            functionName = NONE;
        }

        //int hashCode = LogUtil.class.getClassLoader().getClass().hashCode();
        stringBuffer.append(TIME);
        stringBuffer.append(timeStampUtil.getAsString());
        stringBuffer.append(CLASS_NAME);
        stringBuffer.append(className);
        stringBuffer.append(FUNCTION_CALL);
        stringBuffer.append(functionName);

        //"\nClassLoader: " +  hashCode +

        return stringBuffer;
    }

}
