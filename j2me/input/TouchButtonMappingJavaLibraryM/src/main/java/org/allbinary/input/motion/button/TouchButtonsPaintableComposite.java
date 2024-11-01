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
package org.allbinary.input.motion.button;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.game.input.CompleteMotionGestureInputEventHandler;
import org.allbinary.game.input.CompleteMotionGestureInputEventListenerInterface;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.mapping.InputMappingInterface;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.paint.ProcessPaintable;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.time.TimeDelayHelper;

public class TouchButtonsPaintableComposite extends ProcessPaintable
implements CompleteMotionGestureInputEventListenerInterface
{
    private InputMappingInterface inputMappingInterface;
    
    private MotionGestureInput previousMotionGestureInput;
    protected TouchButtonsMappingPaintable touchButtonsPaintable;

    private TimeDelayHelper timeHelper = new TimeDelayHelper(250);
    private boolean released = true;
    
    public TouchButtonsPaintableComposite(final InputMappingInterface inputMappingInterface, final BasicColor basicColor)
    {
        this.inputMappingInterface = inputMappingInterface;
        
        CompleteMotionGestureInputEventHandler.getInstance().addListener(
                this);
     
        this.touchButtonsPaintable = new TouchButtonsMappingPaintable(
                basicColor);
    }
    
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    private final String METHOD_NAME = "onCompleteMotionGestureInputEvent";
    private final String RELEASE = "Ignoring: MotionGestureInput Release";
    private final String FAST_REPEAT = "Ignoring: MotionGestureInput Repeated To Quickly";
    private final String IGNORE = "Ignoring: Until Released";
    public void onCompleteMotionGestureInputEvent(
            final CompleteMotionGestureInputEvent completeMotionGestureInputEvent)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onCompleteMotionGestureInputEvent"));
         
            final TouchMotionGestureFactory touchMotionGestureFactory = TouchMotionGestureFactory.getInstance();

            final GameKeyCompleteMotionGestureInputEvent gameKeyCompleteMotionGestureInputEvent = 
                (GameKeyCompleteMotionGestureInputEvent) completeMotionGestureInputEvent;
            
            final GameKey gameKey = gameKeyCompleteMotionGestureInputEvent.getGameKey();
         
            final MotionGestureInput motionGestureInput = 
                completeMotionGestureInputEvent.getMotionGestureInput();

            //Ignore Release only motionGesture
            if(motionGestureInput == touchMotionGestureFactory.RELEASED)
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
            
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("GameKey: ").append(gameKey).append(" MotionGestureInput: ").append(motionGestureInput).toString(), this, METHOD_NAME));
            
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
