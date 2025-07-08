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

import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

//This is mainly for network communications.
public class ImageThreadPool extends ThreadPool
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ThreadPool instance = new ImageThreadPool("ImageLoader", 1);

    public static ThreadPool getInstance()
    {
        return instance;
    }

    public ImageThreadPool(final String poolName, final int numThreads) {
        super(poolName, numThreads);
    }

    public synchronized void runTask(Runnable task)
    {
        //logUtil.put(new StringMaker().append(task).append(System.currentTimeMillis()).toString(), this, this.threadPoolStrings.ADD_TASK);

        super.runTask(task);
    }

    protected void startTask(Runnable task)
    {
        //logUtil.put(new StringMaker().append(this.threadPoolStrings.START_TASK).append(task).append(System.currentTimeMillis()).toString(), this, this.commonStrings.RUN);
    }

    protected void completedTask(Runnable task)
    {
        //logUtil.put(new StringMaker().append(this.threadPoolStrings.COMPLETE_TASK).append(task).append(System.currentTimeMillis()).toString(), this, this.commonStrings.RUN);
    }

}
