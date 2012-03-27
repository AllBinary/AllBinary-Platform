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

import javax.microedition.lcdui.Displayable;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.canvas.Processor;

public class ExitRunnable implements Runnable
{
    private final AllBinaryMidlet midlet;
    private final Processor processor;
    private final boolean isProgress;
    
    public ExitRunnable(AllBinaryMidlet midlet, Processor processor, boolean isProgress)
    {
        this.midlet = midlet;
        this.isProgress = isProgress;
        this.processor = processor;
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_RUNNABLE, this, CommonStrings.getInstance().RUN));

            this.midlet.destroyApp(false, this.isProgress);

            // Why set to null
            this.midlet.setDisplay((Displayable) null);

            // TWB - Only remove from context when multiple midlets share the
            // same emulator
            // Note this would also require that the midlet be reinitialized
            // when resuming from onSaveInstanceState
            this.midlet.notifyDestroyed();

            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END_RUNNABLE, this, CommonStrings.getInstance().RUN));

            processor.process();
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));

            try
            {
                processor.process();
            } catch (Exception e2)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
            }
        }
    }
}
