/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 05/19/06
 *
 *
 * Modified By         When       ?
 *
 */
package org.allbinary.input.motion.gyro.action;

import org.allbinary.input.gyro.OrientationMotionGestureFactory;

import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;

public class LeftOrientationInputToGameKeyEventAction extends GameKeyCompleteMotionGestureInputEvent
{
    private static final GameKeyCompleteMotionGestureInputEvent instance = new LeftOrientationInputToGameKeyEventAction();
    
    public static GameKeyCompleteMotionGestureInputEvent getInstance()
    {
        return instance;
    }
    
    private LeftOrientationInputToGameKeyEventAction()
    {
        super("Left Action", OrientationMotionGestureFactory.getInstance().LEFT,
                PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
    }    
}
