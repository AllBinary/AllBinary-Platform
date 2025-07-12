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

public class TouchMotionGestureFactory
{
    private static final TouchMotionGestureFactory MOTION = new TouchMotionGestureFactory();
    
    public static final TouchMotionGestureFactory getInstance()
    {
        return MOTION;
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
    
    //public final MotionGestureInput TOUCH = new MotionGestureInput(Input.MAX - 12, "Touch");
    
    public final MotionGestureInput NO_MOTION;
    
    public final MotionGestureInput LAST_MOTION;
    
    private TouchMotionGestureFactory()
    {
        int MAX = InputFactory.getInstance().MAX;
        
        LEFT = new MotionGestureInput(MAX - 3, "Left Touch");

        RIGHT = new MotionGestureInput(MAX - 4, "Right Touch");

        UP = new MotionGestureInput(MAX - 5, "Up Touch");

        DOWN = new MotionGestureInput(MAX - 6, "Down Touch");

        PRESSED = new MotionGestureInput(MAX - 7, "Screen Press");
        RELEASED = new MotionGestureInput(MAX - 8, "Released");

        DIAGONAL_DOWN_RIGHT = new MotionGestureInput(MAX - 9, "Diagonal Dn R");

        DIAGONAL_DOWN_LEFT = new MotionGestureInput(MAX - 10, "Diagonal Dn L");

        DIAGONAL_UP_RIGHT = new MotionGestureInput(MAX - 11, "Diagonal Up R");

        DIAGONAL_UP_LEFT = new MotionGestureInput(MAX - 12, "Diagonal Up L");

        //TOUCH = new MotionGestureInput(MAX - 12, "Touch");

        NO_MOTION = new MotionGestureInput(MAX - 2, "No Motion");

        LAST_MOTION = new MotionGestureInput(MAX - 19, "Last Motion");
    }
}
