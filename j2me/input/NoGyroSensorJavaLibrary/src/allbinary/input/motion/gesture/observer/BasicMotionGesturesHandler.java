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

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class BasicMotionGesturesHandler extends BasicEventHandler
{
    private static final BasicMotionGesturesHandler SINGLETON =
       new BasicMotionGesturesHandler();

    public static final BasicMotionGesturesHandler getInstance()
    {
        return SINGLETON;
    }
    
    protected BasicMotionGesturesHandler()
    {
    }

    protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {
        
        ((BaseMotionGestureEventListener) eventListenerInterface).onMotionGestureEvent(
           (MotionGestureEvent) eventObject);
    }
}
