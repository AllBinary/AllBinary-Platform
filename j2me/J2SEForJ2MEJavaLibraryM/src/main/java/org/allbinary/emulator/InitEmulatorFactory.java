package org.allbinary.emulator;

public class InitEmulatorFactory
{
    private static final InitEmulatorFactory instance = new InitEmulatorFactory();

    public static InitEmulatorFactory getInstance()
    {
        return InitEmulatorFactory.instance;
    }
    
    public void setInitEmulator(boolean initEmulator)
    {
        this.initEmulator = initEmulator;
    }

    public boolean isInitEmulator()
    {
        return this.initEmulator;
    }

    private boolean initEmulator = false;
}
