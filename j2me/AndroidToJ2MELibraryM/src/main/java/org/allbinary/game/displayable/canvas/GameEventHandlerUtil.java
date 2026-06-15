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
package org.allbinary.game.displayable.canvas;

import org.allbinary.android.input.motion.GameInputMotionEventProcessorFactory;
import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;

public class GameEventHandlerUtil
{
    //public static BaseMotionGestureEventListener baseMotionGestureEventListener;
    
    public static void removeAllListeners()
    {   
        BasicMotionGesturesHandler.getInstance().removeAllListeners();
//        if(baseMotionGestureEventListener != null) {
//            BasicMotionGesturesHandler.getInstance().addListener(baseMotionGestureEventListener);
//        }
        

        GameKeyEventHandler.getInstance().removeAllListeners();
        //BaseGameEventHandlerUtil.removeAllListeners();

        GameInputMotionEventProcessorFactory.getInstance().addListener();
    }
}
