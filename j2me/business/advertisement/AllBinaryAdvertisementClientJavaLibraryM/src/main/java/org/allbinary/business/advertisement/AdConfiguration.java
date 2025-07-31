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

import org.allbinary.direction.Direction;
import org.allbinary.game.state.GameState;

public class AdConfiguration
{
    public final String INNERACTIVE;
    public final String LEADBOLT;
    public final String ADMOB;
    
    public AdConfiguration(Object[] advertIdArray)
    {
        this.INNERACTIVE = (String) advertIdArray[0];
        this.ADMOB = (String) advertIdArray[1];
        this.LEADBOLT = (String) advertIdArray[2];
    }
    
    public Direction[] getValidAdSpots()
    {
        return new Direction[0];
    }
    
    //Special in gamestate based processing
    public void process(GameState gameState)
    {
    }
    
    //Special Demo processing
    public void process(int state)
    {
    }

    public void setShowAds()
    {
    }
    
    public void setShowAds(boolean showAds)
    {
    }

    public boolean isShowAds()
    {
        return true;
    }
}
