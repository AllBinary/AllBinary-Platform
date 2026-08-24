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
package org.allbinary.game.input;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class GameInputStrings
{
    private static final GameInputStrings instance = new GameInputStrings();

    @JsMethod
    public static GameInputStrings getInstance()
    {
        return GameInputStrings.instance;
    }
    
    @JsProperty
    public final String PROCESS_INPUT = "processInput";
    
    @JsProperty
    public final String KEY_PRESSED = "keyPressed";
    @JsProperty
    public final String KEY_RELEASED = "keyReleased";
    @JsProperty
    public final String KEY_REPEATED = "keyRepeated";
    
    @JsProperty
    public final String ON_PRESS_GAME_KEY = "onPressGameKeyEvent";
    @JsProperty
    public final String ON_DOWN_GAME_KEY = "onDownGameKeyEvent";
    @JsProperty
    public final String ON_UP_GAME_KEY = "onUpGameKeyEvent";

    @JsProperty
    public final String NO_KEY = "Key Code Not Mapped For Game: ";
    @JsProperty
    public final String ADD_KEY_EVENT = "addGameKeyEvent";
    @JsProperty
    public final String REMOVE_KEY_EVENT = "removeGameKeyEvent";
    
    @JsProperty
    public final String STRAFE_LEFT = "strafeLeft";
    @JsProperty
    public final String STRAFE_RIGHT = "strafeRight";
    
    @JsProperty
    public final String PROCESS_MOTION_INPUT = "processMotionInput";
    @JsProperty
    public final String UDPATE_CURRENT_TOUCH_INPUT_FACTORY = "updateCurrentTouchInputFactory";
    
    @JsProperty
    public final String ENABLE_PLAYER_GAME_INPUTS = "Enabling PlayerGameInputs: ";
    @JsProperty
    public final String ENABLE_PLAYER_GAME_INPUT = "Enabling PlayerGameInput: ";
    
}
