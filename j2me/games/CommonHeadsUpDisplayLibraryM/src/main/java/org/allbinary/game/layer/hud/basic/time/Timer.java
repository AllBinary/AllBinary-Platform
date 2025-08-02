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
package org.allbinary.game.layer.hud.basic.time;

import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.PrimitiveLongUtil;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;

public class Timer
{
    private long startTime = -1;
    private long hundredthTime;
    private long maxTime;
    private long modifier;

    // private String hundrethsString;
    // PrimitiveLongUtil.NUMBER_STRING_ARRAY[0];

    private char[] hundrethsString = PrimitiveLongSingleton.getInstance().ZERO;
    private int totalDigits = 1;

    private PrimitiveLongUtil primitiveLongUtil;
 
    private final 
    char[] MAX = { 'L', 'O', 'L' };
    //String MAX = "LOL";

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();

    public Timer(int maxTime)
    {
        this.primitiveLongUtil = new PrimitiveLongUtil(maxTime);
        this.maxTime = (long) maxTime;
    }

    public void start()
    {
        this.hundredthTime = 0;
        this.startTime = this.gameTickTimeDelayHelper.startTime;
    }

    public long get()
    {
        return hundredthTime;
    }

    /*
     * public void add(long score) { this.set(this.time + score); }
     */

    public void update()
    {
        long lastLowerTime = this.hundredthTime;
        long currentTime = ((this.modifier + this.gameTickTimeDelayHelper.startTime) - this.startTime);

        if (currentTime < 0)
        {
            currentTime = 0;
        }

        this.hundredthTime = currentTime / 100;

        if (this.hundredthTime >= this.maxTime)
        {
            if (this.startTime > 0)
            {
                this.hundrethsString = MAX;
            }
        }
        else if (lastLowerTime != this.hundredthTime)
        {
            // this.hundrethsString = primitiveLongUtil.getString((int)
            // this.get());
            this.hundrethsString = primitiveLongUtil.getCharArray((int) this.hundredthTime);
            this.totalDigits = this.primitiveLongUtil.getCurrentTotalDigits();
        }
    }

    public void reduce(int value)
    {
        this.modifier -= value;
    }

    public void add(int value)
    {
        this.modifier += value;
    }

    /*
     * public String toString() { return this.hundrethsString; }
     */

    public char[] getTimeChars()
    {
        return this.hundrethsString;
    }

    public int getCurrentTotalDigits()
    {
        return totalDigits;
    }
}
