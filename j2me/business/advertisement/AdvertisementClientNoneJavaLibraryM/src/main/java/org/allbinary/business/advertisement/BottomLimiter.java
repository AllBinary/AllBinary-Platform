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
package org.allbinary.business.advertisement;

import org.allbinary.game.GameAdState;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;

public class BottomLimiter
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BottomLimiter instance = new BottomLimiter();

    public static BottomLimiter getInstance()
    {
        return instance;
    }

    private int bottom;
    private int deltaY;
    private int lower;
    private int higher;

    private BottomLimiter()
    {
    }
        
    public void init(int lower, int higher)
    {
        this.lower = lower;
        this.higher = higher;
    }
    
    public void update()
    {
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        //deltaY = 68;
        
        GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();

        if (gameAdState.isShowingAt())
        {
            // PreLogUtil.put("Y: " + this.getY(), this, GameStrings.getInstance().PROCESS_TICK);
        	
        	//PreLogUtil.put("Ad Showing Bottom", this, GameStrings.getInstance().PROCESS_TICK);
            deltaY = higher;
        } else
        {
        	//PreLogUtil.put("No Ad Bottom", this, GameStrings.getInstance().PROCESS_TICK);
            deltaY = lower;
        }

        bottom = displayInfo.getLastHeight() - deltaY;
    }
    
    public int getDeltaY()
    {
        return deltaY;
    }
    
    public int getBottom()
    {
        return bottom;
    }

}
