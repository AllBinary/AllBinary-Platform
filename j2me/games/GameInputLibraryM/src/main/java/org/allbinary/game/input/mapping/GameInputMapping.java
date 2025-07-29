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

import org.allbinary.game.input.GameKey;
import org.allbinary.logic.string.StringUtil;

public class GameInputMapping
{
    private String name = StringUtil.getInstance().EMPTY_STRING;
    private GameKey gameKey = GameKey.NULL_GAME_KEY;
    
    public GameInputMapping(String name, GameKey gameKey)
    {
        this.setName(name);
        this.setGameKey(gameKey);
    }

    private void setGameKey(GameKey gameKey)
    {
        this.gameKey = gameKey;
    }

    public GameKey getGameKey()
    {
        return gameKey;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
