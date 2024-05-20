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

public class SecondaryThreadPool extends ThreadPool
{
    private static final ThreadPool instance = new SecondaryThreadPool(1);

    public static ThreadPool getInstance()
    {
        return instance;
    }

    public SecondaryThreadPool(int total)
    {
        super(total);
    }

    public synchronized void runTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(task).append(System.currentTimeMillis()).toString(), this, "runTask"));

        super.runTask(task);
    }

    private final String START_TASK = "Started Task: ";
    private final String COMPLETE_TASK = "Completed Task: ";

    protected void startTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(START_TASK).append(task).append(System.currentTimeMillis()).toString(), this, CommonStrings.getInstance().RUN));
    }

    protected void completedTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(COMPLETE_TASK).append(task).append(System.currentTimeMillis()).toString(), this, CommonStrings.getInstance().RUN));
    }

}
