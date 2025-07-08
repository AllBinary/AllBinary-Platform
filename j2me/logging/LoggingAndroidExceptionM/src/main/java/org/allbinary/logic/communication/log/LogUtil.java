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

public class LogUtil
{
   private LogUtil()
   {
   }
   
   public static void put(Log log)
   {
      if(log != null)
      {
      String specialMessage = log.getSpecialMessage();
      Object object = log.getObject();
      String functionName = log.getFunctionName();
      Throwable exception = log.getThrowable();

      put(specialMessage, object, functionName, exception);
      }
   }
   
   public synchronized static void put(
      String specialMessage,
      Object object,
      String functionName)
   {
   }
   private static final String LABEL = "org.allbinary";
   private final static String LOG_SUCCESS = "org.allbinary: ";
   
   public synchronized static void put(
      String specialMessage,
      Object object,
      String functionName,
      Throwable exception)
   {
      String className = LABEL;
      /*
      if(object != null && object.getClass().getName() != null)
      {
         className = new String(object.getClass().getName());
      }
      */
      
      className = object.getClass().getName();

      String message = LogFormatUtil.getInstance().get(
         className, functionName, specialMessage, exception);
      
      android.util.Log.i(LABEL, LOG_SUCCESS + message);
      //android.util.Log.i(LABEL, message);
   }
}
