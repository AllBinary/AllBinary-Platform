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

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.game.input.CompleteMotionGestureInputEventListener;
import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.UpGameKeyEventHandler;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class GameKeyFromMotionGestureEventListener 
extends CompleteMotionGestureInputEventListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private GameKeyEvent previousGameKeyEvent = GameKeyEvent.NONE;

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    //TWB - Is this correct?
    //private GameKeyEvent releaseGameKeyEvent = GameKeyEvent.NONE;
       //GameKeyEventFactory.getInstance(this, GameKey.NONE);
    //ReleaseTouchInputToGameKeyEventAction.getInstance().getGameKeyEvent();
    
    public void onCompleteMotionGestureInputEvent(
            CompleteMotionGestureInputEvent completeMotionGestureInputEvent)
    {
        try
        {
            GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent = 
                (GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent;

            //logUtil.put(commonStrings.START_LABEL + gameKeyCompleteMotionGestureInputEvent.getGameKey(), this, "onCompleteMotionGestureInputEvent");
            //PreLogUtil.put(commonStrings.START_LABEL + gameKeyCompleteMotionGestureInputEvent.getGameKey(), this, "onCompleteMotionGestureInputEvent");
            
            GameKeyEvent gameKeyEvent = gameKeyCompleteMotionGestureInputEvent.getGameKeyEvent();

            if (gameKeyEvent != previousGameKeyEvent)
            {
                if (previousGameKeyEvent != GameKeyEvent.NONE)
                //if (GameKeyCancelUtil.isGameKeyEventCancelledByAnother(previousGameKeyEvent, gameKeyEvent))
                {
                    //logUtil.put("Up: " + previousGameKeyEvent.toString(), this, "onCompleteMotionGestureInputEvent");

                    UpGameKeyEventHandler.getInstance().fireEvent(
                            previousGameKeyEvent);
                }
                /*
                else
                {
                    logUtil.put("No Key", this, "onCompleteMotionGestureInputEvent");
                }
                */

                previousGameKeyEvent = gameKeyEvent;
                
                //if(gameKeyEvent != releaseGameKeyEvent)
                if(gameKeyEvent != null && gameKeyEvent != GameKeyEvent.NONE)
                {
                    //logUtil.put("Down: " + gameKeyCompleteMotionGestureInputEvent.getGameKey(), this, "onCompleteMotionGestureInputEvent");
                    
                    DownGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
                }
            }
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "onCompleteMotionGestureInputEvent", e);
        }
    }
}