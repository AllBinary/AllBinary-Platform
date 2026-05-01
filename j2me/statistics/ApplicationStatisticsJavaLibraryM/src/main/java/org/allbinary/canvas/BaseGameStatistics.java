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

import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.ScaleFactorFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class BaseGameStatistics
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

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
         * catch(Exception e) { this.logUtil.putF(commonStrings.EXCEPTION, * this, commonStrings.CONSTRUCTOR); }
         */
    }

    public void init()
    {
        this.timeDelayHelper.setStartTimeTNT();
        this.totalRefreshes = 0;
        this.totalFrames = 0;
    }

    public void add(String string) {
        
    }
        
    // never really implemented
    public void process()
    {
        // this.logUtil.putF(commonStrings.START, "Refresh", commonStrings.PROCESS);
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
            final short time = (short) (this.totalRefreshes / (elapsed >> this.DEFAULT_SCALE_FACTOR));
            return time;
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
    { " Total Time: ", this.EMPTY_STRING, " Total Frames: ", this.EMPTY_STRING,
            " Frames/10 Sec: ", this.EMPTY_STRING, " Total Paints: ",
            this.EMPTY_STRING, " Paints/10 Sec: ", this.EMPTY_STRING };

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
        totalTime = (totalTime >> this.DEFAULT_SCALE_FACTOR);

        if (totalTime > 0 && this.updateDelayHelper.isTime(this.gameTickTimeDelayHelper.startTime))
        {
            int framesPerSec = (int) (this.totalFrames / totalTime);

            if (framesPerSec < 10)
            {
                this.CHAR_ARRAY[1][0] = this.SPACE_CHAR;
                this.CHAR_ARRAY[1][1] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[framesPerSec];
            } else if (framesPerSec < 100)
            {
                int tens = framesPerSec / 10;
                int removeTens = tens * 10;
                this.CHAR_ARRAY[1][0] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[tens];
                this.CHAR_ARRAY[1][1] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[framesPerSec - removeTens];                
            } else
            {
                this.CHAR_ARRAY[1][0] = this.PLUS_CHAR;
                this.CHAR_ARRAY[1][1] = this.PLUS_CHAR;
            }

            int refreshesPerSec = (int) (this.totalRefreshes / totalTime);

            if (refreshesPerSec < 10)
            {
                this.CHAR_ARRAY[3][0] = this.X_CHAR;
                this.CHAR_ARRAY[3][1] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[refreshesPerSec];
            } else if (refreshesPerSec < 100)
            {
                int tens = refreshesPerSec / 10;
                int removeTens = tens * 10;
                this.CHAR_ARRAY[3][0] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[refreshesPerSec / 10];
                this.CHAR_ARRAY[3][1] = this.primitiveLongSingleton.NUMBER_CHAR_ARRAY[refreshesPerSec - removeTens];
            } else
            {
                this.CHAR_ARRAY[3][0] = this.PLUS_CHAR;
                this.CHAR_ARRAY[3][1] = this.PLUS_CHAR;
            }
        }
        return this.CHAR_ARRAY;
    }

    public String[] toStringArray()
    {
        long totalTime = this.timeDelayHelper.getElapsed(this.gameTickTimeDelayHelper.startTime);
        totalTime = (totalTime / 10000);

        if (totalTime > 0)
        {
            this.STRING_ARRAY[1] = Long.toString(totalTime);
            this.STRING_ARRAY[3] = Long.toString(this.totalFrames);
            this.STRING_ARRAY[5] = Long.toString(this.totalFrames / totalTime);
            this.STRING_ARRAY[7] = Long.toString(this.totalRefreshes);
            this.STRING_ARRAY[9] = Long.toString(this.totalRefreshes / totalTime);
        } else
        {
            String string = this.EMPTY_STRING;
            this.STRING_ARRAY[1] = string;
            this.STRING_ARRAY[3] = string;
            this.STRING_ARRAY[5] = string;
            this.STRING_ARRAY[7] = string;
            this.STRING_ARRAY[9] = string;
        }

        return this.STRING_ARRAY;
    }

    public String toStringAt(long totalTime)
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.STRING_ARRAY[0]);
        stringBuffer.appendlong(totalTime);
        stringBuffer.append(this.STRING_ARRAY[2]);
        stringBuffer.appendlong(this.totalFrames);
        stringBuffer.append(this.STRING_ARRAY[4]);
        stringBuffer.appendlong(this.totalFrames / totalTime);
        
        if(this.totalRefreshes > 0)
        {
            stringBuffer.append(this.STRING_ARRAY[6]);
            stringBuffer.appendlong(this.totalRefreshes);
            stringBuffer.append(this.STRING_ARRAY[8]);
            stringBuffer.appendlong(this.totalRefreshes / totalTime);
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
        	return this.toStringAt(totalTime);
        } else
        {
            return this.NOT;
        }
    }
}
