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

import org.allbinary.game.input.CompleteMotionGestureInputEventHandler;
import org.allbinary.game.input.motion.action.GameKeyCompleteMotionGestureInputEvent;
import org.allbinary.game.input.motion.action.GameKeyFromMotionGestureEventListener;
import org.allbinary.input.motion.gesture.MotionGestureToMotionGestureActionAssociation;
import org.allbinary.input.motion.gesture.configuration.MotionGestureConfiguration;
import org.allbinary.input.motion.gesture.configuration.MotionGestureConfigurationFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class ResolveCompleteMotionGestureListener implements CompleteMotionGestureListenerInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    public ResolveCompleteMotionGestureListener() {
        
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put("MotionGesture to CompleteMotionGesture Reciever", this, commonStrings.CONSTRUCTOR);

        CompleteMotionGestureInputEventHandler.getInstance().addListener(
                //SingleKeyPress
                new GameKeyFromMotionGestureEventListener());
    }

    public void onMotionGestureCompleted(BasicArrayList list)
    throws Exception{
        
        //logUtil.put("Gesture Completed: " + list.toString(), this, "mouseGestureCompleted");
       //PreLogUtil.put("Gesture Completed: " + list.toString(), this, "mouseGestureCompleted");
        
        MotionGestureConfiguration configuration = 
            MotionGestureConfigurationFactory.getInstance();
        
        BasicArrayList commandActionsList = configuration.getAssociateCommandActionsList();
        
        int size = commandActionsList.size();
        //logUtil.put("commandActionsList.size(): " + size, this, "mouseGestureCompleted");
        
        for(int index = size - 1; index >= 0; index--) 
        {
            MotionGestureToMotionGestureActionAssociation association = 
                (MotionGestureToMotionGestureActionAssociation) 
                commandActionsList.objectArray[index];
            
            if (association.isMotionGestureArrayEquals(list) == true) {

                GameKeyCompleteMotionGestureInputEvent completeMotionGestureInputEvent = 
                    (GameKeyCompleteMotionGestureInputEvent) association.getCommandAction();
                
                //logUtil.put("Gesture Completed: " +  completeMotionGestureInputEvent.getMotionGestureInput().getName(),
                  //      this, "mouseGestureCompleted");
                CompleteMotionGestureInputEventHandler.getInstance().fireEvent(
                        completeMotionGestureInputEvent);

                break;
            }
        }
    }
    
}
