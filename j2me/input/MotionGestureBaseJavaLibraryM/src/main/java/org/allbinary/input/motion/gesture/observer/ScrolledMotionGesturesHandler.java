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


import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class ScrolledMotionGesturesHandler extends BasicEventHandler
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ScrolledMotionGesturesHandler SINGLETON =
       new ScrolledMotionGesturesHandler();

    public static final ScrolledMotionGesturesHandler getInstance()
    {
        return ScrolledMotionGesturesHandler.SINGLETON;
    }
    
    protected ScrolledMotionGesturesHandler()
    {
    }

    @Override
    protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {
        
        final BaseMotionGestureEventListener baseMotionGestureEventListener = (BaseMotionGestureEventListener) eventListenerInterface;
        baseMotionGestureEventListener.onScrolledMotionGestureEvent((MotionGestureEvent) eventObject);
    }
}
