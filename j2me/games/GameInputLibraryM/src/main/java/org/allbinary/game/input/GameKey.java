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

import org.allbinary.logic.basic.string.StringMaker;


/*
 * These are imaginary inputs outside of J2ME Canvas keys.
 */
public class GameKey extends Input
{
    protected GameKey(int key, String name)
    {
        super(key, name);
    }

    public String toString()
    {
        return new StringMaker().append("GameKey ").append(super.toString()).toString();
    }
}
