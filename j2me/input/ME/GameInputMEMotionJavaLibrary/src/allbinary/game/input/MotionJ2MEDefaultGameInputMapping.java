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
package allbinary.game.input;

import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.game.input.mapping.PersistentInputMapping;
import allbinary.input.motion.button.BasicTouchInputFactory;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;

public class MotionJ2MEDefaultGameInputMapping
        extends PersistentInputMapping
{
    public MotionJ2MEDefaultGameInputMapping()
    {
        super(PersistentInputMapping.DEFAULT_SAVE_NAME);

        InputToGameKeyMapping inputToGameKeyMapping = this.getInputMapping();

        // Also up
        inputToGameKeyMapping.add(GameKey.UP, GameKey.KEY_NUM2);
        // Also left
        inputToGameKeyMapping.add(GameKey.LEFT, GameKey.KEY_NUM4);
        // Also right
        inputToGameKeyMapping.add(GameKey.RIGHT, GameKey.KEY_NUM6);
        // Also down
        inputToGameKeyMapping.add(GameKey.DOWN, GameKey.KEY_NUM8);

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.GAME_A);
        inputToGameKeyMapping.add(GameKey.KEY_NUM3, GameKey.GAME_B);
        inputToGameKeyMapping.add(GameKey.KEY_NUM7, GameKey.GAME_C);
        inputToGameKeyMapping.add(GameKey.KEY_NUM9, GameKey.GAME_D);

        /*
        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.KEYBOARD_Y);
        inputToGameKeyMapping.add(GameKey.KEY_NUM2, GameKey.KEYBOARD_U);
        inputToGameKeyMapping.add(GameKey.KEY_NUM3, GameKey.KEYBOARD_I);
        inputToGameKeyMapping.add(GameKey.KEY_NUM4, GameKey.KEYBOARD_H);
        inputToGameKeyMapping.add(GameKey.KEY_NUM5, GameKey.KEYBOARD_J);
        inputToGameKeyMapping.add(GameKey.KEY_NUM6, GameKey.KEYBOARD_K);
        inputToGameKeyMapping.add(GameKey.KEY_NUM7, GameKey.KEYBOARD_N);
        inputToGameKeyMapping.add(GameKey.KEY_NUM8, GameKey.KEYBOARD_M);

        inputToGameKeyMapping.add(GameKey.KEY_NUM9, GameKey.KEYBOARD_LESS);
        inputToGameKeyMapping.add(GameKey.KEY_STAR, GameKey.KEYBOARD_SPACE);
        inputToGameKeyMapping.add(GameKey.KEY_NUM0, GameKey.KEYBOARD_INSERT);
        inputToGameKeyMapping.add(GameKey.KEY_POUND, GameKey.KEYBOARD_DELETE);
         */

        inputToGameKeyMapping.add(GameKey.LEVEL_DOWN, GameKey.LEVEL_DOWN);
        inputToGameKeyMapping.add(GameKey.LEVEL_UP, GameKey.LEVEL_UP);

        inputToGameKeyMapping.add(GameKey.UP, GameKey.UP);

        inputToGameKeyMapping.add(GameKey.DOWN, GameKey.DOWN);

        inputToGameKeyMapping.add(GameKey.LEFT, GameKey.LEFT);

        inputToGameKeyMapping.add(GameKey.RIGHT, GameKey.RIGHT);

        inputToGameKeyMapping.add(GameKey.KEY_POUND, GameKey.KEY_POUND);

        inputToGameKeyMapping.add(GameKey.KEY_STAR, GameKey.KEY_STAR);

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.KEY_NUM1);

        inputToGameKeyMapping.add(GameKey.KEY_NUM3, GameKey.KEY_NUM3);

        inputToGameKeyMapping.add(GameKey.KEY_NUM5, GameKey.KEY_NUM5);

        inputToGameKeyMapping.add(GameKey.KEY_NUM7, GameKey.KEY_NUM7);

        inputToGameKeyMapping.add(GameKey.KEY_NUM9, GameKey.KEY_NUM9);

        inputToGameKeyMapping.add(GameKey.KEY_NUM0, GameKey.KEY_NUM0);

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.FIRE);

        //
        inputToGameKeyMapping.add(GameKey.UP, TouchMotionGestureFactory.getInstance().UP);
        inputToGameKeyMapping.add(GameKey.DOWN, TouchMotionGestureFactory.getInstance().DOWN);
        //inputToGameKeyMapping.add(GameKey.KEY_NUM5, TouchMotionGestureFactory.getInstance().TOUCH);
        //inputToGameKeyMapping.add(GameKey.KEY_NUM5, TouchMotionGestureFactory.getInstance().DIAGONAL_DOWN_LEFT);
        //inputToGameKeyMapping.add(GameKey.KEY_NUM5, TouchMotionGestureFactory.getInstance().DIAGONAL_DOWN_RIGHT);
        inputToGameKeyMapping.add(GameKey.LEFT, TouchMotionGestureFactory.getInstance().LEFT);
        inputToGameKeyMapping.add(GameKey.RIGHT, TouchMotionGestureFactory.getInstance().RIGHT);

        //
        
        //inputToGameKeyMapping.add(GameKey.UP, TrackballMotionGestureFactory.getInstance().UP);
        //inputToGameKeyMapping.add(GameKey.DOWN, TrackballMotionGestureFactory.getInstance().DOWN);
        //inputToGameKeyMapping.add(GameKey.LEFT, TrackballMotionGestureFactory.getInstance().LEFT);
        //inputToGameKeyMapping.add(GameKey.RIGHT, TrackballMotionGestureFactory.getInstance().RIGHT);

        //

        inputToGameKeyMapping.add(GameKey.UP, BasicTouchInputFactory.getInstance().UP);
        inputToGameKeyMapping.add(GameKey.LEFT, BasicTouchInputFactory.getInstance().LEFT);
        inputToGameKeyMapping.add(GameKey.RIGHT, BasicTouchInputFactory.getInstance().RIGHT);
        inputToGameKeyMapping.add(GameKey.DOWN, BasicTouchInputFactory.getInstance().DOWN);

        inputToGameKeyMapping.add(GameKey.KEY_NUM0, BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_FIVE);
        inputToGameKeyMapping.add(GameKey.KEY_NUM7, BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_FOUR);
        inputToGameKeyMapping.add(GameKey.KEY_NUM9, BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_THREE);
        inputToGameKeyMapping.add(GameKey.KEY_NUM1, BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_TWO);
        inputToGameKeyMapping.add(GameKey.KEY_NUM3, BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_ONE);

        //
        inputToGameKeyMapping.add(GameKey.KEY_NUM2, BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_SIX);

    }
}
