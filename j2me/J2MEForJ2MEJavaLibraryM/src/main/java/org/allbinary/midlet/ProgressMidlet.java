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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.canvas.Processor;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.thread.PrimaryThreadPool;
import org.allbinary.thread.ThreadPool;

public class ProgressMidlet extends AllBinaryMidlet
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public final AbeClientInformationInterface abeClientInformation;
    
    public ProgressMidlet(final ClientInformationFactory clientInformationFactory)
    {
        this.preInit();
        this.abeClientInformation = clientInformationFactory.getInstance();
    }   
    
    public void preInit() {
    }
    
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
            logUtil.put(commonStrings.EXCEPTION, this, "exit", e);
            try
            {
                processor.process();
            }
            catch (Exception e2)
            {
                logUtil.put(commonStrings.EXCEPTION, this, "exit", e);
            }
        }
    }
}
