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

import allbinary.game.input.InputFactory;
import allbinary.input.motion.gesture.MotionGestureInput;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;
import allbinary.logic.basic.util.event.AllBinaryEventCircularPool;

public class MotionEventCircularPool {

    public static MotionEventCircularPool getInstance(int id)
    {
        return new MotionEventCircularPool(id);
    }
    
    private static final int MIN = TouchMotionGestureFactory.getInstance().LAST_MOTION.getId();
    
    private AllBinaryEventCircularPool eventPool = 
        new AllBinaryEventCircularPool((InputFactory.getInstance().MAX - 1) - MIN);
    
    private MotionEventCircularPool()
    {
        //do not use
    }
    
    private MotionEventCircularPool(int id)
    {
        eventPool.init(new MotionEventFactory(eventPool, id));
    }
    
    public synchronized MotionGestureEvent getInstance(MotionGestureInput motionGestureInput)
          throws Exception
    {
        return (MotionGestureEvent) eventPool.getInstance(motionGestureInput.getId() - MIN);
    }
}
