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

import org.allbinary.logic.basic.NotImplemented;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

public class GameMotionGestureListener implements MotionGestureEventListener
{
    private BasicArrayList motionGestureCollection = new BasicArrayList();
    private BasicArrayList touchGestureCollection = new BasicArrayList();
    private CompleteMotionGestureListenerInterface signed;

    public GameMotionGestureListener(
            CompleteMotionGestureListenerInterface signed)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));
        this.signed = signed;
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(NotImplemented.NAME, this);
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
        touchGestureCollection.add(ev.getMotionGesture());
    }
    
    public void released(MotionGestureEvent ev)
    {
        try
        {
            //if(touchGestureCollection.size() == 1)
            //{
               touchGestureCollection.add(ev.getMotionGesture());
               signed.onMotionGestureCompleted(touchGestureCollection);
               touchGestureCollection.clear();
            //}
        }
        catch (Exception e)
        {
           StringMaker stringBuffer = new StringMaker();

           stringBuffer.append(CommonStrings.getInstance().EXCEPTION_LABEL);
           stringBuffer.append(ev.getMotionGesture());

           LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "release", e));
        }
    }

    public void onMotionGestureEvent(MotionGestureEvent ev)
    {
        try
        {
            MotionGestureInput motionGestureInput = ev.getMotionGesture();
            if (motionGestureInput == TouchMotionGestureFactory.getInstance().PRESSED)
            {
                this.onPressedMotionGestureEvent(ev);
            }
            else if (motionGestureInput == TouchMotionGestureFactory.getInstance().RELEASED)
            {
                this.released(ev);
            }
            else
            {
                motionGestureCollection.add(motionGestureInput);
                signed.onMotionGestureCompleted(motionGestureCollection);
                motionGestureCollection.clear();
                touchGestureCollection.clear();
            }
        }
        catch (Exception e)
        {
           StringMaker stringBuffer = new StringMaker();

           stringBuffer.append(CommonStrings.getInstance().EXCEPTION_LABEL);
           stringBuffer.append(ev.getMotionGesture());

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "onMotionGestureEvent", e));
        }
    }
}
