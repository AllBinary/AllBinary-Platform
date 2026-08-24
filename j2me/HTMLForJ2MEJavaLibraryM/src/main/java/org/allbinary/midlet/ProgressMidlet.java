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

import jsinterop.annotations.JsType;

import org.allbinary.canvas.Processor;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.thread.PrimaryThreadPool;
import org.allbinary.thread.ThreadPool;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ProgressMidlet extends AllBinaryMidlet
{

    @JsProperty
    public final AbeClientInformationInterface abeClientInformation;
    
    @JsConstructor
    public ProgressMidlet(final ClientInformationFactory clientInformationFactory)
    {
        this.preInit();
        this.abeClientInformation = clientInformationFactory.getInstance();
    }   

    @JsMethod
    public void preInit() {
    }
    
    @JsMethod
    protected void exitProgress(boolean isProgress)
    {

        final Processor processor = MidletExitProcessorFactory.getInstance().getExitInstance(this);
        
        try
        {
            ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();
            
            Runnable runnable = new ExitRunnable(this, processor, isProgress);

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
