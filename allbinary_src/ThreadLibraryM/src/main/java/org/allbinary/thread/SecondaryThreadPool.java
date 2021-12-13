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

import org.allbinary.logic.basic.string.CommonLabels;
import org.allbinary.logic.basic.string.CommonStrings;
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
        LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START_LABEL + task, this, "runTask"));

        super.runTask(task);
    }

    private final String START_TASK = "Started Task: ";
    private final String COMPLETE_TASK = "Completed Task: ";

    protected void startTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(START_TASK + task, this, CommonStrings.getInstance().RUN));
    }

    protected void completedTask(Runnable task)
    {
        LogUtil.put(LogFactory.getInstance(COMPLETE_TASK + task, this, CommonStrings.getInstance().RUN));
    }

}
