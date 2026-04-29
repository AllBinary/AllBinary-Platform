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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class GameKeyCompleteMotionGestureInputEventFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final GameKeyCompleteMotionGestureInputEventFactory SINGLETON = 
        new GameKeyCompleteMotionGestureInputEventFactory();
    
    private final BasicArrayList eventList = new BasicArrayListD();
    
    public static GameKeyCompleteMotionGestureInputEventFactory getInstance()
    {
        return GameKeyCompleteMotionGestureInputEventFactory.SINGLETON;
    }
    
    public void add(GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent)
    {
        this.eventList.add(gameKeyCompleteMotionGestureInputEvent);
    }
    
    public void updateAll()
    {
        this.logUtil.putF(new StringMaker().append(CommonLabels.getInstance().START_LABEL).appendint(this.eventList.size()).toString(), this, "updateAll");
        
        for(int index = this.eventList.size() - 1; index >= 0; index--)
        {
            GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent =
                (GameKeyCompleteMotionGestureInputEvent)
                this.eventList.objectArray[index];
            gameKeyCompleteMotionGestureInputEvent.update();
        }
    }
}
