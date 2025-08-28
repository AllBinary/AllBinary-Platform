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

import javax.microedition.lcdui.NullCanvas;

import org.allbinary.canvas.Processor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class ExitRunnable implements Runnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final AllBinaryMidlet midlet;
    private final Processor processor;
    private final boolean isProgress;
    
    public ExitRunnable(AllBinaryMidlet midlet, Processor processor, boolean isProgress)
    {
        this.midlet = midlet;
        this.isProgress = isProgress;
        this.processor = processor;
    }
    
    @Override
    public void run()
    {
        try
        {
            logUtil.put(commonStrings.START_RUNNABLE, this, commonStrings.RUN);

            this.midlet.destroyApp(false, this.isProgress);

            // Why set to null
            this.midlet.setDisplay(NullCanvas.NULL_CANVAS);

            // TWB - Only remove from context when multiple midlets share the
            // same emulator
            // Note this would also require that the midlet be reinitialized
            // when resuming from onSaveInstanceState
            this.midlet.notifyDestroyed();

            logUtil.put(commonStrings.END_RUNNABLE, this, commonStrings.RUN);

            processor.process();
        } catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);

            try
            {
                processor.process();
            } catch (Exception e2)
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }
    }
}
