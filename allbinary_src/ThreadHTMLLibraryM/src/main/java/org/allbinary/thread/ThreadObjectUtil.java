package org.allbinary.thread;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class ThreadObjectUtil {

    private static final ThreadObjectUtil instance = new ThreadObjectUtil();

    @JsMethod
    public static ThreadObjectUtil getInstance() {
        return ThreadObjectUtil.instance;
    }

    @JsProperty
    public final PriorityRunnable NULL_PRIORITY_RUNNABLE = new NullPriorityRunnable();

    @JsMethod
    public void notifyObject(Object object) {
    }

    @JsMethod
    public void waitObject(Object object)
        throws Exception {
    }

    @JsMethod
    public void waitObjectFor(Object object, long delay)
        throws Exception {
    }

    @JsMethod
    public void processThread(final Runnable runnable) {
        runnable.run();
    }
    
}
