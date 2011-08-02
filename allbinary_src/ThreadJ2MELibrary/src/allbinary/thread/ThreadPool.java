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
package allbinary.thread;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class ThreadPool
{
    private boolean isAlive;
    private BasicArrayList taskQueue;
    //private int threadID;
    private int numThreads;
    //private static int threadPoolID;

    public ThreadPool(int numThreads)
    {
        this.numThreads = numThreads;
    }

    public void init()
    {
        if (!this.isAlive)
        {
            isAlive = true;

            taskQueue = new BasicArrayList();
            for (int i = 0; i < numThreads; i++)
            {
                new PooledThread().start();
            }
        }
    }

    public synchronized void runTask(Runnable task)
    {
        if (!isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }
        if (task != null)
        {

            //LogUtil.put(LogFactory.getInstance("Add: " + task, this, "runTask"));
            //PreLogUtil.put("Add: " + task, this, "runTask");

            taskQueue.add(task);
            notify();
        }
    }

    protected synchronized Runnable getTask()
            throws InterruptedException
    {
        while (taskQueue.size() == 0)
        {
            if (!isAlive)
            {
                return null;
            }
            this.wait();
        }
        return (Runnable) taskQueue.remove(0);
    }

    public synchronized void close()
    {
        if (isAlive)
        {
            isAlive = false;
            taskQueue.clear();
            //interrupt();
        }
    }

    public void join()
    {

        synchronized (this)
        {
            isAlive = false;
            notifyAll();
        }

        /*
        Thread[] threads = new Thread[MAX];
        int count = threads.length;
        for (int i = 0; i < count; i++) {
        try {
        threads[i].join();
        } catch (InterruptedException ex) {
        }
        }
         */
    }

    public boolean isBusy()
    {
        if (this.taskQueue.size() > 0)
        {
            return true;
        }

        if (this.runningTask)
        {
            return true;
        }
        return false;
    }

    protected void threadStarted()
    {
    }

    protected void threadStopped()
    {
        if (this.numThreads == 1)
        {
            this.isAlive = false;
        }
    }

    protected void startTask(Runnable task)
    {
    }

    protected void completedTask(Runnable task)
    {
    }

    private boolean runningTask;

    private class PooledThread extends Thread
    {

        public PooledThread()
        {
            //super(ThreadPool.this, "PooledThread-" + (threadID++));
        }

        private final String INTERRUPT_EXCEPTION = "Exit InterruptedException";

        public void run()
        {

            threadStarted();

            while (true)
            //while (!isInterrupted())
            {

                Runnable task = null;
                try
                {
                    task = getTask();
                    runningTask = true;

                    startTask(task);

                } catch (InterruptedException ex)
                {
                    LogUtil.put(LogFactory.getInstance(INTERRUPT_EXCEPTION, this, CommonStrings.getInstance().RUN));
                    break;
                }

                if (task == null)
                {
                    break;
                }
                /*
                else
                {
                PreLogUtil.put("Running: " + task, this, CommonStrings.getInstance().RUN);
                }
                 */

                try
                {
                    task.run();
                    completedTask(task);
                    runningTask = false;
                } catch (Exception e)
                {
                    LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION_LABEL + task, this, CommonStrings.getInstance().RUN, e));
                }
            }

            threadStopped();
        }
    }
}
