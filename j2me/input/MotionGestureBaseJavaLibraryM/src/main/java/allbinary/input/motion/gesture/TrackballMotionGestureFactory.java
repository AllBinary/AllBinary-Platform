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

import allbinary.game.input.InputFactory;

public class TrackballMotionGestureFactory
{
    private static final TrackballMotionGestureFactory MOTION = new TrackballMotionGestureFactory();

    public static final TrackballMotionGestureFactory getInstance()
    {
        return MOTION;
    }
    
    public final MotionGestureInput LEFT;

    public final MotionGestureInput RIGHT;
    
    public final MotionGestureInput UP;

    public final MotionGestureInput DOWN;

    private TrackballMotionGestureFactory()
    {
        int MAX = InputFactory.getInstance().MAX;

        LEFT = new MotionGestureInput(MAX - 26, "L Trackball");

        RIGHT = new MotionGestureInput(MAX - 27, "R Trackball");

        UP = new MotionGestureInput(MAX - 28, "Up Trackball");

        DOWN = new MotionGestureInput(MAX - 29, "D Trackball");
    }
    
}
