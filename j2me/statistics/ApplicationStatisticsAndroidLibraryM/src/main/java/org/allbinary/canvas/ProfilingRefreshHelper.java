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
        return instance;
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
        frameProcessingTimeElapsed = this.getTimeDelayHelper().getStartTime();

        bestFrameProcessingTime = Integer.MAX_VALUE;
        // averageFrameProcessingTime = 0;
        worstFrameProcessingTime = 0;
    }

    @Override
    public void nextFrame()
    {
        frameProcessingTimeElapsed = System.currentTimeMillis()
                - frameProcessingTimeElapsed;

        if (firstTime != true)
        {
            if (frameProcessingTimeElapsed > worstFrameProcessingTime)
                worstFrameProcessingTime = (int) frameProcessingTimeElapsed;

            if (frameProcessingTimeElapsed < bestFrameProcessingTime)
                bestFrameProcessingTime = (int) frameProcessingTimeElapsed;
        }
        else
        {
            firstTime = false;
        }

        // averageFrameProcessingTime += frameProcessingTimeElapsed

        // averageFrameProcessingTime = 0;

        super.nextFrame();
    }

    public String toString()
    {
        return new StringMaker().append(super.toString())
            .append(" Worst: ").append(worstFrameProcessingTime)
            .append(" Best: ").append(bestFrameProcessingTime).toString();
    }
}
