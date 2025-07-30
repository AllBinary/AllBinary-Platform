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
package org.allbinary.game.input.motion.action;

import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.input.motion.gesture.MotionGestureInput;

public class GameKeyCompleteMotionGestureInputEvent extends
        CompleteMotionGestureInputEvent implements GameKeyEventSourceInterface
{
    private GameKey gameKey = GameKey.NULL_GAME_KEY;
    private GameKeyEvent gameKeyEvent = GameKeyEvent.NONE;

    public GameKeyCompleteMotionGestureInputEvent(String name,
            MotionGestureInput motionGestureInput, InputToGameKeyMapping inputToGameKeyMapping)
    {
        super(name, motionGestureInput);
    }

    @Override
    public int getSourceId()
    {
        return 0;
    }

    public void update()
    {
    }

    protected void setGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        this.gameKeyEvent = gameKeyEvent;
    }

    public GameKeyEvent getGameKeyEvent()
    {
        return gameKeyEvent;
    }

    protected void setGameKey(GameKey gameKey)
    {
        this.gameKey = gameKey;
    }

    public GameKey getGameKey()
    {
        return gameKey;
    }
}
