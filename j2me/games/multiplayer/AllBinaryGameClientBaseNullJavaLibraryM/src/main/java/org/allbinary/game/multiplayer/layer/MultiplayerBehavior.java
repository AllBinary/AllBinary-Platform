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
package org.allbinary.game.multiplayer.layer;

import org.allbinary.logic.string.StringUtil;

public class MultiplayerBehavior
{
    public static final MultiplayerBehavior NULL_MULTIPLAYER_BEHAVIOR = new MultiplayerBehavior(StringUtil.getInstance().EMPTY_STRING);

    protected MultiplayerBehavior(String username)
    {
    }
    
    public void startTick()
    {
        
    }
    
    public void endTick()
    {

    }
    
    public long getApproximateElapsed()
    {
        return 0;
    }    
}
