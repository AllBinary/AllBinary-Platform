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

import org.allbinary.game.input.motion.action.SingleKeyPressGameKeyFromMotionGestureEventListener;
import org.allbinary.input.motion.trackball.action.DownTrackballInputToGameKeyEventAction;
import org.allbinary.input.motion.trackball.action.LeftTrackballInputToGameKeyEventAction;
import org.allbinary.input.motion.trackball.action.RightTrackballInputToGameKeyEventAction;
import org.allbinary.input.motion.trackball.action.UpTrackballInputToGameKeyEventAction;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class TrackballGameInputMotionEventProcessor extends MotionEventProcessor
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static TrackballGameInputMotionEventProcessor PROCESSOR = new TrackballGameInputMotionEventProcessor();

    public static TrackballGameInputMotionEventProcessor getInstance()
    //MIDlet midlet
    {
        //PROCESSOR.setMidlet(midlet);
        return TrackballGameInputMotionEventProcessor.PROCESSOR;
    }

    private final SingleKeyPressGameKeyFromMotionGestureEventListener listener = 
        new SingleKeyPressGameKeyFromMotionGestureEventListener();
    
    private TrackballGameInputMotionEventProcessor()
    {
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
 
    @Override
    public void process(MotionEvent motionEvent)
    {
        try
        {
            //this.logUtil.putF(this.commonStrings.START_LABEL + "motionEvent: "
              //      + motionEvent.getAction() + " x: " + motionEvent.getX()
                //    + " y: " + motionEvent.getY(), this, this.commonStrings.PROCESS);

            float x = motionEvent.getX();
            float y = motionEvent.getY();
            // motionEvent.getAction();
            
            if (x < 0)
            {
                this.listener.onCompleteMotionGestureInputEvent(
                        LeftTrackballInputToGameKeyEventAction.getInstance());
            }
            else if (x > 0)
            {
                this.listener.onCompleteMotionGestureInputEvent(
                        RightTrackballInputToGameKeyEventAction.getInstance());
            }

            if (y < 0)
            {
                this.listener.onCompleteMotionGestureInputEvent(
                        UpTrackballInputToGameKeyEventAction.getInstance());
            }
            else if (y > 0)
            {
                this.listener.onCompleteMotionGestureInputEvent(
                        DownTrackballInputToGameKeyEventAction.getInstance());
            }

        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e);
        }
    }
}
