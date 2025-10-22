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
package org.allbinary.game.layer.hud;

import org.allbinary.logic.string.StringMaker;

public class LapInfo
{
    public static LapInfo NULL_LAP_INFO = new LapInfo(-1);

    private int numberOfLaps;
    private int totalLaps;

    public LapInfo(int totalLaps)
    {
        this.totalLaps = totalLaps;
        this.numberOfLaps = 1;
    }

    public void add()
    {
        this.numberOfLaps++;
    }

    public int getCurrentLap()
    {
        return this.numberOfLaps;
    }

    public int getTotalLaps()
    {
        return this.totalLaps;
    }

    public boolean isDone()
    {
        if (this.numberOfLaps >= this.totalLaps)
        {
            return true;
        }
        return false;
    }

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("isDone: ");
        stringBuffer.append(this.isDone());
        stringBuffer.append(" CurrentLap: ");
        stringBuffer.append(this.getCurrentLap());
        stringBuffer.append(" Total Laps: ");
        stringBuffer.append(this.getTotalLaps());

        return stringBuffer.toString();
    }
}
