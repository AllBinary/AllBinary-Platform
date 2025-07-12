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
package org.allbinary.game.input.motion.action;

import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.game.input.CompleteMotionGestureInputEventListener;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.PressGameKeyEventHandler;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;

public class SingleKeyPressGameKeyFromMotionGestureEventListener 
extends CompleteMotionGestureInputEventListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    public void onCompleteMotionGestureInputEvent(CompleteMotionGestureInputEvent completeMotionGestureInputEvent)
    {
        try
        {
            //logUtil.put("Start GameKey: " + ((GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent).getGameKey(), this, "onCompleteMotionGestureInputEvent");
            
            GameKeyEvent gameKeyEvent = 
                ((GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent).getGameKeyEvent();

            //logUtil.put("gameKeyEvent: " + gameKeyEvent, this, "onCompleteMotionGestureInputEvent");

            PressGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "onCompleteMotionGestureInputEvent", e);
        }
    }
}
