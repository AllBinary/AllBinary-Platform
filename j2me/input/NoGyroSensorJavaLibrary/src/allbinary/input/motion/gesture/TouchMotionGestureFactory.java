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
package allbinary.input.motion.gesture;

import allbinary.game.input.Input;

public class TouchMotionGestureFactory
{
    private static final TouchMotionGestureFactory MOTION = new TouchMotionGestureFactory();

    public final MotionGestureInput LEFT = new MotionGestureInput(Input.MAX - 3, "Left Touch");

    public final MotionGestureInput RIGHT = new MotionGestureInput(Input.MAX - 4, "Right Touch");
    
    public final MotionGestureInput UP = new MotionGestureInput(Input.MAX - 5, "Up Touch");

    public final MotionGestureInput DOWN = new MotionGestureInput(Input.MAX - 6, "Down Touch");

    public final MotionGestureInput PRESSED = new MotionGestureInput(Input.MAX - 7, "Screen Press");
    public final MotionGestureInput RELEASED = new MotionGestureInput(Input.MAX - 8, "Released");

    public final MotionGestureInput DIAGONAL_DOWN_RIGHT = new MotionGestureInput(Input.MAX - 9, "Diagonal Dn R");

    public final MotionGestureInput DIAGONAL_DOWN_LEFT = new MotionGestureInput(Input.MAX - 10, "Diagonal Dn L");

    public final MotionGestureInput DIAGONAL_UP_RIGHT = new MotionGestureInput(Input.MAX - 11, "Diagonal Up R");

    public final MotionGestureInput DIAGONAL_UP_LEFT = new MotionGestureInput(Input.MAX - 12, "Diagonal Up L");
    
    //public final MotionGestureInput TOUCH = new MotionGestureInput(Input.MAX - 12, "Touch");
    
    public final MotionGestureInput NO_MOTION = new MotionGestureInput(Input.MAX - 2, "No Motion");
    
    public final MotionGestureInput LAST_MOTION = new MotionGestureInput(Input.MAX - 19, "Last Motion");
    
    public static final TouchMotionGestureFactory getInstance()
    {
        return MOTION;
    }
}
