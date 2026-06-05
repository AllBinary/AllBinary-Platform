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
package org.allbinary.input.motion.gyro.action;

import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.input.gyro.OrientationMotionGestureFactory;
import org.allbinary.logic.NullUtil;

public class UpOrientationInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        if(UpOrientationInputToGameKeyEventAction.instance == NullUtil.getInstance().NULL_OBJECT) {
            UpOrientationInputToGameKeyEventAction.instance = new UpOrientationInputToGameKeyEventAction();
        }

        return (GameKeyCompleteMotionGestureInputEvent) UpOrientationInputToGameKeyEventAction.instance;
    }

    private UpOrientationInputToGameKeyEventAction()
    {
        super("Up Action", OrientationMotionGestureFactory.getInstance().UP,
                PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
    }
}
