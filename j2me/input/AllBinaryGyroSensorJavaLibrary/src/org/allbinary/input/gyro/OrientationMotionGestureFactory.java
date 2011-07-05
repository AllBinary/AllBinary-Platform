/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 05/19/06
 *
 *
 * Modified By         When       ?
 *
 */
package org.allbinary.input.gyro;

import allbinary.game.input.InputFactory;
import allbinary.input.motion.gesture.MotionGestureInput;

public class OrientationMotionGestureFactory
{
    private static final OrientationMotionGestureFactory instance = new OrientationMotionGestureFactory();

    public static final OrientationMotionGestureFactory getInstance()
    {
        return instance;
    }

    public final MotionGestureInput LEFT;

    public final MotionGestureInput RIGHT;
    
    public final MotionGestureInput UP;

    public final MotionGestureInput DOWN;
    
    public final MotionGestureInput ROLL_LEFT;

    public final MotionGestureInput ROLL_RIGHT;

    private OrientationMotionGestureFactory()
    {
        int MAX = InputFactory.getInstance().MAX;

        LEFT = new MotionGestureInput(MAX - 20, "Left Orient");

        RIGHT = new MotionGestureInput(MAX - 21, "Right Orient");

        UP = new MotionGestureInput(MAX - 22, "Up Orient");

        DOWN = new MotionGestureInput(MAX - 23, "Down Orient");

        ROLL_LEFT = new MotionGestureInput(MAX - 24, "Roll Left");

        ROLL_RIGHT = new MotionGestureInput(MAX - 25, "Roll Right");
    }
}
