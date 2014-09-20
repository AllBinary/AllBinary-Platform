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

public class GameRunnable implements Runnable
{
    private static final GameRunnable instance = new GameRunnable();

    public static GameRunnable getInstance()
    {
        return instance;
    }

    public void run()
    {
    }
    
    public void processLoopSleep()
    throws Exception
    {
        Thread.sleep(120);
    }
}
