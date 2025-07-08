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

    private final String LABEL = "org.allbinary";
    private final String LOG_SUCCESS = "org.allbinary: ";
    
    private LogUtil() {
    }

    public void put(final Log log) {
        final String specialMessage = log.getSpecialMessage();
        final Object object = log.getObject();
        final String functionName = log.getFunctionName();
        final Object exception = log.getThrowable();

        if (log != null) {
            this.put(specialMessage, object, functionName, exception);
        }
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
        String className = LABEL;
        /*
      if(object != null && object.getClass().getName() != null)
      {
         className = new String(object.getClass().getName());
      }
         */

        className = object.getClass().getName();

        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        android.util.Log.i(LABEL, LOG_SUCCESS + message);
        //android.util.Log.i(LABEL, message);
    }
}
