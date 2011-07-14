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
import allbinary.game.input.CompleteMotionGestureInputEventListenerInterface;
import allbinary.game.input.event.DownGameKeyEventHandler;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.UpGameKeyEventHandler;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameKeyFromMotionGestureEventListener implements
        CompleteMotionGestureInputEventListenerInterface
{
    private GameKeyEvent previousGameKeyEvent = GameKeyEvent.NONE;

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
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

            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + gameKeyCompleteMotionGestureInputEvent.getGameKey(), this, "onCompleteMotionGestureInputEvent"));
            //PreLogUtil.put(CommonStrings.getInstance().START_LABEL + gameKeyCompleteMotionGestureInputEvent.getGameKey(), this, "onCompleteMotionGestureInputEvent");
            
            GameKeyEvent gameKeyEvent = gameKeyCompleteMotionGestureInputEvent.getGameKeyEvent();

            if (gameKeyEvent != previousGameKeyEvent)
            {
                if (previousGameKeyEvent != GameKeyEvent.NONE)
                //if (GameKeyCancelUtil.isGameKeyEventCancelledByAnother(previousGameKeyEvent, gameKeyEvent))
                {
                    //LogUtil.put(LogFactory.getInstance("Up: " + previousGameKeyEvent.toString(), this, "onCompleteMotionGestureInputEvent"));

                    UpGameKeyEventHandler.getInstance().fireEvent(
                            previousGameKeyEvent);
                }
                /*
                else
                {
                    LogUtil.put(LogFactory.getInstance("No Key", this, "onCompleteMotionGestureInputEvent"));
                }
                */

                previousGameKeyEvent = gameKeyEvent;
                
                //if(gameKeyEvent != releaseGameKeyEvent)
                if(gameKeyEvent != null && gameKeyEvent != GameKeyEvent.NONE)
                {
                    //LogUtil.put(LogFactory.getInstance("Down: " + gameKeyCompleteMotionGestureInputEvent.getGameKey(), this, "onCompleteMotionGestureInputEvent"));
                    
                    DownGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
                }
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "onCompleteMotionGestureInputEvent", e));
        }
    }
}