package org.allbinary.thread;

public class ThreadObjectUtil
{
    private static final ThreadObjectUtil instance = new ThreadObjectUtil();

    public static ThreadObjectUtil getInstance()
    {
        return instance;
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

    public void waitObject(Object object, long delay)
            throws Exception
    {
        object.wait(delay);
    }
}
