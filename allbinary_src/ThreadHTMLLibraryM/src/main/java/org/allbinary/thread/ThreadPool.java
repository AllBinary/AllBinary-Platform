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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class ThreadPool
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final ThreadPoolStrings threadPoolStrings = ThreadPoolStrings.getInstance();
    
    //private final String poolName;
    //private final int priority;
    
    private boolean isAlive;
    private BasicArrayList taskQueue = new BasicArrayList();
    //private int threadID;
    private int numThreads;
    //private static int threadPoolID;

    private boolean runningTask;
    
    public ThreadPool(final String poolName, final int numThreads)
    {
        this(poolName, numThreads, 5); //5 = Thread.NORM_PRIORITY
    }
    
    public ThreadPool(final String poolName, final int numThreads, final int priority)
    {
//        this.poolName = poolName;
//        this.priority = priority;
//        this.numThreads = numThreads;
    }
    
    //TWB - PlayN Single Thread Fix
    private PriorityRunnable currentPriorityRunnable = null;
    public void runAPriorityTask()
            throws Exception
    {

        if(currentPriorityRunnable != null && !currentPriorityRunnable.isDone()) {
            currentPriorityRunnable.run();
        } else {
            currentPriorityRunnable = (PriorityRunnable) this.getTask();

            if (currentPriorityRunnable != null)
            {
                currentPriorityRunnable.run();
            }
        }

    }

    public void runATask()
            throws Exception
    {
            Runnable runnable = this.getTask();

            if (runnable != null)
            {
                runnable.run();
            }
    }
    
    public void init()
    {
        if (!this.isAlive)
        {
            isAlive = true;

            taskQueue = new BasicArrayList();
            //for (int i = 0; i < numThreads; i++)
            //{
                //new PooledThread().start();
            //}
        }
    }

    
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
            

            //notify();
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
            //LogUtil.put(LogFactory.getInstance("Add: ").append(task, this, "runTask"));
            //PreLogUtil.put("Add: ").append(task, this, "runTask");

            taskQueue.add(task);
            //TWB - Playn Testing
            //notify();
        }
    }

    protected synchronized Runnable getTask()
            throws InterruptedException
    {
        if(taskQueue.isEmpty())
        {
            return null;
        }
        /*
        while (taskQueue.size() == 0)
        {
            if (!isAlive)
            {
                return null;
            }
            //TWB - Playn Testing
            //this.wait();
        }
        */
        return (Runnable) taskQueue.remove(0);
    }

    public synchronized void clear() 
    {
        
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
            //TWB - Playn Testing
            //notifyAll();
        }

        //Thread[] threads = new Thread[MAX];
        //int count = threads.length;
        //for (int i = 0; i < count; i++) {
        //try {
        //threads[i].join();
        //} catch (InterruptedException ex) {
        //}
        //}
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
}
