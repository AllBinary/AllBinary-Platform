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
package org.allbinary.game.displayable.canvas;

import org.allbinary.graphics.displayable.DisplayInfoSingleton;

public class GameRunnable implements Runnable
{

    public final int WAIT = 240;
    public final int FAST = 60;
    public int wait = WAIT;
    
    public GameRunnable() {
        
    }
    
    public void run()
    {
        DisplayInfoSingleton.getInstance().process();
    }
    
    public void processLoopSleep()
    throws Exception
    {
        Thread.sleep(wait);
    }
}
