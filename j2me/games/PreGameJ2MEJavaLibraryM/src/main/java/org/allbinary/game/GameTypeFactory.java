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

import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class GameTypeFactory
{
    private static final GameTypeFactory instance = new GameTypeFactory();
    
    @JsMethod
    public static GameTypeFactory getInstance()
    {
        return GameTypeFactory.instance;
    }

    @JsProperty
    public GameType[] NULL_GAME_TYPE_ARRAY = new GameType[0];

    @JsProperty
    public GameType NONE = new GameType(StringUtil.getInstance().NULL_STRING);
    @JsProperty
    public GameType SINGLE_PLAYER = new GameType("Single Player");
    @JsProperty
    public GameType MULTI_PLAYER = new GameType("Multi Player");
    @JsProperty
    public GameType BOT = new GameType("Artificial Player");
}
