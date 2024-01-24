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
package org.allbinary.input;

import org.allbinary.android.input.motion.AnalogLocationInputProcessor;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameInputProcessor;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.analog.AnalogLocationInput;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.graphics.CustomGPoint;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.string.CommonStrings;

public class DirectionalAnalogLocationInputProcessor
        extends AnalogLocationInputProcessor
        implements GameKeyEventSourceInterface {

    private final GameInputProcessor[] inputProcessorArray;

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

    private GameKeyEvent leftGameKeyEvent;
    private GameKeyEvent rightGameKeyEvent;
    private GameKeyEvent upGameKeyEvent;
    private GameKeyEvent downGameKeyEvent;
    
    private GameKeyEvent leftTriggerGameKeyEvent;
    private GameKeyEvent rightTriggerGameKeyEvent;
    
    public DirectionalAnalogLocationInputProcessor(GameInputProcessor[] inputProcessorArray) {
        this.inputProcessorArray = inputProcessorArray;

        try {
            this.leftGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.LEFT);
            this.rightGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.RIGHT);
            this.upGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.UP);
            this.downGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.DOWN);
            
            this.leftTriggerGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.KEY_NUM0);
            this.rightTriggerGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.KEY_NUM5);
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
        }
    }

    //private final String RIGHT_TRIGGER_VALUE = "Right Trigger Value: ";
    //private final String LEFT_TRIGGER_VALUE = "Left Trigger Value: ";    
    
    public void process(AllBinaryLayerManager allbinaryLayerManager, AnalogLocationInput analogLocationInput) {
        try {
            CustomGPoint customGPoint;
            customGPoint = analogLocationInput.getCustomGPoint();

            int x = customGPoint.getX();
            int y = customGPoint.getY();

            int leftTrigger = analogLocationInput.getLeftTrigger();
            int rightTrigger = analogLocationInput.getRightTrigger();

            if (x < 0) {
                inputProcessorArray[this.leftGameKeyEvent.getKey()].process(allbinaryLayerManager, this.leftGameKeyEvent, x);
            } else if (x > 0) {
                inputProcessorArray[this.rightGameKeyEvent.getKey()].process(allbinaryLayerManager, this.rightGameKeyEvent, x);
            }

            if (y < 0) {
                inputProcessorArray[this.downGameKeyEvent.getKey()].process(allbinaryLayerManager, this.downGameKeyEvent, y);
            } else if (y > 0) {
                inputProcessorArray[this.upGameKeyEvent.getKey()].process(allbinaryLayerManager, this.upGameKeyEvent, y);
            }
            
            //LogUtil.put(LogFactory.getInstance(RIGHT_TRIGGER_VALUE + rightTrigger, this, commonStrings.PROCESS));
            //LogUtil.put(LogFactory.getInstance(LEFT_TRIGGER_VALUE + leftTrigger, this, commonStrings.PROCESS));
            
            if (leftTrigger > 0) {
                inputProcessorArray[this.leftTriggerGameKeyEvent.getKey()].process(allbinaryLayerManager, this.leftTriggerGameKeyEvent, leftTrigger);
            }            

            if (rightTrigger > 0) {
                inputProcessorArray[this.rightTriggerGameKeyEvent.getKey()].process(allbinaryLayerManager, this.rightTriggerGameKeyEvent, rightTrigger);
            }            
            
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance("Unable to process analog input", this, commonStrings.PROCESS, e));
        }
    }

    public int getSourceId() {
        return 0;
    }
}
