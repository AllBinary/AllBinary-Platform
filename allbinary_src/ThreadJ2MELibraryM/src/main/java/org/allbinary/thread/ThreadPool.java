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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class ThreadPool
{
    private static final String ROOT_NAME = "-PooledThread-";
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final ThreadPoolStrings threadPoolStrings = ThreadPoolStrings.getInstance();

    private final String poolName;
    private final int priority;
    
    private boolean isAlive = false;
    private BasicArrayList taskQueue = BasicArrayListUtil.getInstance().getImmutableInstance();
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
            //logUtil.put(this.commonStrings.INIT, this, this.commonStrings.INIT);
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

    public void runAPriorityTask()
            throws Exception
    {
        throw new RuntimeException();
    }
    
    //private final String COMPARE_PRIORITY = "Compare priority task: ";
    //private final String ADD_PRIORITY = "Add priority task: ";
    
    public void runTaskWithPriority(final PriorityRunnable task)
    {
        synchronized (this) {

        if (!this.isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }

        if (task != null)
        {

            //logUtil.put(new StringMaker().append("Add: ").append(task).toString(), this, this.threadPoolStrings.ADD_TASK);
            //PreLogUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);
            final int size = this.taskQueue.size();
            PriorityRunnable runnable;
            PriorityRunnable lowerPriorityRunnable = ThreadObjectUtil.getInstance().NULL_PRIORITY_RUNNABLE;
            for(int index = 0; index < size; index++) {
                runnable = (PriorityRunnable) this.taskQueue.get(index);
                //logUtil.put(new StringMaker().append(COMPARE_PRIORITY).append(task.getPriority()).toString(), this, this.threadPoolStrings.ADD_TASK);
                if(runnable.getPriority() > task.getPriority()) {
                    lowerPriorityRunnable = runnable;
                    break;
                }
            }
            
            if(lowerPriorityRunnable == null || lowerPriorityRunnable == NullRunnable.getInstance()) {
                this.taskQueue.add(task);
            } else {
                //logUtil.put(new StringMaker().append(ADD_PRIORITY).append(task.getPriority()).toString(), this, this.threadPoolStrings.ADD_TASK);
                final int index = this.taskQueue.indexOf(lowerPriorityRunnable);
                //logUtil.put(new StringMaker().append(ADD_PRIORITY).append(index).append(CommonSeps.getInstance().SPACE).append(this.taskQueue.size()).toString(), this, this.threadPoolStrings.ADD_TASK);
                this.taskQueue.add(index, task);
            }
            

            notify();
        }
        
        }
    }
    
    public void runTask(final Runnable task)
    {
        synchronized (this) {

        if (!isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }
        if (task != null)
        {

            //logUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);
            //PreLogUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);

            this.taskQueue.add(task);
            notify();
        }
        
        }
    }

    protected Runnable getTask()
            throws InterruptedException
    {
        synchronized (this) {
            
        while (this.taskQueue.size() == 0)
        {
            if (!this.isAlive)
            {
                return NullRunnable.getInstance();
            }
            this.wait();
        }
        final Runnable runnable = (Runnable) this.taskQueue.remove(0);
        return runnable;
        
        }
    }

    public void clear()
    {
        synchronized (this) {
            
        if (this.isAlive)
        {
            //logUtil.put("clear", this, this.commonStrings.RUN);
            this.taskQueue.clear();
        }
        
        }
    }
    
    public void close()
    {
        synchronized (this) {

        if (this.isAlive)
        {
            this.isAlive = false;
            //logUtil.put("clear2", this, this.commonStrings.RUN);
            this.taskQueue.clear();
            //interrupt();
        }
        
        }
    }

    public void join()
    {

        synchronized (this)
        {
            this.isAlive = false;
            //logUtil.put("clear3", this, this.commonStrings.RUN);
            this.taskQueue.clear();
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
            taskQueue.clear();
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
   
        public PooledThread()
        {
            //super(ThreadPool.this, 
            super(new StringMaker().append(poolName).append(ROOT_NAME).append(threadID++).toString());
            logUtil.put(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR);
        }

        private final String INTERRUPT_EXCEPTION = "Exit InterruptedException";

        @Override
        public void run()
        {

            threadStarted();

            final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
            
            while (true)
            //while (!isInterrupted())
            {

                Runnable task2 = threadObjectUtil.NULL_PRIORITY_RUNNABLE;
                try
                {
                    task2 = getTask();
                    //logUtil.put(task + " with Thread: " + this.toString(), this, commonStrings.RUN);
                    runningTask = true;

                    startTask(task2);

                } catch (InterruptedException ex)
                {
                    logUtil.put(INTERRUPT_EXCEPTION, this, commonStrings.RUN);
                    break;
                }

                if (task2 == threadObjectUtil.NULL_PRIORITY_RUNNABLE)
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
                    task2.run();
                    completedTask(task2);
                    runningTask = false;
                } catch (Exception e)
                {
                    logUtil.put(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(StringUtil.getInstance().toString(task2)).toString(), this, commonStrings.RUN, e);
                }
            }

            threadStopped();
        }
    }
}
