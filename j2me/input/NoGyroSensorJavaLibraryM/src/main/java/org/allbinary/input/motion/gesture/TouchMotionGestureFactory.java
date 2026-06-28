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
package org.allbinary.input.motion.gesture;

import org.allbinary.game.input.InputFactory;
import org.allbinary.logic.NullUtil;

public class TouchMotionGestureFactory
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static final TouchMotionGestureFactory getInstance()
    {
        if(TouchMotionGestureFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            TouchMotionGestureFactory.instance = new TouchMotionGestureFactory();
        }
        
        return (TouchMotionGestureFactory) TouchMotionGestureFactory.instance;
    }

    public final MotionGestureInput LEFT;

    public final MotionGestureInput RIGHT;
    
    public final MotionGestureInput UP;

    public final MotionGestureInput DOWN;

    public final MotionGestureInput PRESSED;
    public final MotionGestureInput RELEASED;

    public final MotionGestureInput DIAGONAL_DOWN_RIGHT;

    public final MotionGestureInput DIAGONAL_DOWN_LEFT;

    public final MotionGestureInput DIAGONAL_UP_RIGHT;

    public final MotionGestureInput DIAGONAL_UP_LEFT;

    public final MotionGestureInput SCROLL_UP;
    public final MotionGestureInput SCROLL_DOWN;
    
    //public final MotionGestureInput TOUCH = new MotionGestureInput(Input.MAX - 12, "Touch");

    public final MotionGestureInput NO_MOTION;
    
    public final MotionGestureInput LAST_MOTION;
    
    private TouchMotionGestureFactory()
    {
        int MAX = InputFactory.getInstance().MAX;
        
        this.LEFT = new MotionGestureInput(MAX - 3, "Left Touch");

        this.RIGHT = new MotionGestureInput(MAX - 4, "Right Touch");

        this.UP = new MotionGestureInput(MAX - 5, "Up Touch");

        this.DOWN = new MotionGestureInput(MAX - 6, "Down Touch");

        this.PRESSED = new MotionGestureInput(MAX - 7, "Screen Press");
        this.RELEASED = new MotionGestureInput(MAX - 8, "Released");

        this.DIAGONAL_DOWN_RIGHT = new MotionGestureInput(MAX - 9, "Diagonal Dn R");

        this.DIAGONAL_DOWN_LEFT = new MotionGestureInput(MAX - 10, "Diagonal Dn L");

        this.DIAGONAL_UP_RIGHT = new MotionGestureInput(MAX - 11, "Diagonal Up R");

        this.DIAGONAL_UP_LEFT = new MotionGestureInput(MAX - 12, "Diagonal Up L");

        this.SCROLL_UP = new MotionGestureInput(MAX - 13, "Scroll Up");
        this.SCROLL_DOWN = new MotionGestureInput(MAX - 14, "Scroll Down");

        //TOUCH = new MotionGestureInput(MAX - 12, "Touch");

        this.NO_MOTION = new MotionGestureInput(MAX - 2, "No Motion");

        this.LAST_MOTION = new MotionGestureInput(MAX - 19, "Last Motion");
    }
}
