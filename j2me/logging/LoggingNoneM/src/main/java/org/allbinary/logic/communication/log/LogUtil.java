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

public class LogUtil {

    private static final LogUtil instance = new LogUtil();

    public static final LogUtil getInstance() {
        return instance;
    }

    private LogUtil() {
    }

    public void put(Log log) {
    }

    public void put(
        final String specialMessage,
        final Object object,
        final String functionName) {
    }

    public void put(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception) {
    }
}
