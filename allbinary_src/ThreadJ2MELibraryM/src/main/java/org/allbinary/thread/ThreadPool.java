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
package org.allbinary.thread;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class ThreadPool
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    protected final String ADD_TASK = "addTask";
    protected final String START_TASK = "Started Task: ";
    protected final String COMPLETE_TASK = "Completed Task: ";
    
    private boolean isAlive;
    private BasicArrayList taskQueue;
    private int threadID;
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

            //LogUtil.put(LogFactory.getInstance("Add: ").append(task, this, "runTask"));
            //PreLogUtil.put("Add: ").append(task, this, "runTask");

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
        if (!this.isAlive) {
            return false;
        }
        
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

        private static final String ROOT_NAME = "-PooledThread-";
        
        public PooledThread()
        {
            //super(ThreadPool.this, 
            super(new StringMaker().append(ThreadPool.this.toString()).append(ROOT_NAME).append(threadID++).toString());
            LogUtil.put(LogFactory.getInstance(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR));
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
                    //LogUtil.put(LogFactory.getInstance(task + " with Thread: " + this.toString(), this, commonStrings.RUN));
                    runningTask = true;

                    startTask(task);

                } catch (InterruptedException ex)
                {
                    LogUtil.put(LogFactory.getInstance(INTERRUPT_EXCEPTION, this, commonStrings.RUN));
                    break;
                }

                if (task == null)
                {
                    break;
                }
                /*
                else
                {
                PreLogUtil.put("Running: ").append(task, this, commonStrings.RUN);
                }
                 */

                try
                {
                    task.run();
                    completedTask(task);
                    runningTask = false;
                } catch (Exception e)
                {
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(task).toString(), this, commonStrings.RUN, e));
                }
            }

            threadStopped();
        }
    }
}
