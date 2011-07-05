/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
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
