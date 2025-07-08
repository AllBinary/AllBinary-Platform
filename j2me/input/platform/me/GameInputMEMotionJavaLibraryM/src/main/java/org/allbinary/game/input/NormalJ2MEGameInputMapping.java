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

import org.allbinary.game.input.mapping.InputToGameKeyMapping;

public class NormalJ2MEGameInputMapping  extends J2MEGameInputMapping
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected InputToGameKeyMapping getDefault()
    {
        //logUtil.put("Use Default GameKey Mappings", this, "addDefault");

        return new NormalJ2MEDefaultGameInputMapping().getInputMapping();
    }

    /*
    public static GameKey getKey(Canvas canvas, int keyCode) throws Exception
    {
        int gameActionKeyCode = canvas.getGameAction(keyCode);
        GameKey gameKey = gameKeyMapping.getInstance(gameActionKeyCode);
        // GameKey gameKey = null;

        if (gameKey == GameKey.NONE)
        {
            gameKey = gameKeyMapping.getInstance(keyCode);
        }

        return gameKey;
    }
    */
}