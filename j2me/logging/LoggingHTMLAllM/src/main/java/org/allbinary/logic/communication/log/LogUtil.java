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

import playn.core.PlayN;

//ActualPlatform
public class LogUtil {

    private static final LogUtil instance = new LogUtil();

    //ActualPlatform
    public static final LogUtil getInstance() {
        return instance;
    }

    private final String LOG_SUCCESS = "org.allbinary: ";

    private LogUtil() {
    }

    //ActualPlatform
    public void put(final Log log) {
        final String specialMessage = log.getSpecialMessage();
        final Object object = log.getObject();
        final String functionName = log.getFunctionName();
        final Object exception = log.getThrowable();

        this.put(specialMessage, object, functionName, exception);
    }

    //ActualPlatform
    public void put(
        final String specialMessage,
        final Object object,
        final String functionName) {
        String className = PreLogUtil.getClassName(object);

        if (className == null) {
            className = CommonStrings.getInstance().EMPTY;
        }

//        String className = EMPTY;
//        if (object.getClass().getName() != null)
//        {
//            className = new String(object.getClass().getName());
//        }
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage);

        PlayN.log().debug(LOG_SUCCESS + message);
        //System.out.print(LOG_SUCCESS);
        //System.out.println(message);
    }

    //ActualPlatform
    public void put(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception) {
        String className = PreLogUtil.getClassName(object);

        if (className == null) {
            className = CommonStrings.getInstance().EMPTY;
        }

//        String className = EMPTY;
//        if (object.getClass().getName() != null)
//        {
//            className = new String(object.getClass().getName());
//        }
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        if (exception != null) {
            PlayN.log().error(LOG_SUCCESS + message, (Throwable) exception);
        } else {
            PlayN.log().debug(LOG_SUCCESS + message);
        }

    }

}
