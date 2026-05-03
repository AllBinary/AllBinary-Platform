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
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.util.BasicArrayListUtil;

public class ThreadPool
{
    private static final String ROOT_NAME = "-PooledThread-";
    //private static int threadPoolID;

    public static final int NORMAL_PRIORITY = Thread.NORM_PRIORITY;
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final NullRunnable NULL_RUNNABLE = NullRunnable.getInstance();
    protected final ThreadPoolStrings threadPoolStrings = ThreadPoolStrings.getInstance();
    protected final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();

    private final String poolName;
    private final int priority;
    
    private boolean isAlive = false;
    private BasicArrayList taskQueue = BasicArrayListUtil.getInstance().getImmutableInstance();
    private int threadID;
    private int numThreads;
    
    protected boolean runningTask;
    
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
            //this.logUtil.putF(this.commonStrings.INIT, this, this.commonStrings.INIT);
            this.isAlive = true;

            this.taskQueue = new BasicArrayListD();
            PooledThread pooledThread;
            for (int i = 0; i < this.numThreads; i++)
            {
                pooledThread = new PooledThread(this);
                pooledThread.setPriority(this.priority);
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

            //this.logUtil.putF(new StringMaker().append("Add: ").append(task).toString(), this, this.threadPoolStrings.ADD_TASK);
            //PreLogUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);
            final int size = this.taskQueue.size();
            PriorityRunnable runnable;
            PriorityRunnable lowerPriorityRunnable = this.threadObjectUtil.NULL_PRIORITY_RUNNABLE;
            for(int index = 0; index < size; index++) {
                runnable = (PriorityRunnable) this.taskQueue.get(index);
                //this.logUtil.putF(new StringMaker().append(COMPARE_PRIORITY).append(task.getPriority()).toString(), this, this.threadPoolStrings.ADD_TASK);
                if(runnable.getPriority() > task.getPriority()) {
                    lowerPriorityRunnable = runnable;
                    break;
                }
            }
            
            if(lowerPriorityRunnable == this.threadObjectUtil.NULL_PRIORITY_RUNNABLE || lowerPriorityRunnable == this.NULL_RUNNABLE) {
                this.taskQueue.add(task);
            } else {
                //this.logUtil.putF(new StringMaker().append(ADD_PRIORITY).append(task.getPriority()).toString(), this, this.threadPoolStrings.ADD_TASK);
                final int index = this.taskQueue.indexOf(lowerPriorityRunnable);
                //this.logUtil.putF(new StringMaker().append(ADD_PRIORITY).append(index).append(CommonSeps.getInstance().SPACE).append(this.taskQueue.size()).toString(), this, this.threadPoolStrings.ADD_TASK);
                this.taskQueue.addAt(index, task);
            }
            

            this.notify();
        }
        
        }
    }
    
    public void runTask(final Runnable task)
    {
        synchronized (this) {

        if (!this.isAlive)
        {
            this.init();
            //throw new IllegalStateException();
        }
        if (task != null)
        {

            //this.logUtil.putF("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);
            //PreLogUtil.put("Add: ").append(task, this, this.threadPoolStrings.ADD_TASK);

            this.taskQueue.add(task);
            this.notify();
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
                return this.NULL_RUNNABLE;
            }
            this.wait();
        }
        final Runnable runnable = (Runnable) this.taskQueue.removeAt(0);
        return runnable;
        
        }
    }

    public void clear()
    {
        synchronized (this) {
            
        if (this.isAlive)
        {
            //this.logUtil.putF("clear", this, this.commonStrings.RUN);
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
            //this.logUtil.putF("clear2", this, this.commonStrings.RUN);
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
            //this.logUtil.putF("clear3", this, this.commonStrings.RUN);
            this.taskQueue.clear();
            this.notifyAll();
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
            this.taskQueue.clear();
        }
    }

    protected void startTask(final Runnable task)
    {
    }

    protected void completedTask(final Runnable task)
    {
    }

    public String createName() {
        return new StringMaker().append(this.poolName).append(ThreadPool.ROOT_NAME).appendint(this.threadID++).toString();
    }
    
}
