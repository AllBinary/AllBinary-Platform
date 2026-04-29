/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
public class NullABRunnable extends ABRunnable {
 
    private static final NullABRunnable instance = new NullABRunnable();

    /**
     * @return the instance
     */
    public static NullABRunnable getInstance() {
        return NullABRunnable.instance;
    }
    
    @Override
    public synchronized boolean isRunning()
    {
        return false;
    }
    
}
