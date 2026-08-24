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
package org.allbinary.game;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class GameStrings
{
    private static final GameStrings instance = new GameStrings();

    @JsMethod
    public static GameStrings getInstance()
    {
        return GameStrings.instance;
    }
    
    @JsProperty
    public final String PROCESS_TICK = "processTick";

    //
    @JsProperty
    public final String ON_GAME_INITIALIZED = "onGameInitialized";
    //
    @JsProperty
    public final String UPDATESURFACE = "updateSurface";
    //
    @JsProperty
    public final String ON_SET_DISPLAYABLE = "onSetDisplayable";
    
    @JsProperty
    public final String UNPAUSE = "unPause";
    @JsProperty
    public final String TOGGLE_MENU = "toggleMenu";
    @JsProperty
    public final String SET_GAME_OVER = "setGameOver";
    @JsProperty
    public final String GAME_STATE = "Game State: ";
    @JsProperty
    public final String SET_GAME_STATE = "setGameState";
    @JsProperty
    public final String STOP_GAME_CANVAS_RUNNABLE_INTERFACE = "stopGameCanvasRunnableInterface";

}
