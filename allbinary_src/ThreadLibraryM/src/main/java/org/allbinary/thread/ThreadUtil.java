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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class ThreadUtil
{
    private static final ThreadUtil instance = new ThreadUtil();
    
    public static ThreadUtil getInstance()
    {
        return instance;
    }

    public final String JOIN = "join";
    
    public boolean isRunning(Thread thread)
    {
        if(thread != null && thread.isAlive())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void join(Thread thread)
    throws Exception
    {
        if (ThreadUtil.getInstance().isRunning(thread))
        {
            //PreLogUtil.put("Waiting for Thread To Join/End", this, JOIN);
            LogUtil.put(LogFactory.getInstance("Waiting for Thread To Join/End", this, JOIN));

            //If loading in background then go ahead and show progress while if
            //it is still initializing
            thread.join();
            //PreLogUtil.put("Done waiting for Thread To Join/End", this, JOIN);
        }
    }
}
