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

import jsinterop.annotations.JsType;

import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class GameKeyCompleteMotionGestureInputEvent extends
        CompleteMotionGestureInputEvent implements GameKeyEventSourceInterface
{

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    private final int SOURCE_ID = this.gameKeyEventFactory.MOTION_GESTURE_SOURCE_ID;
    
    private GameKey gameKey = GameKey.NULL_GAME_KEY;
    private GameKeyEvent gameKeyEvent = GameKeyEvent.NONE;

    private InputToGameKeyMapping inputToGameKeyMapping;

    @JsConstructor
    public GameKeyCompleteMotionGestureInputEvent(String name,
            MotionGestureInput motionGestureInput, InputToGameKeyMapping inputToGameKeyMapping)
    {
        super(name, motionGestureInput);

        this.inputToGameKeyMapping = inputToGameKeyMapping;

        GameKeyCompleteMotionGestureInputEventFactory.getInstance().add(this);
        
        this.update();
        //this.logUtil.putF(name + " mapped to gameKey: " + this.getGameKey(), this, this.commonStrings.CONSTRUCTOR);
    }

    @Override
    @JsMethod
    public int getSourceId()
    {
        return this.SOURCE_ID;
    }

    @JsMethod
    public void update()
    {
        try
        {
            this.setGameKey(this.inputToGameKeyMapping.getInstance(
                    this.getMotionGestureInput().getId()));

            this.setGameKeyEvent(this.gameKeyEventFactory.getInstanceForInput(this, this.getGameKey()));
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e);
        }
    }

    @JsMethod
    protected void setGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        this.gameKeyEvent = gameKeyEvent;
    }

    @JsMethod
    public GameKeyEvent getGameKeyEvent()
    {
        return this.gameKeyEvent;
    }

    @JsMethod
    protected void setGameKey(GameKey gameKey)
    {
        this.gameKey = gameKey;
    }

    @JsMethod
    public GameKey getGameKey()
    {
        return this.gameKey;
    }
}
