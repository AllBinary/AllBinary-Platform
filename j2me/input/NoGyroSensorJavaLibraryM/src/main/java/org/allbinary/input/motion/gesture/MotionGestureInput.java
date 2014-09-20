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

import org.allbinary.game.input.Input;

public final class MotionGestureInput extends Input
{
    //private static MotionGestureInput[] motionGestureInputArray = new MotionGestureInput[Input.MAX];
    
    public MotionGestureInput(int id, String name)
    {
        super(id,  name);

        this.inputFactory.add(this.getId(), this);
    }
}
