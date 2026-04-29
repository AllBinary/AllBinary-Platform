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
import org.allbinary.game.input.GameInputProcessor;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.analog.AnalogLocationInput;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.graphics.CustomGPoint;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class DirectionalAnalogLocationInputProcessor
        extends AnalogLocationInputProcessor
        implements GameKeyEventSourceInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final GameInputProcessor[] inputProcessorArray;

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

    private GameKeyEvent leftGameKeyEvent = GameKeyEvent.NONE;
    private GameKeyEvent rightGameKeyEvent = GameKeyEvent.NONE;
    private GameKeyEvent upGameKeyEvent = GameKeyEvent.NONE;
    private GameKeyEvent downGameKeyEvent = GameKeyEvent.NONE;
    
    private GameKeyEvent leftTriggerGameKeyEvent = GameKeyEvent.NONE;
    private GameKeyEvent rightTriggerGameKeyEvent = GameKeyEvent.NONE;
    
    public DirectionalAnalogLocationInputProcessor(GameInputProcessor[] inputProcessorArray) {
        this.inputProcessorArray = inputProcessorArray;

        try {
            this.leftGameKeyEvent = GameKeyEventFactory.getInstance().getInstanceForInput(this, this.gameKeyFactory.LEFT);
            this.rightGameKeyEvent = GameKeyEventFactory.getInstance().getInstanceForInput(this, this.gameKeyFactory.RIGHT);
            this.upGameKeyEvent = GameKeyEventFactory.getInstance().getInstanceForInput(this, this.gameKeyFactory.UP);
            this.downGameKeyEvent = GameKeyEventFactory.getInstance().getInstanceForInput(this, this.gameKeyFactory.DOWN);
            
            this.leftTriggerGameKeyEvent = GameKeyEventFactory.getInstance().getInstanceForInput(this, this.gameKeyFactory.KEY_NUM0);
            this.rightTriggerGameKeyEvent = GameKeyEventFactory.getInstance().getInstanceForInput(this, this.gameKeyFactory.KEY_NUM5);
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    //private final String RIGHT_TRIGGER_VALUE = "Right Trigger Value: ";
    //private final String LEFT_TRIGGER_VALUE = "Left Trigger Value: ";    
    
    @Override
    public void process(AllBinaryLayerManager allbinaryLayerManager, AnalogLocationInput analogLocationInput) {
        try {
            CustomGPoint customGPoint;
            customGPoint = analogLocationInput.getCustomGPoint();

            int x = customGPoint.getX();
            int y = customGPoint.getY();

            int leftTrigger = analogLocationInput.getLeftTrigger();
            int rightTrigger = analogLocationInput.getRightTrigger();

            if (x < 0) {
                this.inputProcessorArray[this.leftGameKeyEvent.getKey()].processAnalog(allbinaryLayerManager, this.leftGameKeyEvent, x);
            } else if (x > 0) {
                this.inputProcessorArray[this.rightGameKeyEvent.getKey()].processAnalog(allbinaryLayerManager, this.rightGameKeyEvent, x);
            }

            if (y < 0) {
                this.inputProcessorArray[this.downGameKeyEvent.getKey()].processAnalog(allbinaryLayerManager, this.downGameKeyEvent, y);
            } else if (y > 0) {
                this.inputProcessorArray[this.upGameKeyEvent.getKey()].processAnalog(allbinaryLayerManager, this.upGameKeyEvent, y);
            }
            
            //this.logUtil.putF(RIGHT_TRIGGER_VALUE + rightTrigger, this, commonStrings.PROCESS);
            //this.logUtil.putF(LEFT_TRIGGER_VALUE + leftTrigger, this, commonStrings.PROCESS);
            
            if (leftTrigger > 0) {
                this.inputProcessorArray[this.leftTriggerGameKeyEvent.getKey()].processAnalog(allbinaryLayerManager, this.leftTriggerGameKeyEvent, leftTrigger);
            }            

            if (rightTrigger > 0) {
                this.inputProcessorArray[this.rightTriggerGameKeyEvent.getKey()].processAnalog(allbinaryLayerManager, this.rightTriggerGameKeyEvent, rightTrigger);
            }            
            
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put("Unable to process analog input", this, commonStrings.PROCESS, e);
        }
    }

    @Override
    public int getSourceId() {
        return 0;
    }
}
