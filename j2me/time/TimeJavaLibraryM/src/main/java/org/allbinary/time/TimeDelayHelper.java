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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class TimeDelayHelper
{
    private long startTime = -1;
    private long elapsedTimeAtPause = Long.MIN_VALUE;
    @JsProperty
    public int delay;

    @JsConstructor
    public TimeDelayHelper(final int delay)
    {
        this.delay = delay;
        this.setStartTimeTNT();
    }
    
    @JsMethod
    public boolean isTimeTNT()
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > this.delay)
        {

            this.startTime = currentTime;
            return true;
        }
        return false;
    }

    @JsMethod
    public boolean isTimeSinceTNT(int delay)
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > delay)
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }
    
    @JsMethod
    public boolean isTime(long currentTime)
    {
        if (currentTime - this.startTime > this.delay)
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }

    @JsMethod
    public boolean isTimeWithoutReset(long currentTime)
    {
        if (currentTime - this.startTime > this.delay)
        {
            return true;
        }
        return false;
    }
    
    @JsMethod
    public boolean isTimeSince(int delay, long currentTime)
    {
        if (currentTime - this.startTime > this.delay)
        {
            this.startTime = currentTime;
            return true;
        }
        return false;
    }
    
    @JsMethod
    public long getElapsedTNT()
    {
        return System.currentTimeMillis() - this.startTime;
    }

    @JsMethod
    public long getElapsed(long currentTime)
    {
        return currentTime - this.startTime;
    }

    @JsMethod
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

    @JsMethod
    public boolean isElapsedTNT(long time)
    {
        if (this.getElapsedTNT() > time)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
        
    @JsMethod
    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    @JsMethod
    public long getStartTime() 
    {
        return this.startTime;
    }

    @JsMethod
    public void setStartTimeTNT()
    {
        this.startTime = System.currentTimeMillis();
    }
    
    @JsMethod
    public void pause() {
        this.elapsedTimeAtPause = this.getElapsedTNT();
        this.startTime = Long.MAX_VALUE;
    }

    @JsMethod
    public boolean resume() {
        if(this.elapsedTimeAtPause != Long.MIN_VALUE) {
            this.startTime = System.currentTimeMillis() + this.elapsedTimeAtPause;
            this.elapsedTimeAtPause = Long.MIN_VALUE;
            this.setStartTimeTNT();
            return true;
        }
        return false;
    }
    
    @JsMethod
    public String toStringAt(long currentTime)
    {
        long elapsed = this.getElapsed(currentTime);

        if(elapsed > 0)
        {
            final CommonLabels commonLabels = CommonLabels.getInstance();
            final String elapsedAsString = Long.toString(elapsed);
            return new StringMaker().append(commonLabels.START).appendlong(this.startTime).append(commonLabels.CURRENT).appendlong(currentTime).append(commonLabels.ELAPSED).append(elapsedAsString).toString();
        }
        else
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }
    
    @JsMethod
    public String toString()
    {
        long currentTime = System.currentTimeMillis();
        long elapsed = this.getElapsed(currentTime);

        if(elapsed > 0)
        {
            final CommonLabels commonLabels = CommonLabels.getInstance();
            final String elapsedAsString = Long.toString(elapsed);
            return new StringMaker().append(commonLabels.START).appendlong(this.startTime).append(commonLabels.CURRENT).appendlong(currentTime).append(commonLabels.ELAPSED).append(elapsedAsString).toString();
        }
        else
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
    }
    
}
