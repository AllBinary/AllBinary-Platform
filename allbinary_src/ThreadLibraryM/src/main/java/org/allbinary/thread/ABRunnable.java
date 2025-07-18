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

/**
 *
 * @author User
 */
public class ABRunnable implements RunnableInterface {
    
    private boolean running = false;
    
    @Override
    public void setThread(Thread thread)
    {
    }

    @Override
    public synchronized boolean isRunning()
    {
        return running;
    }

    @Override
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }
    
    @Override
    public void run() {
        
    }

    public int getType() {
        return -1;
    }
    
}
