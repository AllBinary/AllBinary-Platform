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
    }

    public void waitObject(Object object)
            throws Exception
    {
    }

    public void waitObject(Object object, int delay)
            throws Exception
    {
    }
}
