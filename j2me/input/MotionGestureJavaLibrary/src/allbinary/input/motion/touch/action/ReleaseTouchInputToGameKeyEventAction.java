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
package allbinary.input.motion.touch.action;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.GameKey;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;

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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }
    
    public void update()
    {
    }    
}
