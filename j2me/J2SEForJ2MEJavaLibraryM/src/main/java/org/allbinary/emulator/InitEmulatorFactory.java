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
