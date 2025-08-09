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
package org.allbinary.debug;

import android.os.Debug;

public class AndroidDebug implements DebugInterface
{
    private long startTime = Long.MAX_VALUE;
    private boolean running = false;
    
    private int bufferSize = 32 * 1024 * 1024; //24 * 1024 * 1024;
    
    @Override
    public void start()
    {
        this.setStartTime(System.currentTimeMillis());
        //To get the trace log Use: adb pull /sdcard/trace.trace ./tmp
        //traceview G:\mnt\tmp\trace
        Debug.startMethodTracing("trace", bufferSize);
        setRunning(true);
    }

    @Override
    public void stop()
    {
        startTime = Long.MAX_VALUE;
        Debug.stopMethodTracing();
        setRunning(false);
    }

    @Override
    public long getMaxTime()
    {
        return 120000;
    }
    
    private void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    @Override
    public long getStartTime()
    {
        return startTime;
    }

    private void setRunning(boolean running)
    {
        this.running = running;
    }

    @Override
    public boolean isRunning()
    {
        return running;
    }
}
