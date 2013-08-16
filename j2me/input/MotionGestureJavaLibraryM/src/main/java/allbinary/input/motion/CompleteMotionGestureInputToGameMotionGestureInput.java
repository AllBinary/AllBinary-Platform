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
package allbinary.input.motion;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;
import allbinary.input.motion.gesture.configuration.MotionGestureConfiguration;
import allbinary.input.motion.gesture.configuration.MotionGestureConfigurationFactory;
import allbinary.input.motion.touch.action.DiagonalDownLeftTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.DiagonalDownRightTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.DiagonalUpLeftTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.DiagonalUpRightTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.DownTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.LeftTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.ReleaseTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.RightTouchInputToGameKeyEventAction;
import allbinary.input.motion.touch.action.UpTouchInputToGameKeyEventAction;

public class CompleteMotionGestureInputToGameMotionGestureInput
{
   private static final CompleteMotionGestureInputToGameMotionGestureInput instance =
           new CompleteMotionGestureInputToGameMotionGestureInput();

    public static void init()
    {
        try
        {
            PreLogUtil.put("Compound Motion Gestures", instance, CommonStrings.getInstance().INIT);

            MotionGestureConfiguration motionGestureConfiguration = 
                MotionGestureConfigurationFactory.getInstance();

            BasicArrayList list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().DIAGONAL_DOWN_LEFT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalDownLeftTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().DIAGONAL_DOWN_RIGHT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalDownRightTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().DIAGONAL_UP_LEFT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalUpLeftTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().DIAGONAL_UP_RIGHT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DiagonalUpRightTouchInputToGameKeyEventAction.getInstance());
            
            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().LEFT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    LeftTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().RIGHT);
            motionGestureConfiguration.addMotionGestureAction(list,
                    RightTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().DOWN);
            motionGestureConfiguration.addMotionGestureAction(list,
                    DownTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().UP);
            motionGestureConfiguration.addMotionGestureAction(list,
                    UpTouchInputToGameKeyEventAction.getInstance());

            list = new BasicArrayList();
            list.add(TouchMotionGestureFactory.getInstance().RELEASED);
            motionGestureConfiguration.addMotionGestureAction(list,
                    ReleaseTouchInputToGameKeyEventAction.getInstance());
            
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION,
                    "CompleteMotionGestureInputToGameMotionGestureInput", CommonStrings.getInstance().INIT, e));
        }
    }
}
