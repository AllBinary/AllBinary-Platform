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
package org.allbinary.input.motion.gesture.observer;

import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.AllBinaryEventObjectFactoryInterface;

public class MotionEventFactory implements AllBinaryEventObjectFactoryInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static int index = 0;
    
    private final Object source;
    
    private final int id;
    
    public MotionEventFactory(Object source, int id)
    {
        this.source = source;
        this.id = id;
        index = TouchMotionGestureFactory.getInstance().LAST_MOTION.getId();
    }
    
    @Override
    public AllBinaryEventObject getInstance()
    {   
        final InputFactory inputFactory = InputFactory.getInstance();
        final Input input = inputFactory.getInstance(index++);
        //logUtil.put("Input: " + input, this, commonStrings.GET_INSTANCE);
        
        if(input == inputFactory.NO_INPUT) {
            return new MotionGestureEvent(this.source, this.id, (MotionGestureInput) TouchMotionGestureFactory.getInstance().NO_MOTION);
        } else {
            return new MotionGestureEvent(this.source, this.id, (MotionGestureInput) input);
        }
    }
    
}
