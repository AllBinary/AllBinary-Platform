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
package allbinary.game.input;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class CompleteMotionGestureInputEventHandler extends BasicEventHandler
{    
    private static final CompleteMotionGestureInputEventHandler SINGLETON = 
        new CompleteMotionGestureInputEventHandler();
    
    public CompleteMotionGestureInputEventHandler()
    {
    }
    
    public static CompleteMotionGestureInputEventHandler getInstance()
    {
        return SINGLETON;
    }
    
    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception {

       ((CompleteMotionGestureInputEventListenerInterface) eventListenerInterface).onCompleteMotionGestureInputEvent(
               (CompleteMotionGestureInputEvent) eventObject);
    }
}
