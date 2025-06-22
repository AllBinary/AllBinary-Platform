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
package org.allbinary.input.motion;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;

public class CompleteMotionGestureInputToGameMotionGestureInput {

    private static final CompleteMotionGestureInputToGameMotionGestureInput instance =
        new CompleteMotionGestureInputToGameMotionGestureInput();

    public static CompleteMotionGestureInputToGameMotionGestureInput getInstance() {
        return instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    public void init() {
        PreLogUtil.put("No Touch Button = No Compound Motion Gestures", instance, this.commonStrings.INIT);
    }
}
