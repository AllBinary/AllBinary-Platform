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

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.input.motion.gesture.configuration.MotionGestureConfiguration;
import org.allbinary.input.motion.gesture.configuration.MotionGestureConfigurationFactory;
import org.allbinary.input.motion.touch.action.DiagonalDownLeftTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.DiagonalDownRightTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.DiagonalUpLeftTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.DiagonalUpRightTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.DownTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.LeftTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.ReleaseTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.RightTouchInputToGameKeyEventAction;
import org.allbinary.input.motion.touch.action.UpTouchInputToGameKeyEventAction;

public class CompleteMotionGestureInputToGameMotionGestureInput
{
   private static final CompleteMotionGestureInputToGameMotionGestureInput instance =
           new CompleteMotionGestureInputToGameMotionGestureInput();

    public static CompleteMotionGestureInputToGameMotionGestureInput getInstance() {
        return instance;
    }
   
    public void init()
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            PreLogUtil.put("Compound Motion Gestures", this, commonStrings.INIT);

            final MotionGestureConfiguration motionGestureConfiguration = 
                MotionGestureConfigurationFactory.getInstance();
            
            final TouchMotionGestureFactory touchMotionGestureFactory = 
                    TouchMotionGestureFactory.getInstance();

            BasicArrayList list = new BasicArrayList();
            list.add(touchMotionGestureFactory.DIAGONAL_DOWN_LEFT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalDownLeftTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.DIAGONAL_DOWN_RIGHT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalDownRightTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.DIAGONAL_UP_LEFT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalUpLeftTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.DIAGONAL_UP_RIGHT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalUpRightTouchInputToGameKeyEventAction.getInstance());
            
            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.LEFT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    LeftTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.RIGHT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    RightTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.DOWN);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DownTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.UP);
            motionGestureConfiguration.addMotionGestureAction(list,
                    UpTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(touchMotionGestureFactory.RELEASED);
            motionGestureConfiguration.addMotionGestureAction(list,
                    ReleaseTouchInputToGameKeyEventAction.getInstance());
            
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.INIT, e));
        }
    }
}
