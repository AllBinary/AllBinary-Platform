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

//import abcs.globals.Globals;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.canvas.Processor;
import allbinary.thread.PrimaryThreadPool;

public class ProgressMidlet extends AllBinaryMidlet
{
    public ProgressMidlet()
    {
        //Globals.init(this.getClass().getClassLoader(), StringUtil.getInstance().EMPTY_STRING);
    }
    
    protected void exit(boolean isProgress)
    {

        final Processor processor = MidletExitProcessorFactory.getInstance().getInstance(this);
        
        try
        {
            PrimaryThreadPool.getInstance().runTask(new ExitRunnable(this, processor, isProgress));
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
