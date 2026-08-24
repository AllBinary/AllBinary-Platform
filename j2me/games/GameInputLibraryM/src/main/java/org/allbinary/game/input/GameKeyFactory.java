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

import javax.microedition.lcdui.Canvas;
import org.allbinary.logic.NullUtil;

import org.allbinary.string.CommonPhoneStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class GameKeyFactory
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    @JsMethod
    public static GameKeyFactory getInstance()
    {
        if(GameKeyFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            GameKeyFactory.instance = new GameKeyFactory();
        }
        
        return (GameKeyFactory) GameKeyFactory.instance;
    }

    @JsProperty
    public final GameKey NONE = new GameKey(InputFactory.getInstance().MAX - 1, "None");

    @JsProperty
    public final GameKey UP = new GameKey(Canvas.UP, CommonPhoneStrings.getInstance().UP);
    @JsProperty
    public final GameKey DOWN = new GameKey(Canvas.DOWN, CommonPhoneStrings.getInstance().DOWN);
    @JsProperty
    public final GameKey LEFT = new GameKey(Canvas.LEFT, CommonPhoneStrings.getInstance().LEFT);
    @JsProperty
    public final GameKey RIGHT = new GameKey(Canvas.RIGHT, CommonPhoneStrings.getInstance().RIGHT);
    
    @JsProperty
    public final GameKey KEY_POUND = new GameKey(Canvas.KEY_POUND, "POUND");
    @JsProperty
    public final GameKey KEY_STAR = new GameKey(Canvas.KEY_STAR, "STAR");
    @JsProperty
    public final GameKey KEY_NUM0 = new GameKey(Canvas.KEY_NUM0, CommonPhoneStrings.getInstance().ZERO);
    @JsProperty
    public final GameKey KEY_NUM1 = new GameKey(Canvas.KEY_NUM1, CommonPhoneStrings.getInstance().ONE);
    @JsProperty
    public final GameKey KEY_NUM2 = new GameKey(Canvas.KEY_NUM2, CommonPhoneStrings.getInstance().TWO);
    @JsProperty
    public final GameKey KEY_NUM3 = new GameKey(Canvas.KEY_NUM3, CommonPhoneStrings.getInstance().THREE);
    @JsProperty
    public final GameKey KEY_NUM4 = new GameKey(Canvas.KEY_NUM4, CommonPhoneStrings.getInstance().FOUR);
    @JsProperty
    public final GameKey KEY_NUM5 = new GameKey(Canvas.KEY_NUM5, CommonPhoneStrings.getInstance().FIVE);
    @JsProperty
    public final GameKey KEY_NUM6 = new GameKey(Canvas.KEY_NUM6, CommonPhoneStrings.getInstance().SIX);
    @JsProperty
    public final GameKey KEY_NUM7 = new GameKey(Canvas.KEY_NUM7, CommonPhoneStrings.getInstance().SEVEN);
    @JsProperty
    public final GameKey KEY_NUM8 = new GameKey(Canvas.KEY_NUM8, CommonPhoneStrings.getInstance().EIGHT);
    @JsProperty
    public final GameKey KEY_NUM9 = new GameKey(Canvas.KEY_NUM9, CommonPhoneStrings.getInstance().NINE);
    @JsProperty
    public final GameKey GAME_A = new GameKey(Canvas.GAME_A, "Game A");
    @JsProperty
    public final GameKey GAME_B = new GameKey(Canvas.GAME_B, "Game B");
    @JsProperty
    public final GameKey GAME_C = new GameKey(Canvas.GAME_C, "Game C");
    @JsProperty
    public final GameKey GAME_D = new GameKey(Canvas.GAME_D, "Game D");
    @JsProperty
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

    @JsProperty
    public GameKey LEVEL_DOWN = new GameKey(Canvas.KEY_NUM9 + 1, "Cheat Level Up");
    @JsProperty
    public GameKey LEVEL_UP = new GameKey(Canvas.KEY_NUM9 + 2, "Cheat Level Down");
}
