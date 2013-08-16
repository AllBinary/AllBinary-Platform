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
package allbinary.midlet;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.canvas.Processor;
import allbinary.thread.PrimaryThreadPool;
import allbinary.thread.ThreadPool;

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
