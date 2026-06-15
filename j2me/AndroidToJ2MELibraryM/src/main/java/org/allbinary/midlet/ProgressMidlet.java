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

import org.allbinary.TsUtil;
import org.allbinary.canvas.Processor;
import org.allbinary.globals.Globals;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.thread.PrimaryThreadPool;
import org.allbinary.thread.ThreadPool;

public class ProgressMidlet extends AllBinaryMidlet
{

    public final AbeClientInformationInterface abeClientInformation;
    
    public ProgressMidlet(final ClientInformationFactory clientInformationFactory)
    {
        Globals.getInstance().init(TsUtil.getInstance().getClassClassLoader(this), StringUtil.getInstance().EMPTY_STRING);
        
        this.preInit();
        this.abeClientInformation = clientInformationFactory.getInstance();
    }   

    public void preInit() {
    }
    
    protected void exitProgress(boolean isProgress)
    {

        final Processor processor = MidletExitProcessorFactory.getInstance().getExitInstance(this);
        
        try
        {
            //this.logUtil.put("isProgress: " + isProgress, this, "exit", new Exception());
            
            final ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();
            
            final Runnable runnable = new ExitRunnable(this, processor, isProgress);

            primaryThreadPool.runTask(runnable);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "exit", e);
            try
            {
                processor.process();
            }
            catch (Exception e2)
            {
                this.logUtil.put(this.commonStrings.EXCEPTION, this, "exit", e);
            }
        }
    }
}
