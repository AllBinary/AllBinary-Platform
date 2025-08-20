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

import javax.microedition.lcdui.NullCanvas;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.util.BasicArrayListUtil;

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
        super(BasicArrayListUtil.getInstance().getImmutableInstance(), -1, NullCanvas.NULL_CANVAS);
    }

    @Override
    public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
    }

    @Override
    public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
    }

    @Override
    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
    }
}
