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
package org.allbinary.game.input.mapping;

import jsinterop.annotations.JsType;

import org.allbinary.game.input.GameKey;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class GameInputMapping
{
    private String name = StringUtil.getInstance().EMPTY_STRING;
    private GameKey gameKey = GameKey.NULL_GAME_KEY;
    
    @JsConstructor
    public GameInputMapping(String name, GameKey gameKey)
    {
        this.setName(name);
        this.setGameKey(gameKey);
    }

    @JsMethod
    private void setGameKey(GameKey gameKey)
    {
        this.gameKey = gameKey;
    }

    @JsMethod
    public GameKey getGameKey()
    {
        return this.gameKey;
    }

    @JsMethod
    private void setName(String name)
    {
        this.name = name;
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }
}
