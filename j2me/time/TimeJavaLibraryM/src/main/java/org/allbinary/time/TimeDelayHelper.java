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
package org.allbinary.time;

import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.basic.string.StringUtil;

public class TimeDelayHelper
{
    private long startTime = -1;
    private long elapsedTimeAtPause = Long.MIN_VALUE;
    private int delay;

    public TimeDelayHelper(int delay)
    {
        this.setDelay(delay);
        this.setStartTime();
    }
    
    public boolean isTime()
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > this.getDelay())
        {

            this.startTime = currentTime;
            return true;
        }
        return false;
    }

    public boolean isTimeSince(int delay)
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > delay)
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }
    
    public boolean isTime(long currentTime)
    {
        if (currentTime - this.startTime > this.getDelay())
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }

    public boolean isTimeWithoutReset(long currentTime)
    {
        if (currentTime - this.startTime > this.getDelay())
        {
            return true;
        }
        return false;
    }
    
    public boolean isTimeSince(int delay, long currentTime)
    {
        if (currentTime - this.startTime > this.getDelay())
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }
    
    public long getElapsed()
    {
        return System.currentTimeMillis() - this.startTime;
    }

    public long getElapsed(long currentTime)
    {
        return currentTime - this.startTime;
    }

    public boolean isElapsed(long currentTime, long time)
    {
        if (this.getElapsed(currentTime) > time)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isElapsed(long time)
    {
        if (this.getElapsed() > time)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public int getDelay()
    {
        return delay;
    }
    
    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public long getStartTime() 
    {
        return startTime;
    }

    public void setStartTime()
    {
        this.startTime = System.currentTimeMillis();
    }
    
    public void pause() {
        this.elapsedTimeAtPause = this.getElapsed();
        this.startTime = Long.MAX_VALUE;
    }

    public boolean resume() {
        if(this.elapsedTimeAtPause != Long.MIN_VALUE) {
            this.startTime = System.currentTimeMillis() + this.elapsedTimeAtPause;
            this.elapsedTimeAtPause = Long.MIN_VALUE;
            this.setStartTime();
            return true;
        }
        return false;
    }
    
    public String toString(long currentTime)
    {
        long elapsed = this.getElapsed(currentTime);

        if(elapsed > 0)
        {
            return new StringMaker().append("Start: ").append(this.startTime).append(" Current: ").append(currentTime).append(" Elapsed: ").append(Long.toString(elapsed)).toString();
        }
        else
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }
    
    public String toString()
    {
        long currentTime = System.currentTimeMillis();
        long elapsed = this.getElapsed(currentTime);

        if(elapsed > 0)
        {
            return new StringMaker().append("Start: ").append(this.startTime).append(" Current: ").append(currentTime).append(" Elapsed: ").append(Long.toString(elapsed)).toString();
        }
        else
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }
    
}
