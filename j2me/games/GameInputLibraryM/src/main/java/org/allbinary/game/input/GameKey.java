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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;


/*
 * These are imaginary inputs outside of J2ME Canvas keys.
 */
public class GameKey extends Input
{
    public static final GameKey NULL_GAME_KEY = new GameKey(-1, StringUtil.getInstance().NULL_STRING);
    
    GameKey(final int key, final String name)
    {
        super(key, name);
    }

    public String toString()
    {
        return new StringMaker().append("GameKey ").append(super.toString()).toString();
    }
}
