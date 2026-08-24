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

import jsinterop.annotations.JsType;
import org.allbinary.string.CommonStrings;

//@playn.core.PlayN
import playn.core.PlayN;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

//ActualPlatform
@JsType
public class LogUtil {

    private static final LogUtil instance = new LogUtil();

    //ActualPlatform
    @JsMethod
    public static final LogUtil getInstance() {
        return LogUtil.instance;
    }

    private final LogFormatUtil logFormatUtil = LogFormatUtil.getInstance();
    
    private final String LOG_SUCCESS = "org.allbinary: ";

    @JsConstructor
    private LogUtil() {
    }

    //ActualPlatform
    @JsMethod
    public void putL(final Log log) {
        if (log == null) {
            return;
        }

        final String specialMessage = log.getSpecialMessage();
        final Object object = log.getObject();
        final String functionName = log.getFunctionName();
        final Object exception = log.getThrowable();

        this.put(specialMessage, object, functionName, exception);
    }

    //ActualPlatform
    @JsMethod
    public void putF(
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
        final String message = this.logFormatUtil.getS(
            className, functionName, specialMessage);

        final PlayN playN = PlayN.getInstance();
        playN.log().debug(this.LOG_SUCCESS + message);
        //System.out.print(LOG_SUCCESS);
        //System.out.println(message);
    }

    //ActualPlatform
    @JsMethod
    public void putFS(
        final String specialMessage,
        final String className,
        final String functionName) {

//        String className = EMPTY;
//        if (object.getClass().getName() != null)
//        {
//            className = new String(object.getClass().getName());
//        }
        final String message = this.logFormatUtil.getS(
            className, functionName, specialMessage);

        //@playn.core.PlayN::debug(Ljava/lang/String;)(this.LOG_SUCCESS + message);
        final PlayN playN = PlayN.getInstance();
        playN.log().debug(this.LOG_SUCCESS + message);
        //System.out.print(LOG_SUCCESS);
        //System.out.println(message);
    }
    
    //ActualPlatform
    @JsMethod
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
        final String message = this.logFormatUtil.get(
            className, functionName, specialMessage, exception);

        final PlayN playN = PlayN.getInstance();
        if (exception != null) {
            //@playn.core.PlayN::error(Ljava/lang/String;, Ljava/lang/Throwable;)(this.LOG_SUCCESS + message, exception);
            playN.log().error(this.LOG_SUCCESS + message, (Throwable) exception);
        } else {
            //@playn.core.PlayN::debug(Ljava/lang/String;)(this.LOG_SUCCESS + message);
            playN.log().debug(this.LOG_SUCCESS + message);
        }

    }

}
