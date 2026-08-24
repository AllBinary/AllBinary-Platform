/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public class ABRunnable implements RunnableInterface {
    
    private boolean running = false;
    
    @Override
    @JsMethod
    public void setThread(Thread thread)
    {
    }

    @Override
    @JsMethod
    public synchronized boolean isRunning()
    {
        return this.running;
    }

    @Override
    @JsMethod
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }
    
    @Override
    @JsMethod
    public void run() {
        
    }

    @JsMethod
    public int getType() {
        return -1;
    }
    
}
