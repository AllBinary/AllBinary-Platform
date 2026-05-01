package org.allbinary.thread;

import org.allbinary.TsUtil;

public class ThreadObjectUtil
{
    private static final ThreadObjectUtil instance = new ThreadObjectUtil();

    public static ThreadObjectUtil getInstance()
    {
        return ThreadObjectUtil.instance;
    }

    public final PriorityRunnable NULL_PRIORITY_RUNNABLE = new NullPriorityRunnable();
    
    public void notifyObject(Object object)
    {
        object.notify();
    }
    
    public void waitObject(Object object)
            throws Exception
    {
        object.wait();
    }

    public void waitObjectFor(Object object, long delay)
            throws Exception
    {
        TsUtil.getInstance().waitFor(object, delay);
    }
}
