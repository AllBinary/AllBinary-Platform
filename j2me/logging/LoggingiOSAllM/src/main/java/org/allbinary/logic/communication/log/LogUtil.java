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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

//ActualPlatform
public class LogUtil {

    private static final LogUtil instance = new LogUtil();

    //ActualPlatform
    public static final LogUtil getInstance() {
        return instance;
    }

    private LogUtil() {
    }

    //ActualPlatform
    public void put(Log log) {
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
        String className = CommonStrings.getInstance().EMPTY;

        if (object.getClass().getName() != null) {
            className = new String(new StringMaker().append(object.getClass().getName()).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(object.hashCode())).toString());
        }

        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage);

        System.out.print(LOG_SUCCESS);
        System.out.println(message);
    }

    private final static String LOG_SUCCESS = "org.allbinary: ";

    //ActualPlatform
    public void put(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception) {
        String className = CommonStrings.getInstance().EMPTY;

        if (object.getClass().getName() != null) {
            className = new String(new StringMaker().append(object.getClass().getName()).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(object.hashCode())).toString());
        }

        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        System.out.print(LOG_SUCCESS);
        System.out.println(message);
    }
}
