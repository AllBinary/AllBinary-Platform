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
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

public class LogUtil {

    private static final LogUtil instance = new LogUtil();

    public static final LogUtil getInstance() {
        return instance;
    }

    private final CommonSeps commonSeps = CommonSeps.getInstance();
    
    private final String LABEL = "org.allbinary";
    //private final String LOG_SUCCESS = "org.allbinary: ";

    private LogUtil() {
    }

    public void put(final Log log) {
        final String specialMessage = log.getSpecialMessage();
        final Object object = log.getObject();
        final String functionName = log.getFunctionName();
        final Object exception = log.getThrowable();
        this.put(specialMessage, object, functionName, exception);
    }
    
    public void put(final String specialMessage, final Object object, String functionName) {
        this.put(specialMessage, object, functionName, NullUtil.getInstance().NULL_OBJECT);
    }
    
    public void put(final String specialMessage, final Object object, String functionName, Object exception) {

        String className = LABEL;
        /*
      if(object != null && object.getClass().getName() != null)
      {
         className = new String(object.getClass().getName());
      }
         */

 /*
      if(exception == null && specialMessage.indexOf(commonStrings.EXCEPTION) < 0)
      {
          return;
      }
         */
        className = new String(new StringMaker().append(object.getClass().getName()).append(commonSeps.COLON).append(Integer.toHexString(object.hashCode())).toString());

        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        android.util.Log.i(LABEL, message);
        //android.util.Log.i(className, message);
        //android.util.Log.i(className, LOGGING_LABEL + message);
    }
}
