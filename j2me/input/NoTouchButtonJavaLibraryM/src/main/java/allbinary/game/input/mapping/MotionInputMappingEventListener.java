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
package allbinary.game.input.mapping;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.game.input.mapping.event.InputMappingEvent;
import allbinary.game.input.mapping.event.InputMappingEventListenerInterface;
//import allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEventFactory;
//import allbinary.input.motion.button.BasicTouchInputFactory;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class MotionInputMappingEventListener implements InputMappingEventListenerInterface
{
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    public void onInputMappingEvent(InputMappingEvent inputMappingEvent) throws Exception
    {
        //GameKeyCompleteMotionGestureInputEventFactory.getInstance().updateAll();
        //BasicTouchInputFactory.getInstance().updateAll(inputMappingEvent.getInputToGameKeyMapping());
    }
}
