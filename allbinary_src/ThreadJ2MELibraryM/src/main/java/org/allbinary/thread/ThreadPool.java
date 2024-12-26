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
import org.allbinary.logic.string.CommonSeps;

public class ThreadPool
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final ThreadPoolStrings threadPoolStrings = ThreadPoolStrings.getInstance();

    private final String poolName;
    private final int priority;
    
    private boolean isAlive;
    private BasicArrayList taskQueue;
    private int threadID;
    private int numThreads;

    //private static int threadPoolID;

    public ThreadPool(final String poolName, final int numThreads)
    {
        this(poolName, numThreads, Thread.NORM_PRIORITY);
    }
    
    public ThreadPool(final String poolName, final int numThreads, final int priority)
    {
        this.poolName = poolName;
        this.priority = priority;
        this.numThreads = numThreads;
    }

    public void init()
    {
        if (!this.isAlive)
        {
            this.isAlive = true;

            this.taskQueue = new BasicArrayList();
            PooledThread pooledThread;
            for (int i = 0; i < this.numThreads; i++)
            {
                pooledThread = new PooledThread();
                pooledThread.setPriority(priority);
                pooledThread.start();
            }
        }
    }

    //private final String COMPARE_PRIORITY = "Compare priority task: ";
    //private final String ADD_PRIORITY = "Add priority task: ";
    
    public synchronized void runTaskWithPriority(final PriorityRunnable task)
    {
        if (!this.isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }

        if (task != null)
        {

            //LogUtil.put(LogFactory.getInstance("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK));
            //PreLogUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);
            final int size = this.taskQueue.size();
            PriorityRunnable runnable;
            PriorityRunnable lowerPriorityRunnable = null;
            for(int index = 0; index < size; index++) {
                runnable = (PriorityRunnable) this.taskQueue.get(index);
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append(COMPARE_PRIORITY).append(task.getPriority()).toString(), this, this.threadPoolStrings.ADD_TASK));
                if(runnable.getPriority() > task.getPriority()) {
                    lowerPriorityRunnable = runnable;
                    break;
                }
            }
            
            if(lowerPriorityRunnable == null) {
                this.taskQueue.add(task);
            } else {
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append(ADD_PRIORITY).append(task.getPriority()).toString(), this, this.threadPoolStrings.ADD_TASK));
                final int index = this.taskQueue.indexOf(lowerPriorityRunnable);
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append(ADD_PRIORITY).append(index).append(CommonSeps.getInstance().SPACE).append(this.taskQueue.size()).toString(), this, this.threadPoolStrings.ADD_TASK));
                this.taskQueue.add(index, task);
            }
            

            notify();
        }
    }
    
    public synchronized void runTask(final Runnable task)
    {
        if (!isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }
        if (task != null)
        {

            //LogUtil.put(LogFactory.getInstance("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK));
            //PreLogUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);

            this.taskQueue.add(task);
            notify();
        }
    }

    protected synchronized Runnable getTask()
            throws InterruptedException
    {
        while (this.taskQueue.size() == 0)
        {
            if (!this.isAlive)
            {
                return null;
            }
            this.wait();
        }
        return (Runnable) this.taskQueue.remove(0);
    }

    public synchronized void clear()
    {
        if (this.isAlive)
        {
            this.taskQueue.clear();
        }
    }
    
    public synchronized void close()
    {
        if (this.isAlive)
        {
            this.isAlive = false;
            this.taskQueue.clear();
            //interrupt();
        }
    }

    public void join()
    {

        synchronized (this)
        {
            this.isAlive = false;
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

    protected void startTask(final Runnable task)
    {
    }

    protected void completedTask(final Runnable task)
    {
    }

    private boolean runningTask;

    private class PooledThread extends Thread
    {

        private static final String ROOT_NAME = "-PooledThread-";
        
        public PooledThread()
        {
            //super(ThreadPool.this, 
            super(new StringMaker().append(poolName).append(ROOT_NAME).append(threadID++).toString());
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
