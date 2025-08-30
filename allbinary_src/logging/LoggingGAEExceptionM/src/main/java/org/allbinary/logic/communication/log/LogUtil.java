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
import java.util.logging.Level;
import org.allbinary.logic.string.StringMaker;


import java.util.logging.Logger;

import org.allbinary.string.CommonStrings;

//ActualPlatform
public class LogUtil {

    private static final LogUtil instance = new LogUtil();

    //ActualPlatform
    public static final LogUtil getInstance() {
        return instance;
    }

    private final Logger logger = Logger.getLogger(LogUtil.class.getName());

    /*
    private final static String DEFAULT_PATH;

    static
    {
    StringMaker stringBuffer = new StringMaker();

    stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
    stringBuffer.append(AbPathData.getInstance().SEPARATOR);
    stringBuffer.append(LogData.ALLBINARY);
    stringBuffer.append(LogData.extension);

    DEFAULT_PATH = stringBuffer.toString();

    LogUtil.init();
    }
     */
    private LogUtil() {
    }

    //ActualPlatform
    public void init() {
        //String message = "The Logging path is: " + LogUtil.getFilePatternPath();
        //PreLogUtil.put(message, "LogUtil", "init()");
        PreLogUtil.put("Loggin Initialized", "LogUtil", "init()");
    }

    //ActualPlatform
    public void put(Log log) {
        Object exception = log.getThrowable();

        try {
            if (exception != null) {
                String specialMessage = log.getSpecialMessage();
                Object object = log.getObject();
                String functionName = log.getFunctionName();

                String className = CommonStrings.getInstance().EMPTY;

                Class clazz = object.getClass();
                if (clazz.getName() != null) {
                    className = clazz.getName();
                }

                String message = LogFormatUtil.getInstance().get(
                    className, functionName, specialMessage, exception);

                //logger.warning(message);
                //logger.severe(message);
                logger.log(Level.INFO, message);
            }
        } catch (Exception e) {
        }
    }
    
    //private final static String LOG_SUCCESS = "org.allbinary: ";
    //ActualPlatform
    public void put(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception) {
        try {
            String className = CommonStrings.getInstance().EMPTY;

            Class clazz = object.getClass();
            if (clazz.getName() != null) {
                className = clazz.getName();
            }

            String message = LogFormatUtil.getInstance().get(
                className, functionName, specialMessage);
            //className, functionName, specialMessage, exception);

            if (exception != null) {
                //logger.warning(message);
                //logger.severe(message);
                logger.log(Level.SEVERE, message, exception);
            } else {
                //Change this back to warning when I get a stable version of gaefv without massive warnings
                logger.log(Level.INFO, message);
                //logger.log(Level.WARNING, message);
                //logger.info(message);
            }
        } catch (Exception e) {
        }
    }
    
    //private final static String LOG_SUCCESS = "org.allbinary: ";

    /*
    static String getFilePatternPath()
    {
    try
    {
    StringMaker stringBuffer = new StringMaker();

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
