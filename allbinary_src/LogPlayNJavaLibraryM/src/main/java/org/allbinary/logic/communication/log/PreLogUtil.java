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
import org.allbinary.logic.string.StringUtil;
import playn.core.PlayN;

public class PreLogUtil
{
    
    public PreLogUtil()
    {
    }
    
    /*
    public static void put(Log log)
    {
        String specialMessage = log.getSpecialMessage();
        Object object = log.getObject();
        String className = log.getClassName();
        String functionName = log.getFunctionName();
        Object exception = log.getThrowable();
        
        if(className != null)
        {
            put(specialMessage, className, functionName, exception);
        }
        else
            if(object != null)
            {
            put(specialMessage, object, functionName, exception);
            }
            else
            {
            put(specialMessage, "This Should Never Happed", functionName, exception);
            }
    }
    */

    public static void put(
        final String specialMessage,
        final Object object,
        final String functionName)
    {
//       String className = EMPTY;
        String className = PreLogUtil.getClassName(object);
        
        if(className == null) {
            className = CommonStrings.getInstance().EMPTY;
        }

        className = new StringMaker().append(className).append(CommonSeps.getInstance().FORWARD_SLASH).append(StringUtil.getInstance().toString(object)).toString();
        
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage);

        PlayN.log().debug(LOG_SUCCESS + message);
    }

    
    private final static String LOG_SUCCESS = "org.allbinary: ";

    public static void put(
        final String specialMessage,
        final Object object,
        final String functionName,
        final Object exception)
    {
//       String className = EMPTY;
        String className = PreLogUtil.getClassName(object);
        
        if(className == null) {
            className = CommonStrings.getInstance().EMPTY;
        }

        className = new StringMaker().append(className).append(CommonSeps.getInstance().FORWARD_SLASH).append(StringUtil.getInstance().toString(object)).toString();
        
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        PlayN.log().error(LOG_SUCCESS + message, (Throwable) exception);
    }
    
    public static void put(
        final String specialMessage,
        final String className,
        final String functionName)
    {        
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage);

        PlayN.log().debug(LOG_SUCCESS + message);
    }

    public static void put(
        final String specialMessage,
        final String className,
        final String functionName,
        final Object exception)
    {
        final String message = LogFormatUtil.getInstance().get(
            className, functionName, specialMessage, exception);

        PlayN.log().error(LOG_SUCCESS + message, (Throwable) exception);
    }
    
//    public static String getClassName(Object object) {
//        String className = EMPTY;
//
//        if (object.getClass().getName() != null)
//        {
//            className = new String(object.getClass().getName());
//        }
//    }
    
    public static native String getClassName(Object object) /*-{
            var a = new Error().stack.split('\n');
            //Example:     at org_allbinary_..._BasicTopViewGeographicMapCellTypeFactory_$init__Lorg_allbinary_..._BasicTopViewGeographicMapCellTypeFactory_2Ljava_util_Hashtable_2V (http://...
            var stackTraceLine = a[3];
            var startIndex = stackTraceLine.indexOf("at ");
            var endIndex = stackTraceLine.indexOf("$");
            if(startIndex + 3 - (endIndex - 1) < 5) {
                return stackTraceLine.substring(startIndex + 3, endIndex - 1);
            } else {
                var endIndex2 = stackTraceLine.indexOf("__");
                return stackTraceLine.substring(startIndex + 3, endIndex2);
            }
            //var endIndex = stackTraceLine.indexOf("(");
            //return stackTraceLine.substring(startIndex + 3, endIndex - 1);
            //var a = new Error().stack.match(/at (.*?) /);
            //return a[1];
            //object.constructor.name;
    }-*/;
    
}
