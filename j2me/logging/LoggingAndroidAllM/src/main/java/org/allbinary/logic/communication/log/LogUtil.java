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

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

public class LogUtil
{
   private LogUtil()
   {
   }

   private static final String LABEL = "org.allbinary";
   //private final static String LOG_SUCCESS = "org.allbinary: ";

   public static void put(Log log)
   {
      String specialMessage = log.getSpecialMessage();
      Object object = log.getObject();
      String functionName = log.getFunctionName();
      Throwable exception = log.getThrowable();

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
      
      className = new String(new StringMaker().append(object.getClass().getName()).append(CommonSeps.getInstance().COLON).append(Integer.toHexString(object.hashCode())).toString());
      
      final String message = LogFormatUtil.getInstance().get(
         className, functionName, specialMessage, exception);
      
      android.util.Log.i(LABEL, message);
      //android.util.Log.i(className, message);
      //android.util.Log.i(className, LOGGING_LABEL + message);
   }
}
