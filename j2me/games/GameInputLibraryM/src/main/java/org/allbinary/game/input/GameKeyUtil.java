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

import javax.microedition.lcdui.Canvas;

/**
 *
 * @author user
 */
public class GameKeyUtil
{
    private static final GameKeyUtil instance = new GameKeyUtil();
    
    /*
    public static boolean isNonDirectionKey(int key)
    {
        if (key == Canvas.KEY_NUM0 ||
            key == Canvas.KEY_NUM1 ||
            key == Canvas.KEY_NUM2 ||
            key == Canvas.KEY_NUM3 ||
            key == Canvas.KEY_NUM4 ||
            key == Canvas.KEY_NUM5 ||
            key == Canvas.KEY_NUM6 ||
            key == Canvas.KEY_NUM7 ||
            key == Canvas.KEY_NUM8 ||
            key == Canvas.KEY_NUM9 ||
            key == Canvas.KEY_POUND ||
            key == Canvas.KEY_STAR ||
            key == Canvas.FIRE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    */

    public boolean isDirectionKey(int key)
    {
        if (key == Canvas.UP ||
            key == Canvas.DOWN ||
            key == Canvas.LEFT ||
            key == Canvas.RIGHT)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static GameKeyUtil getInstance()
    {
        return instance;
    }
}
