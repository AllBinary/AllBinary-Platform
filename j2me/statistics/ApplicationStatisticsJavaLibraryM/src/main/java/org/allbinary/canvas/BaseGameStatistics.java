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
package org.allbinary.canvas;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.ScaleFactorFactory;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class BaseGameStatistics
{
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(Integer.MAX_VALUE);
    private final TimeDelayHelper updateDelayHelper = new TimeDelayHelper(2000);

    private long totalRefreshes;
    private long totalFrames;

    protected final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();

    // private PrimitiveLongUtil primitiveLongUtil;

    public BaseGameStatistics()
    {
        /*
         * try { this.primitiveLongUtil = new PrimitiveLongUtil(1000000000); }
         * catch(Exception e) { LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION,
         * this, CommonStrings.getInstance().CONSTRUCTOR)); }
         */
    }

    public void init()
    {
        this.timeDelayHelper.setStartTime();
        this.totalRefreshes = 0;
        this.totalFrames = 0;
    }

    // never really implemented
    public void process()
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, "Refresh", CommonStrings.getInstance().PROCESS));
        // if (timeDelayHelper.isTime())
        // {

        // }
    }

    public void nextFrame()
    {
        this.totalFrames++;
    }

    public void nextRefresh()
    {
        this.totalRefreshes++;
    }

    private final int DEFAULT_SCALE_FACTOR = 
            ScaleFactorFactory.getInstance().DEFAULT_SCALE_FACTOR;
    
    public short getRefreshRate()
    {
        long elapsed = this.timeDelayHelper.getElapsed(this.gameTickTimeDelayHelper.startTime);
        
        if(elapsed > 1)
        {
            return (short) (
                    this.totalRefreshes / (elapsed >> DEFAULT_SCALE_FACTOR)
                    );            
        }
        else
        {
            return 0;
        }
    }
    
    // Helper method for deciding if I should buffer
    /*
     * public static boolean shouldBuffer() { if ((totalRefreshes / 10 * 9) <
     * totalFrames) { return false; } else { return true; } }
     */

    protected TimeDelayHelper getTimeDelayHelper()
    {
        return this.timeDelayHelper;
    }

    protected final String NOT = "Not enough Time to Calculate";
    
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
    private final String[] STRING_ARRAY =
    { " Total Time: ", EMPTY_STRING, " Total Frames: ", EMPTY_STRING,
            " Frames/10 Sec: ", EMPTY_STRING, " Total Paints: ",
            EMPTY_STRING, " Paints/10 Sec: ", EMPTY_STRING };

    private final char SPACE_CHAR = ' ';
    private final char PLUS_CHAR = '+';
    private final char X_CHAR = 'X';

    private final char[][] CHAR_ARRAY =
    {
    { 'F', 'r', 'a', 'm', 'e', 's', '/', '1', '0', ' ', 'S', 'e', 'c', ':' },
    { 'X', 'X' },
    { 'P', 'a', 'i', 'n', 't', 's', '/', '1', '0', ' ', 'S', 'e', 'c', ':' },
    { 'X', 'X' } };

    private final PrimitiveLongSingleton primitiveLongSingleton = PrimitiveLongSingleton.getInstance();
    
    public char[][] toCharArray()
    {
        long totalTime = this.timeDelayHelper.getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime >> DEFAULT_SCALE_FACTOR);

        if (totalTime > 0 && updateDelayHelper.isTime(this.gameTickTimeDelayHelper.startTime))
        {
            int framesPerSec = (int) (this.totalFrames / totalTime);

            if (framesPerSec < 10)
            {
                CHAR_ARRAY[1][0] = SPACE_CHAR;
                CHAR_ARRAY[1][1] = primitiveLongSingleton.NUMBER_CHAR_ARRAY[framesPerSec];
            } else if (framesPerSec < 100)
            {
                int tens = framesPerSec / 10;
                int removeTens = tens * 10;
                CHAR_ARRAY[1][0] = primitiveLongSingleton.NUMBER_CHAR_ARRAY[tens];
                CHAR_ARRAY[1][1] = primitiveLongSingleton.NUMBER_CHAR_ARRAY[framesPerSec - removeTens];                
            } else
            {
                CHAR_ARRAY[1][0] = PLUS_CHAR;
                CHAR_ARRAY[1][1] = PLUS_CHAR;
            }

            int refreshesPerSec = (int) (this.totalRefreshes / totalTime);

            if (refreshesPerSec < 10)
            {
                CHAR_ARRAY[3][0] = X_CHAR;
                CHAR_ARRAY[3][1] = primitiveLongSingleton.NUMBER_CHAR_ARRAY[refreshesPerSec];
            } else if (refreshesPerSec < 100)
            {
                int tens = refreshesPerSec / 10;
                int removeTens = tens * 10;
                CHAR_ARRAY[3][0] = primitiveLongSingleton.NUMBER_CHAR_ARRAY[refreshesPerSec / 10];
                CHAR_ARRAY[3][1] = primitiveLongSingleton.NUMBER_CHAR_ARRAY[refreshesPerSec - removeTens];
            } else
            {
                CHAR_ARRAY[3][0] = PLUS_CHAR;
                CHAR_ARRAY[3][1] = PLUS_CHAR;
            }
        }
        return CHAR_ARRAY;
    }

    public String[] toStringArray()
    {
        long totalTime = this.timeDelayHelper.getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 10000);

        if (totalTime > 0)
        {
            STRING_ARRAY[1] = Long.toString(totalTime);
            STRING_ARRAY[3] = Long.toString(this.totalFrames);
            STRING_ARRAY[5] = Long.toString(this.totalFrames / totalTime);
            STRING_ARRAY[7] = Long.toString(this.totalRefreshes);
            STRING_ARRAY[9] = Long.toString(this.totalRefreshes / totalTime);
        } else
        {
            String string = EMPTY_STRING;
            STRING_ARRAY[1] = string;
            STRING_ARRAY[3] = string;
            STRING_ARRAY[5] = string;
            STRING_ARRAY[7] = string;
            STRING_ARRAY[9] = string;
        }

        return STRING_ARRAY;
    }

    public String toString(long totalTime)
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(STRING_ARRAY[0]);
        stringBuffer.append(totalTime);
        stringBuffer.append(STRING_ARRAY[2]);
        stringBuffer.append(this.totalFrames);
        stringBuffer.append(STRING_ARRAY[4]);
        stringBuffer.append(this.totalFrames / totalTime);
        
        if(this.totalRefreshes > 0)
        {
            stringBuffer.append(STRING_ARRAY[6]);
            stringBuffer.append(this.totalRefreshes);
            stringBuffer.append(STRING_ARRAY[8]);
            stringBuffer.append(this.totalRefreshes / totalTime);
            stringBuffer.append(CommonSeps.getInstance().NEW_LINE);
        }

        return stringBuffer.toString();
    }
    
    public String toString()
    {
        long totalTime = this.timeDelayHelper.getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 1000);

        if (totalTime > 0)
        {
        	return this.toString(totalTime);
        } else
        {
            return NOT;
        }
    }
}
