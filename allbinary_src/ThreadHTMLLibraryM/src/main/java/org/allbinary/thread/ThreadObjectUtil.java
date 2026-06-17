package org.allbinary.thread;

public class ThreadObjectUtil {

    private static final ThreadObjectUtil instance = new ThreadObjectUtil();

    public static ThreadObjectUtil getInstance() {
        return ThreadObjectUtil.instance;
    }

    public final PriorityRunnable NULL_PRIORITY_RUNNABLE = new NullPriorityRunnable();

    public void notifyObject(Object object) {
    }

    public void waitObject(Object object)
        throws Exception {
    }

    public void waitObjectFor(Object object, long delay)
        throws Exception {
    }

    public void processThread(final Runnable runnable) {
        runnable.run();
    }
    
}
