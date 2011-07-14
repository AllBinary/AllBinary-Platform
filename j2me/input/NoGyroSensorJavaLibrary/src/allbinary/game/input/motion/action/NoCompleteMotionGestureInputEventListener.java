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
package allbinary.game.input.motion.action;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.game.input.CompleteMotionGestureInputEvent;
import allbinary.game.input.CompleteMotionGestureInputEventListenerInterface;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class NoCompleteMotionGestureInputEventListener implements
CompleteMotionGestureInputEventListenerInterface
{
    private static final NoCompleteMotionGestureInputEventListener instance =
        new NoCompleteMotionGestureInputEventListener();
    
    private NoCompleteMotionGestureInputEventListener()
    {
        
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onCompleteMotionGestureInputEvent(CompleteMotionGestureInputEvent completeMotionGestureInputEvent)
    {
    }

    public static NoCompleteMotionGestureInputEventListener getInstance()
    {
        return instance;
    }
}
