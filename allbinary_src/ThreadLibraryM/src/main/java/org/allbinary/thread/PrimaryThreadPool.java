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

import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class PrimaryThreadPool extends ThreadPool
{

    //Watch out for the Android/J2ME thread limit
    private static final ThreadPool THREAD_POOL = new ThreadPool(1);

    public static ThreadPool getInstance()
    {
        return THREAD_POOL;
    }

    public PrimaryThreadPool(int total)
    {
        super(total);
    }

    public synchronized void runTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(task).append(System.currentTimeMillis()).toString(), this, ADD_TASK));

        super.runTask(task);
    }

    private final String START_TASK = "Started Task: ";
    private final String COMPLETE_TASK = "Completed Task: ";

    protected void startTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(START_TASK).append(task).toString(), this, this.commonStrings.RUN));
    }

    protected void completedTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(COMPLETE_TASK).append(task).toString(), this, this.commonStrings.RUN));
    }

}
