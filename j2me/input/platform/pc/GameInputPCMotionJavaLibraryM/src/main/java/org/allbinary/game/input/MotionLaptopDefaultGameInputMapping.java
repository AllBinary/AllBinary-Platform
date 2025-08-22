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

import org.allbinary.game.configuration.persistance.GamePersistanceStrings;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.game.input.mapping.PersistentInputMapping;
import org.allbinary.input.motion.button.BasicTouchInputFactory;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;

public class MotionLaptopDefaultGameInputMapping
        extends PersistentInputMapping
{
    public MotionLaptopDefaultGameInputMapping()
    {
        super(GamePersistanceStrings.getInstance().DEFAULT_INPUT_MAPPING_RECORD_ID);

        final PCKeyFactory pcKeyFactory = PCKeyFactory.getInstance();

        final InputToGameKeyMapping inputToGameKeyMapping = this.getInputMapping();
        final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
        final TouchMotionGestureFactory touchMotionGestureFactory = TouchMotionGestureFactory.getInstance();
        final BasicTouchInputFactory basicTouchInputFactory = BasicTouchInputFactory.getInstance();

        inputToGameKeyMapping.add(gameKeyFactory.FIRE, pcKeyFactory.ENTER);
        
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, AndroidKeyFactory.getInstance().ONE);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.FOUR);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory._P);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.p);

        //
        //inputToGameKeyMapping.add(gameKeyFactory.UP, gameKeyFactory.UP);
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory.DPAD_UP);
        //inputToGameKeyMapping.add(gameKeyFactory.DOWN, gameKeyFactory.DOWN);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory.DPAD_DOWN);
        //inputToGameKeyMapping.add(gameKeyFactory.LEFT, gameKeyFactory.LEFT);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory.DPAD_LEFT);
        //inputToGameKeyMapping.add(gameKeyFactory.RIGHT, gameKeyFactory.RIGHT);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory.DPAD_RIGHT);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.FIVE);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.DPAD_CENTER);

        //
        
        /*
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, AndroidKeyFactory.getInstance().SEVEN);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, AndroidKeyFactory.getInstance().NINE);
        inputToGameKeyMapping.add(gameKeyFactory.UP, AndroidKeyFactory.getInstance().TWO);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, AndroidKeyFactory.getInstance().FOUR);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, AndroidKeyFactory.getInstance().FIVE);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, AndroidKeyFactory.getInstance().SIX);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, AndroidKeyFactory.getInstance().EIGHT);
        */
        
        //
        
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, pcKeyFactory._Y);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, pcKeyFactory._I);
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory._U);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory._H);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory._J);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory._N);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, pcKeyFactory._M);

        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, pcKeyFactory.y);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, pcKeyFactory.i);
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory.u);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory.h);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory.j);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory.n);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, pcKeyFactory.m);
        
        //

        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, pcKeyFactory._Q);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, pcKeyFactory._E);
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory._W);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory._A);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory._D);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory._S);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, pcKeyFactory._X);

        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, pcKeyFactory.q);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, pcKeyFactory.e);
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory.w);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory.a);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory.d);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory.s);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, pcKeyFactory.x);

        //
        inputToGameKeyMapping.add(gameKeyFactory.UP, touchMotionGestureFactory.UP);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, touchMotionGestureFactory.DOWN);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, touchMotionGestureFactory.TOUCH);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, touchMotionGestureFactory.DIAGONAL_DOWN_LEFT);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM5, touchMotionGestureFactory.DIAGONAL_DOWN_RIGHT);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, touchMotionGestureFactory.LEFT);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, touchMotionGestureFactory.RIGHT);

        //
        
        //inputToGameKeyMapping.add(gameKeyFactory.UP, TrackballMotionGestureFactory.getInstance().UP);
        //inputToGameKeyMapping.add(gameKeyFactory.DOWN, TrackballMotionGestureFactory.getInstance().DOWN);
        //inputToGameKeyMapping.add(gameKeyFactory.LEFT, TrackballMotionGestureFactory.getInstance().LEFT);
        //inputToGameKeyMapping.add(gameKeyFactory.RIGHT, TrackballMotionGestureFactory.getInstance().RIGHT);

        //

        inputToGameKeyMapping.add(gameKeyFactory.UP, basicTouchInputFactory.UP);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, basicTouchInputFactory.LEFT);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, basicTouchInputFactory.RIGHT);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, basicTouchInputFactory.DOWN);


        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM0, basicTouchInputFactory.SPECIAL_BUTTON_FIVE);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, basicTouchInputFactory.SPECIAL_BUTTON_FOUR);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, basicTouchInputFactory.SPECIAL_BUTTON_THREE);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, basicTouchInputFactory.SPECIAL_BUTTON_TWO);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM3, basicTouchInputFactory.SPECIAL_BUTTON_ONE);
        
        //
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM2, basicTouchInputFactory.SPECIAL_BUTTON_SIX);
        
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM3, pcKeyFactory.THREE);

        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM0, AndroidKeyFactory.getInstance().SLASH);

        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, AndroidKeyFactory.getInstance().gameKeyFactory.KEYBOARD_LESS);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM0, pcKeyFactory.ZERO);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM0, pcKeyFactory.ONE);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM0, pcKeyFactory.SPACE);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM0, AndroidKeyFactory.getInstance().gameKeyFactory.KEYBOARD_INSERT);

        inputToGameKeyMapping.add(gameKeyFactory.KEY_POUND, pcKeyFactory.DEL);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_POUND, pcKeyFactory.TWO);

        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM7, OrientationMotionGestureFactory.getInstance().ROLL_RIGHT);
        //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM9, OrientationMotionGestureFactory.getInstance().ROLL_LEFT);

        inputToGameKeyMapping.add(gameKeyFactory.KEY_STAR, pcKeyFactory.ESCAPE);
        inputToGameKeyMapping.add(gameKeyFactory.KEY_STAR, pcKeyFactory.STAR);

        inputToGameKeyMapping.add(gameKeyFactory.LEVEL_DOWN, pcKeyFactory.COMMA);
        inputToGameKeyMapping.add(gameKeyFactory.LEVEL_UP, pcKeyFactory.PERIOD);
    }
}
