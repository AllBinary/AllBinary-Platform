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

import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.string.CommonStrings;

public class ReleaseTouchInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        if(ReleaseTouchInputToGameKeyEventAction.instance == NullUtil.getInstance().NULL_OBJECT) {
            ReleaseTouchInputToGameKeyEventAction.instance = new ReleaseTouchInputToGameKeyEventAction();
        }
        
        return (GameKeyCompleteMotionGestureInputEvent) ReleaseTouchInputToGameKeyEventAction.instance;
    }
    
    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    
    private ReleaseTouchInputToGameKeyEventAction()
    {
        super("Release Action", TouchMotionGestureFactory.getInstance().RELEASED,
                PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
        
        try
        {
            this.setGameKey(this.NONE);
            this.setGameKeyEvent(GameKeyEventFactory.getInstance().getInstanceForInput(this, this.NONE));

        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    @Override    
    public void update()
    {
    }    
}
