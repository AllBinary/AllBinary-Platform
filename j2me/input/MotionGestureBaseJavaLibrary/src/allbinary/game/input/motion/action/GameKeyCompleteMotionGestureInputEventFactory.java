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

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class GameKeyCompleteMotionGestureInputEventFactory
{
    private static final GameKeyCompleteMotionGestureInputEventFactory SINGLETON = 
        new GameKeyCompleteMotionGestureInputEventFactory();
    
    private final BasicArrayList eventList = new BasicArrayList();
    
    public static GameKeyCompleteMotionGestureInputEventFactory getInstance()
    {
        return SINGLETON;
    }
    
    public void add(GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent)
    {
        eventList.add(gameKeyCompleteMotionGestureInputEvent);
    }
    
    public void updateAll()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + eventList.size(), this, "updateAll"));
        
        for(int index = eventList.size() - 1; index >= 0; index--)
        {
            GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent =
                (GameKeyCompleteMotionGestureInputEvent)
                eventList.objectArray[index];
            gameKeyCompleteMotionGestureInputEvent.update();
        }
    }
}
