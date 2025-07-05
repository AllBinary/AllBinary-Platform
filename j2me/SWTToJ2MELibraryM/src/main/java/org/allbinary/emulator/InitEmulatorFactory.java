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
package org.allbinary.emulator;

public class InitEmulatorFactory
{
    private static final InitEmulatorFactory instance = new InitEmulatorFactory();

    public static InitEmulatorFactory getInstance()
    {
        return instance;
    }
    
    public void setInitEmulator(boolean initEmulator)
    {
        this.initEmulator = initEmulator;
    }

    public boolean isInitEmulator()
    {
        return initEmulator;
    }

    private boolean initEmulator = false;
}
