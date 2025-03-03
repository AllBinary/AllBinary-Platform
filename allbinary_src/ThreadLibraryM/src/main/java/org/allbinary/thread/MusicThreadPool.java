/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class MusicThreadPool extends ThreadPool
{

    //Watch out for the Android/J2ME thread limit
    private static final ThreadPool THREAD_POOL = new ThreadPool("Music", 2);

    public static ThreadPool getInstance()
    {
        return THREAD_POOL;
    }

    public MusicThreadPool(final String poolName, final int numThreads) {
        super(poolName, numThreads);
    }
    
    public synchronized void runTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(StringUtil.getInstance().toString(task)).append(System.currentTimeMillis()).toString(), this, this.threadPoolStrings.ADD_TASK));

        super.runTask(task);
    }

    protected void startTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.threadPoolStrings.START_TASK).append(StringUtil.getInstance().toString(task)).append(System.currentTimeMillis()).toString(), this, commonStrings.RUN));
    }

    protected void completedTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.threadPoolStrings.COMPLETE_TASK).append(StringUtil.getInstance().toString(task)).append(System.currentTimeMillis()).toString(), this, commonStrings.RUN));
    }

}
