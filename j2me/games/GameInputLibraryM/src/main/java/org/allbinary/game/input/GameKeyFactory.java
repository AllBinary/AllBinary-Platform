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

import org.allbinary.string.CommonPhoneStrings;

public class GameKeyFactory
{
    private static final GameKeyFactory instance = new GameKeyFactory();
    
    public static GameKeyFactory getInstance()
    {
        return instance;
    }

    public final GameKey NONE = new GameKey(InputFactory.getInstance().MAX - 1, "None");

    public final GameKey UP = new GameKey(Canvas.UP, CommonPhoneStrings.getInstance().UP);
    public final GameKey DOWN = new GameKey(Canvas.DOWN, CommonPhoneStrings.getInstance().DOWN);
    public final GameKey LEFT = new GameKey(Canvas.LEFT, CommonPhoneStrings.getInstance().LEFT);
    public final GameKey RIGHT = new GameKey(Canvas.RIGHT, CommonPhoneStrings.getInstance().RIGHT);
    
    public final GameKey KEY_POUND = new GameKey(Canvas.KEY_POUND, "POUND");
    public final GameKey KEY_STAR = new GameKey(Canvas.KEY_STAR, "STAR");
    public final GameKey KEY_NUM0 = new GameKey(Canvas.KEY_NUM0, CommonPhoneStrings.getInstance().ZERO);
    public final GameKey KEY_NUM1 = new GameKey(Canvas.KEY_NUM1, CommonPhoneStrings.getInstance().ONE);
    public final GameKey KEY_NUM2 = new GameKey(Canvas.KEY_NUM2, CommonPhoneStrings.getInstance().TWO);
    public final GameKey KEY_NUM3 = new GameKey(Canvas.KEY_NUM3, CommonPhoneStrings.getInstance().THREE);
    public final GameKey KEY_NUM4 = new GameKey(Canvas.KEY_NUM4, CommonPhoneStrings.getInstance().FOUR);
    public final GameKey KEY_NUM5 = new GameKey(Canvas.KEY_NUM5, CommonPhoneStrings.getInstance().FIVE);
    public final GameKey KEY_NUM6 = new GameKey(Canvas.KEY_NUM6, CommonPhoneStrings.getInstance().SIX);
    public final GameKey KEY_NUM7 = new GameKey(Canvas.KEY_NUM7, CommonPhoneStrings.getInstance().SEVEN);
    public final GameKey KEY_NUM8 = new GameKey(Canvas.KEY_NUM8, CommonPhoneStrings.getInstance().EIGHT);
    public final GameKey KEY_NUM9 = new GameKey(Canvas.KEY_NUM9, CommonPhoneStrings.getInstance().NINE);
    public final GameKey GAME_A = new GameKey(Canvas.GAME_A, "Game A");
    public final GameKey GAME_B = new GameKey(Canvas.GAME_B, "Game B");
    public final GameKey GAME_C = new GameKey(Canvas.GAME_C, "Game C");
    public final GameKey GAME_D = new GameKey(Canvas.GAME_D, "Game D");
    public final GameKey FIRE = new GameKey(Canvas.FIRE, CommonPhoneStrings.getInstance().FIRE);

    /*
    public GameKey KEYBOARD_Y = GameKey.NONE;
    public GameKey KEYBOARD_U = GameKey.NONE;
    public GameKey KEYBOARD_I = GameKey.NONE;

    public GameKey KEYBOARD_H = GameKey.NONE;
    public GameKey KEYBOARD_J = GameKey.NONE;
    public GameKey KEYBOARD_K = GameKey.NONE;

    public GameKey KEYBOARD_N = GameKey.NONE;
    public GameKey KEYBOARD_M = GameKey.NONE;
    public GameKey KEYBOARD_LESS = GameKey.NONE;

    public GameKey KEYBOARD_SPACE = GameKey.NONE;
    public GameKey KEYBOARD_INSERT = GameKey.NONE;
    public GameKey KEYBOARD_DELETE = GameKey.NONE;

    public GameKey QUICK_STAR = GameKey.KEYBOARD_SPACE;
    public GameKey QUICK_POUND = GameKey.KEYBOARD_DELETE;

    // KeyEvent.VK_F3
    public GameKey LEVEL_DOWN = GameKey.NONE;
    public GameKey LEVEL_UP = GameKey.NONE; 
    */

    public GameKey LEVEL_DOWN = new GameKey(Canvas.KEY_NUM9 + 1, "Cheat Level Up");
    public GameKey LEVEL_UP = new GameKey(Canvas.KEY_NUM9 + 2, "Cheat Level Down");
}
