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
package allbinary.input.motion.gesture.observer;

import allbinary.game.input.Input;
import allbinary.game.input.InputFactory;
import allbinary.input.motion.gesture.MotionGestureInput;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.AllBinaryEventObjectFactoryInterface;

public class MotionEventFactory implements AllBinaryEventObjectFactoryInterface
{
    private static int index;
    
    private final Object source;
    
    private final int id;
    
    public MotionEventFactory(Object source, int id)
    {
        this.source = source;
        this.id = id;
        index = TouchMotionGestureFactory.getInstance().LAST_MOTION.getId();
    }
    
    public AllBinaryEventObject getInstance()
    {   
        Input input = InputFactory.getInstance().getInstance(index++);
        //LogUtil.put(LogFactory.getInstance("Input: " + input, this, CommonStrings.getInstance().GET_INSTANCE));
        
        return new MotionGestureEvent(this.source, this.id, (MotionGestureInput) input );
    }
    
}
