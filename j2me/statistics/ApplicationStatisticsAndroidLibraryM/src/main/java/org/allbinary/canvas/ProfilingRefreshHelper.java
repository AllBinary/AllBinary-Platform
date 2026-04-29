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

import android.view.View;
import org.allbinary.logic.string.StringMaker;

public class ProfilingRefreshHelper extends AndroidGameStatistics
{
    private static final ProfilingRefreshHelper instance = new ProfilingRefreshHelper();
    
    public static AndroidGameStatistics getInstance()
    {
        return ProfilingRefreshHelper.instance;
    }    
    
    private boolean firstTime = true;
    private int bestFrameProcessingTime;
    // private long averageFrameProcessingTime;
    private int worstFrameProcessingTime;
    private long frameProcessingTimeElapsed;

    private ProfilingRefreshHelper()
    {
        
    }
    
    @Override
    public void init(View view)
    {
        super.init(view);
        this.frameProcessingTimeElapsed = this.getTimeDelayHelper().getStartTime();

        this.bestFrameProcessingTime = Integer.MAX_VALUE;
        // averageFrameProcessingTime = 0;
        this.worstFrameProcessingTime = 0;
    }

    @Override
    public void nextFrame()
    {
        this.frameProcessingTimeElapsed = System.currentTimeMillis()
                - this.frameProcessingTimeElapsed;

        if (this.firstTime != true)
        {
            if (this.frameProcessingTimeElapsed > this.worstFrameProcessingTime)
                this.worstFrameProcessingTime = (int) this.frameProcessingTimeElapsed;

            if (this.frameProcessingTimeElapsed < this.bestFrameProcessingTime)
                this.bestFrameProcessingTime = (int) this.frameProcessingTimeElapsed;
        }
        else
        {
            this.firstTime = false;
        }

        // averageFrameProcessingTime += frameProcessingTimeElapsed

        // averageFrameProcessingTime = 0;

        super.nextFrame();
    }

    public String toString()
    {
        return new StringMaker().append(super.toString())
            .append(" Worst: ").appendint(this.worstFrameProcessingTime)
            .append(" Best: ").appendint(this.bestFrameProcessingTime).toString();
    }
}
