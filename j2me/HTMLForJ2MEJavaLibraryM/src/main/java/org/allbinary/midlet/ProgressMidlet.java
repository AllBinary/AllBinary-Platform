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
package org.allbinary.midlet;

import org.allbinary.midlet.ExitRunnable;
import org.allbinary.midlet.AllBinaryMidlet;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.canvas.Processor;
import org.allbinary.thread.PrimaryThreadPool;
import org.allbinary.thread.ThreadPool;

public class ProgressMidlet extends AllBinaryMidlet
{
    protected void exit(boolean isProgress)
    {

        final Processor processor = MidletExitProcessorFactory.getInstance().getInstance(this);
        
        try
        {
            ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();
            
            Runnable runnable = new ExitRunnable(this, processor, isProgress);

            primaryThreadPool.runTask(runnable);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "exit", e));
            try
            {
                processor.process();
            }
            catch (Exception e2)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "exit", e));
            }
        }
    }
}
