/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

/**
 *
 * @author User
 */
public class PooledThread extends Thread {

    private final LogUtil logUtil = LogUtil.getInstance();
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final ThreadObjectUtil threadObjectUtil = ThreadObjectUtil.getInstance();
    
    private final ThreadPool threadPool;
    
    public PooledThread(final ThreadPool threadPool) {
        //super(ThreadPool.this, 
        super(threadPool.createName());
        this.logUtil.putF(this.commonStrings.CONSTRUCTOR, this, this.commonStrings.CONSTRUCTOR);
        
        this.threadPool = threadPool;
    }

    private final String INTERRUPT_EXCEPTION = "Exit InterruptedException";

    @Override
    public void run() {

        this.threadPool.threadStarted();

        while (true) //while (!isInterrupted())
        {

            Runnable task2 = this.threadObjectUtil.NULL_PRIORITY_RUNNABLE;
            try {
                task2 = this.threadPool.getTask();
                //this.logUtil.putF(task + " with Thread: " + this.toString(), this, commonStrings.RUN);
                this.threadPool.runningTask = true;

                this.threadPool.startTask(task2);

            } catch (InterruptedException ex) {
                final LogUtil logUtil = LogUtil.getInstance();
                logUtil.putF(this.INTERRUPT_EXCEPTION, this, this.commonStrings.RUN);
                break;
            }

            if (task2 == this.threadObjectUtil.NULL_PRIORITY_RUNNABLE) {
                break;
            }
            /*
                else
                {
                PreLogUtil.put("Running: ").append(task, this, commonStrings.RUN);
                }
             */

            try {
                task2.run();
                this.threadPool.completedTask(task2);
                this.threadPool.runningTask = false;
            } catch (Exception e) {
                final LogUtil logUtil = LogUtil.getInstance();
                logUtil.put(new StringMaker().append(this.commonStrings.EXCEPTION_LABEL).append(StringUtil.getInstance().toString(task2)).toString(), this, this.commonStrings.RUN, e);
            }
        }

        this.threadPool.threadStopped();
    }
}
