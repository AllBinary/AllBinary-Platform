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
package allbinary.game.displayable.canvas;

import allbinary.game.input.event.GameKeyEvent;
import allbinary.input.motion.gesture.observer.MotionGestureEvent;

public class NoMenuInputProcessor extends BasicMenuInputProcessor
{
    private static final NoMenuInputProcessor SINGLETON =
        new NoMenuInputProcessor();

    public static final NoMenuInputProcessor getInstance()
    {
        return SINGLETON;
    }

    private NoMenuInputProcessor()
    {
        super(null, null);
    }

    public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
    }

    public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
    }

    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
    }
}
