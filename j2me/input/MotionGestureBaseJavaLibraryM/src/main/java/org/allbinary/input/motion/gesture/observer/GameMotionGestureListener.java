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

import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class GameMotionGestureListener implements MotionGestureEventListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private BasicArrayList motionGestureCollection = new BasicArrayList();
    private BasicArrayList touchGestureCollection = new BasicArrayList();
    private CompleteMotionGestureListenerInterface signed;

    public GameMotionGestureListener(
            CompleteMotionGestureListenerInterface signed)
    {
        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        this.signed = signed;
    }

    @Override
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }

    @Override
    public void onUpMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onDownMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onLeftMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onRightMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onDiagonalDownRightMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onDiagonalDownLeftMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onDiagonalUpRightMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onDiagonalUpLeftMotionGestureEvent(MotionGestureEvent ev)
    {
        onMotionGestureEvent(ev);
    }

    @Override
    public void onPressedMotionGestureEvent(MotionGestureEvent ev)
    {
        touchGestureCollection.add(ev.getMotionGesture());
    }
    
    @Override
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
           final StringMaker stringBuffer = new StringMaker();

           stringBuffer.append(commonStrings.EXCEPTION_LABEL);
           stringBuffer.append(StringUtil.getInstance().toString(ev.getMotionGesture()));

           logUtil.put(stringBuffer.toString(), this, "release", e);
        }
    }

    @Override
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

           stringBuffer.append(commonStrings.EXCEPTION_LABEL);
           stringBuffer.append(StringUtil.getInstance().toString(ev.getMotionGesture()));

            logUtil.put(stringBuffer.toString(), this, "onMotionGestureEvent", e);
        }
    }
}
