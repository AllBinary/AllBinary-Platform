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
package org.allbinary.input.motion.touch.action;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;

public class ReleaseTouchInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static final GameKeyCompleteMotionGestureInputEvent SINGLETON = new ReleaseTouchInputToGameKeyEventAction();
    
    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        return SINGLETON;
    }
    
    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    
    private ReleaseTouchInputToGameKeyEventAction()
    {
        super("Release Action", TouchMotionGestureFactory.getInstance().RELEASED,
                PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
        
        try
        {
            this.setGameKey(NONE);
            this.setGameKeyEvent(GameKeyEventFactory.getInstance().getInstance(this, NONE));

        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
        }
    }
    
    public void update()
    {
    }    
}
