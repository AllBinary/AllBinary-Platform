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
package allbinary.input.motion.button;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.CompleteMotionGestureInputEvent;
import allbinary.game.input.CompleteMotionGestureInputEventHandler;
import allbinary.game.input.CompleteMotionGestureInputEventListenerInterface;
import allbinary.game.input.GameKey;
import allbinary.game.input.mapping.InputMappingInterface;
import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.paint.ProcessPaintable;
import allbinary.input.motion.gesture.MotionGestureInput;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import allbinary.time.TimeDelayHelper;

public class TouchButtonsPaintableComposite extends ProcessPaintable
implements CompleteMotionGestureInputEventListenerInterface
{
    private InputMappingInterface inputMappingInterface;
    
    private MotionGestureInput previousMotionGestureInput;
    protected TouchButtonsMappingPaintable touchButtonsPaintable;

    private TimeDelayHelper timeHelper = new TimeDelayHelper(250);
    private boolean released = true;
    
    public TouchButtonsPaintableComposite(InputMappingInterface inputMappingInterface, BasicColor basicColor)
    {
        this.inputMappingInterface = inputMappingInterface;
        
        CompleteMotionGestureInputEventHandler.getInstance().addListener(
                this);
     
        this.touchButtonsPaintable = new TouchButtonsMappingPaintable(
                basicColor);
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    private final String METHOD_NAME = "onCompleteMotionGestureInputEvent";
    private final String RELEASE = "Ignoring: MotionGestureInput Release";
    private final String FAST_REPEAT = "Ignoring: MotionGestureInput Repeated To Quickly";
    private final String IGNORE = "Ignoring: Until Released";
    public void onCompleteMotionGestureInputEvent(
            CompleteMotionGestureInputEvent completeMotionGestureInputEvent)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onCompleteMotionGestureInputEvent"));
                        
            GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent = 
                (GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent;
            
            GameKey gameKey = gameKeyCompleteMotionGestureInputEvent.getGameKey();
         
            MotionGestureInput motionGestureInput = 
                completeMotionGestureInputEvent.getMotionGestureInput();

            //Ignore Release only motionGesture
            if(motionGestureInput == TouchMotionGestureFactory.getInstance().RELEASED)
            {
                LogUtil.put(LogFactory.getInstance(RELEASE, this, METHOD_NAME));
                released = true;
                return;
            }
                
            //When a motionGesture is repeated quickly change is never painted
            if(previousMotionGestureInput == motionGestureInput && !this.timeHelper.isTime())
            {
                LogUtil.put(LogFactory.getInstance(FAST_REPEAT, this, METHOD_NAME));
                return;
            }

            //if(motionGestureInput != TouchMotionGestureFactory.getInstance().TOUCH && !released)
            if(!released)
            {
                LogUtil.put(LogFactory.getInstance(IGNORE, this, METHOD_NAME));
                return;
            }
            
            released = false;
            
            LogUtil.put(LogFactory.getInstance("GameKey: " + gameKey + " MotionGestureInput: " + motionGestureInput, this, METHOD_NAME));
            
            this.inputMappingInterface.process(gameKey, motionGestureInput);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, METHOD_NAME, e));
        }
    }

    public void process()
    {
        CompleteMotionGestureInputEventHandler.getInstance().removeListener(this);
    }
}
