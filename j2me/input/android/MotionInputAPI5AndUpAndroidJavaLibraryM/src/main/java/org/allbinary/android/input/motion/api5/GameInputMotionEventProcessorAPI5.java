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

package org.allbinary.android.input.motion.api5;

import android.view.MotionEvent;

import org.allbinary.android.input.motion.BaseGameInputMotionEventProcessor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.ScalableListener;
import org.allbinary.input.motion.AllMotionRecognizer;
import org.allbinary.input.motion.MotionRecognizer;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;

public class GameInputMotionEventProcessorAPI5 extends
        BaseGameInputMotionEventProcessor
{

    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    public static GameInputMotionEventProcessorAPI5 getInstance()
    {
        if(GameInputMotionEventProcessorAPI5.instance == NullUtil.getInstance().NULL_OBJECT) {
            GameInputMotionEventProcessorAPI5.instance = new GameInputMotionEventProcessorAPI5();
        }

        return (GameInputMotionEventProcessorAPI5) GameInputMotionEventProcessorAPI5.instance;
    }

    protected final LogUtil logUtil = LogUtil.getInstance();

    private final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
    
    private final MotionRecognizer[] motionRecognizerArray;
    private final int TOTAL_MINUS_ONE;
            
    private GameInputMotionEventProcessorAPI5()
    {
        //PreLogUtil.put("Single-Touch API Through API 4", this, this.commonStrings.CONSTRUCTOR);

        final int size = GameInputMotionInfoAPI5.getInstance().MAX_POINTERS;

        this.motionRecognizerArray = new AllMotionRecognizer[size];

        this.TOTAL_MINUS_ONE = size;

        for (int index = 0; index < size; index++)
        {
            this.motionRecognizerArray[index] = new AllMotionRecognizer();
        }
    }

    private final boolean isValid(final int pointerId)
    {
        if (pointerId > this.TOTAL_MINUS_ONE)
        {
            String message = "Log that the pointer Ids are not what would be expected or Android has more than what I set my stuff to support pointerId: ";
            ForcedLogUtil.log(message + pointerId, this);
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void process(final MotionEvent motionEvent)
    {
        try
        {
            // motionEvent.getActionIndex()
            // motionEvent.getActionMasked()
            // motionEvent.getDeviceId()
            //this.logUtil.putF("ratio: " + displayInfo.getRatio(), this, this.commonStrings.PROCESS);

            // 0 down, 1 up, 2 drag
            // this.logUtil.putF(this.commonStrings.START_LABEL + "motionEvent: " +
            // motionEvent.getAction() +
            // " x: " + motionEvent.getX() + " y: " + motionEvent.getY(), this,
            // commonStrings.PROCESS);

            final int action = motionEvent.getAction();

            int total = this.motionRecognizerArray.length;

            if (total > motionEvent.getPointerCount())
            {
                total = motionEvent.getPointerCount();
            }
            else
            {
                String message = "Log that Android has more pointers than I have expected so increase it motionEvent.getPointerCount(): ";
                ForcedLogUtil.log(message + motionEvent.getPointerCount(), this);
            }

            final int totalMinusOne = total - 1;

            /*
            for(int pointerIndex = totalMinusOne; pointerIndex >= 0; pointerIndex--)
            {
                int pointerId = motionEvent.getPointerId(pointerIndex);

                if(this.isValid(pointerId))
                {
                    int x = (int) (motionEvent.getX(pointerIndex) - this.displayInfo.getLeft());
                    int y = (int) (motionEvent.getY(pointerIndex) - this.displayInfo.getTop());
                    //int x = (int) ((motionEvent.getX(pointerIndex) - displayInfo.getLeft()) * displayInfo.getRatio()) ;
                    //int y = (int) ((motionEvent.getY(pointerIndex) - displayInfo.getTop()) * displayInfo.getRatio());
                    //int x = (int) motionEvent.getX(pointerIndex);
                    //int y = (int) motionEvent.getY(pointerIndex);
                    
                    this.logUtil.putF(
                            new StringBuilder().append(commonStrings.START_LABEL)
                                    .append("motionEvent: ").append(motionEvent.getAction()).append(" x: ").append(x).append(" y: ").append(y).toString(), 
                            this, this.commonStrings.PROCESS);
                    
                }
            }
            */
            
            if (action == MotionEvent.ACTION_DOWN)
            {
                for (int pointerIndex = totalMinusOne; pointerIndex >= 0; pointerIndex--)
                {
                    final int pointerId = motionEvent.getPointerId(pointerIndex);

                    if (this.isValid(pointerId))
                    {
//                        this.logUtil.putF(
//                                new StringBuilder().append(commonStrings.START_LABEL)
//                                        .append("processStartMotionEvent - motionEvent: ").append(motionEvent.getAction())
//                                        .append(" x: ").append(motionEvent.getX(pointerIndex))
//                                        .append(" y: ").append(motionEvent.getY(pointerIndex))
//                                        .append(" left: ").append(displayInfo.getLeft())
//                                        .append(" top: ").append(displayInfo.getTop())
//                                        .append(" ratio: ").append(displayInfo.getRatio())
//                                        .toString(), 
//                                this, this.commonStrings.PROCESS);
                        
                        final ScalableListener scalableListener = this.displayInfo.getScalableListener();
                        final int x = (int) ((motionEvent.getX(pointerIndex) - this.displayInfo.getLeft()) / scalableListener.getRatio(this.displayInfo.getRatio()));
                        final int y = (int) ((motionEvent.getY(pointerIndex) - this.displayInfo.getTop())  / scalableListener.getRatio(this.displayInfo.getRatio()));

                        //final int x = (int) ((motionEvent.getX(pointerIndex) - displayInfo.getLeft()));
                        //final int y = (int) ((motionEvent.getY(pointerIndex) - displayInfo.getTop()));
                        
//                        this.logUtil.putF(
//                                new StringBuilder().append(commonStrings.START_LABEL)
//                                        .append("processStartMotionEvent - motionEvent adjusted: ").append(motionEvent.getAction()).append(" x: ").append(x).append(" y: ").append(y).toString(), 
//                                this, this.commonStrings.PROCESS);
                        
                        this.motionRecognizerArray[pointerId]
                                .processStartMotionEvent(x, y, motionEvent.getDeviceId(), 0);
                    }
                }
            }
            else if (action == MotionEvent.ACTION_UP
                    || action == MotionEvent.ACTION_CANCEL)
            {
                for (int pointerIndex = totalMinusOne; pointerIndex >= 0; pointerIndex--)
                {
                    final int pointerId = motionEvent.getPointerId(pointerIndex);

                    if (this.isValid(pointerId))
                    {
//                        this.logUtil.putF(
//                                new StringBuilder().append(commonStrings.START_LABEL)
//                                        .append("processEndMotionEvent - motionEvent: ").append(motionEvent.getAction())
//                                        .append(" x: ").append(motionEvent.getX(pointerIndex))
//                                        .append(" y: ").append(motionEvent.getY(pointerIndex))
//                                        .append(" left: ").append(displayInfo.getLeft())
//                                        .append(" top: ").append(displayInfo.getTop())
//                                        .append(" ratio: ").append(displayInfo.getRatio())
//                                        .toString(), 
//                                this, this.commonStrings.PROCESS);
                        
                        final ScalableListener scalableListener = this.displayInfo.getScalableListener();
                        final int x = (int) ((motionEvent.getX(pointerIndex) - this.displayInfo.getLeft()) / scalableListener.getRatio(this.displayInfo.getRatio()));
                        final int y = (int) ((motionEvent.getY(pointerIndex) - this.displayInfo.getTop())  / scalableListener.getRatio(this.displayInfo.getRatio()));
                        
//                        this.logUtil.putF(
//                                new StringBuilder().append(commonStrings.START_LABEL)
//                                        .append("processEndMotionEvent - motionEvent adjusted: ").append(motionEvent.getAction()).append(" x: ").append(x).append(" y: ").append(y).toString(), 
//                                this, this.commonStrings.PROCESS);
                        
                        this.motionRecognizerArray[pointerId]
                                .processEndMotionEvent(x, y, motionEvent.getDeviceId(), 0);
                    }
                }
            }
            else if (action == MotionEvent.ACTION_MOVE)
            {
                for (int pointerIndex = totalMinusOne; pointerIndex >= 0; pointerIndex--)
                {
                    final int pointerId = motionEvent.getPointerId(pointerIndex);

                    if (this.isValid(pointerId))
                    {
                        final ScalableListener scalableListener = this.displayInfo.getScalableListener();
                        final int x = (int) ((motionEvent.getX(pointerIndex) - this.displayInfo.getLeft()) / scalableListener.getRatio(this.displayInfo.getRatio()));
                        final int y = (int) ((motionEvent.getY(pointerIndex) - this.displayInfo.getTop())  / scalableListener.getRatio(this.displayInfo.getRatio()));
                        
                        this.motionRecognizerArray[pointerId]
                                .processDraggedMotionEvent(x, y, motionEvent.getDeviceId(), 0);
                    }
                }
            }

            // MotionEvent.ACTION_OUTSIDE

            // motionEventsToMotionGestureListener.onMotionEvent(motionEvent);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e);
        }
    }
}
