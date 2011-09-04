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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.CompleteMotionGestureInputEvent;
import allbinary.game.input.CompleteMotionGestureInputEventListener;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.PressGameKeyEventHandler;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class SingleKeyPressGameKeyFromMotionGestureEventListener 
extends CompleteMotionGestureInputEventListener
{
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onCompleteMotionGestureInputEvent(CompleteMotionGestureInputEvent completeMotionGestureInputEvent)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance("Start GameKey: " + ((GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent).getGameKey(), this, "onCompleteMotionGestureInputEvent"));
            
            GameKeyEvent gameKeyEvent = 
                ((GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent).getGameKeyEvent();

            //LogUtil.put(LogFactory.getInstance("gameKeyEvent: " + gameKeyEvent, this, "onCompleteMotionGestureInputEvent"));

            PressGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "onCompleteMotionGestureInputEvent", e));
        }
    }
}
