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

import org.allbinary.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

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
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(eventList.size()).toString(), this, "updateAll"));
        
        for(int index = eventList.size() - 1; index >= 0; index--)
        {
            GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent =
                (GameKeyCompleteMotionGestureInputEvent)
                eventList.objectArray[index];
            gameKeyCompleteMotionGestureInputEvent.update();
        }
    }
}
