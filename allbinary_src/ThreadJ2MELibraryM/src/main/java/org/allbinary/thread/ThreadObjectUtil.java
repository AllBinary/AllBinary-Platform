package org.allbinary.thread;

public class ThreadObjectUtil
{
    private static final ThreadObjectUtil instance = new ThreadObjectUtil();

    public static ThreadObjectUtil getInstance()
    {
        return instance;
    }

    public void notifyObject(Object object)
    {
        object.notify();
    }
    
    public void waitObject(Object object)
            throws Exception
    {
        object.wait();
    }

    public void waitObject(Object object, int delay)
            throws Exception
    {
        object.wait(delay);
    }
}
