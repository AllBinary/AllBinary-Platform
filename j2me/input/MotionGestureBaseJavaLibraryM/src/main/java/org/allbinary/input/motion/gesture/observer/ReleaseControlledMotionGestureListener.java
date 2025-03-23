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

import org.allbinary.util.BasicArrayList;


import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;

/**
 * This MotionGestureEventListener creates a list of gestures from press 
 * to release and then sends the list as a complete gesture.
 *  
 * @author user
 *
 */
public class ReleaseControlledMotionGestureListener implements MotionGestureEventListener
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private MotionGestureInput currentMotionGesture = TouchMotionGestureFactory.getInstance().NO_MOTION;
    private boolean isMouseGestureOccurring = false;

    private BasicArrayList motionGestureCollection = new BasicArrayList();
    private CompleteMotionGestureListenerInterface signed;

    public ReleaseControlledMotionGestureListener(CompleteMotionGestureListenerInterface signed)
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));
        this.signed = signed;
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }
    
    public void onUpMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onDownMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onLeftMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onRightMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onDiagonalDownRightMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onDiagonalDownLeftMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onDiagonalUpRightMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onDiagonalUpLeftMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    public void onPressedMotionGestureEvent(MotionGestureEvent ev)
    {
        isMouseGestureOccurring = true;
        currentMotionGesture = TouchMotionGestureFactory.getInstance().NO_MOTION;
        this.onMotionGestureEvent(ev);
    }
    
    public void released(MotionGestureEvent ev)
    {
        try
        {
            if (isMouseGestureOccurring == false)
                return;
            isMouseGestureOccurring = false;
            
            //LogUtil.put(LogFactory.getInstance(commonStrings.START_LABEL).append(ev.getMotionGesture(), this, "release"));
            signed.onMotionGestureCompleted(motionGestureCollection);
            //List is only compared and not referenced again so it is safe just to clear
            //TWB - Although this could be dangerous
            motionGestureCollection.clear();
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(StringUtil.getInstance().toString(ev.getMotionGesture())).toString(), this, "release", e));
        }
    }

    public void onMotionGestureEvent(MotionGestureEvent ev)
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START_LABEL).append(ev.getMotionGesture(), this, "onMotionGestureEvent"));

        //currentMotionGesture == TouchMotionGestureFactory.getInstance().NO_MOTION &&
        if (isMouseGestureOccurring == false)
            return;
        
        MotionGestureInput motionGestureInput = ev.getMotionGesture();
        if(motionGestureInput == TouchMotionGestureFactory.getInstance().PRESSED)
        {
            this.onPressedMotionGestureEvent(ev);
        }
        else
        if(motionGestureInput == TouchMotionGestureFactory.getInstance().RELEASED)
        {
            this.released(ev);
        }
        else
        {
            if (currentMotionGesture != motionGestureInput) {
                currentMotionGesture = motionGestureInput;
                motionGestureCollection.add(ev.getMotionGesture());
            }
        }
    }

}
