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

import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.NullUtil;

public class DiagonalDownRightTouchInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        if(DiagonalDownRightTouchInputToGameKeyEventAction.instance == NullUtil.getInstance().NULL_OBJECT) {
            DiagonalDownRightTouchInputToGameKeyEventAction.instance = new DiagonalDownRightTouchInputToGameKeyEventAction();
        }

        return (GameKeyCompleteMotionGestureInputEvent) DiagonalDownRightTouchInputToGameKeyEventAction.instance;
    }

    private DiagonalDownRightTouchInputToGameKeyEventAction()
    {
        super("Diagonal Down Right Action", 
           TouchMotionGestureFactory.getInstance().DIAGONAL_DOWN_RIGHT,
           PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
    }    
}
