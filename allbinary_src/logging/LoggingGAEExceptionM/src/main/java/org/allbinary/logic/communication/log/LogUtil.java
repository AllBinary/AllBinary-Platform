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

import java.util.logging.Logger;
import org.allbinary.string.CommonStrings;

public class LogUtil
{

    private static final Logger logger = Logger.getLogger(LogUtil.class.getName());

    /*
    private final static String DEFAULT_PATH;

    static
    {
    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
    stringBuffer.append(AbPathData.getInstance().SEPARATOR);
    stringBuffer.append(LogData.ALLBINARY);
    stringBuffer.append(LogData.extension);

    DEFAULT_PATH = stringBuffer.toString();

    LogUtil.init();
    }
     */
    private LogUtil()
    {
    }

    public static void init()
    {
        //String message = "The Logging path is: " + LogUtil.getFilePatternPath();
        //PreLogUtil.put(message, "LogUtil", "init()");
        PreLogUtil.put("Loggin Initialized", "LogUtil", "init()");
    }

    public synchronized static void put(Log log)
    {
        Throwable exception = log.getThrowable();

        try
        {
            if (exception != null)
            {
                String specialMessage = log.getSpecialMessage();
                Object object = log.getObject();
                String functionName = log.getFunctionName();

                String className = commonStrings.EMPTY;

                Class clazz = object.getClass();
                if (clazz.getName() != null)
                {
                    className = clazz.getName();
                }

                String message = LogFormatUtil.getInstance().get(
                    className, functionName, specialMessage, exception);

                //logger.warning(message);
                logger.severe(message);
            }
        } catch (Exception e)
        {
        }
    }
    
    //private final static String LOG_SUCCESS = "org.allbinary: ";

    /*
    static String getFilePatternPath()
    {
    try
    {
    StringBuffer stringBuffer = new StringBuffer();

    stringBuffer.append(URLGLOBALS.getMainPath());
    stringBuffer.append(PATH_GLOBALS.getInstance().LOG_PATH);
    //stringBuffer.append(LogUtil.class.getClassLoader().getClass().hashCode());
    stringBuffer.append(LogData.ALLBINARY);
    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
    stringBuffer.append(LogData.extension);

    return stringBuffer.toString();
    //%d{yyyy_MM_dd_hh_mm_ss} + ".gz"

    }
    catch(Exception e)
    {
    //If a path is not set then use the current path
    return DEFAULT_PATH;
    }
    }
     */
}
