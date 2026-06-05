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

package org.allbinary.input.gyro;

import org.allbinary.game.input.InputFactory;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.logic.NullUtil;

public class OrientationMotionGestureFactory
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    public static final OrientationMotionGestureFactory getInstance()
    {
        if(OrientationMotionGestureFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            OrientationMotionGestureFactory.instance = new OrientationMotionGestureFactory();
        }
        
        return (OrientationMotionGestureFactory) OrientationMotionGestureFactory.instance;
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

        this.LEFT = new MotionGestureInput(MAX - 20, "Left Orient");

        this.RIGHT = new MotionGestureInput(MAX - 21, "Right Orient");

        this.UP = new MotionGestureInput(MAX - 22, "Up Orient");

        this.DOWN = new MotionGestureInput(MAX - 23, "Down Orient");

        this.ROLL_LEFT = new MotionGestureInput(MAX - 24, "Roll Left");

        this.ROLL_RIGHT = new MotionGestureInput(MAX - 25, "Roll Right");
    }
}
