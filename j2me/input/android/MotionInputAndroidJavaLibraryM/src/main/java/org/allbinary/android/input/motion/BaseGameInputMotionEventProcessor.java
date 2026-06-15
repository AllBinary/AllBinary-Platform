/*
* AllBinary Open License Version 1
* Copyright (c) 2009 AllBinary
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

package org.allbinary.android.input.motion;

import android.view.MotionEvent;

import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.input.motion.gesture.observer.GameMotionGestureListener;
import org.allbinary.input.motion.gesture.observer.MotionGestureReceiveInterfaceFactory;
import org.allbinary.string.CommonStrings;

public class BaseGameInputMotionEventProcessor extends MotionEventProcessor
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //private AndroidMotionEventsToMotionGestureListener motionEventsToMotionGestureListener;

    private final GameMotionGestureListener gameMotionGestureListener;

    protected BaseGameInputMotionEventProcessor()
    {
        /*
        motionEventsToMotionGestureListener = new AndroidMotionEventsToMotionGestureListener();
        
        MotionGestureRecognizer motionGestureRecognizer = 
            this.motionEventsToMotionGestureListener.getMotionRecognizer().getMotionGestureRecognizer();
        BasicMotionGesturesHandler motionGesturesHandler = 
            motionGestureRecognizer.getMotionGesturesHandler();
        */

        this.gameMotionGestureListener =
            new GameMotionGestureListener(
                    MotionGestureReceiveInterfaceFactory.getInstance());
        
        this.addListener();
    }

    @Override
    public void process(MotionEvent motionEvent)
    {
    }

    public void addListener()
    {
        BasicMotionGesturesHandler motionGesturesHandler = 
            BasicMotionGesturesHandler.getInstance();
     
        motionGesturesHandler.addListenerInterface(
                this.gameMotionGestureListener);
    }
}
