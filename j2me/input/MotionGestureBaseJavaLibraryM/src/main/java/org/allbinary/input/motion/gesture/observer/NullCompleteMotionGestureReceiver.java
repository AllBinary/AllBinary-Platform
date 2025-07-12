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
package org.allbinary.input.motion.gesture.observer;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class NullCompleteMotionGestureReceiver
implements CompleteMotionGestureListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public NullCompleteMotionGestureReceiver()
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put("Null Gesture Reciever", this, commonStrings.CONSTRUCTOR);
    }

    public void onMotionGestureCompleted(BasicArrayList list)
        throws Exception
    {
        
    }
}
