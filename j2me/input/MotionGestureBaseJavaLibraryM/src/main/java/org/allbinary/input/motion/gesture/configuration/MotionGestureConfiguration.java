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
package org.allbinary.input.motion.gesture.configuration;

import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.input.motion.gesture.MotionGestureToMotionGestureActionAssociation;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class MotionGestureConfiguration
{
    private boolean diagonalMotionGestureAllowed = true;
    private int diagonalTolerance = 7;
    private int minimumMotionGesture = 9;
    private String button = StringUtil.getInstance().EMPTY_STRING;
    private BasicColor color = BasicColorFactory.getInstance().BLUE;
    private boolean executingActions = true;

    private int mouseButtonMask = 0;
    
    private BasicArrayList activeCommands = new BasicArrayList();
    
    public MotionGestureConfiguration() {
    }

    public CompleteMotionGestureInputEvent getMotionGestureAction(int index) {
        if (index >= activeCommands.size()) {
            return CompleteMotionGestureInputEvent.NULL_COMPLETE_MOTION_GESTURE_INPUT_EVENT;
        }
        return (CompleteMotionGestureInputEvent) activeCommands.objectArray[index];
    }
    
    public void addMotionGestureAction(BasicArrayList list, CompleteMotionGestureInputEvent commandAction) {
        activeCommands.add(new MotionGestureToMotionGestureActionAssociation(list, commandAction));
    }

    public void clearMotionGestureAction() {
        activeCommands.clear();
    }

    /*
    public Collection getMotionGestureActionsClone() {
        return (Collection) activeCommands.clone();
    }
    */
    
    /*
    public void setGestureActionAssociation(int index, BasicArrayList list, BaseInputActionProcessor commandAction) {
        activeCommands.set(index, new MotionGestureToMotionGestureActionAssociation(list, commandAction));
    }
    */
    
    public BasicArrayList getAssociateCommandActionsList() {
        return activeCommands;
    }
    
    public boolean isDiagonalMotionGestureAllowed() {
        return diagonalMotionGestureAllowed;
    }

    public boolean isExecutingActionsAllowed() {
        return executingActions;
    }
    
    public void setExecutingActionsAllowed(boolean executingActionAllowed) {
        this.executingActions = executingActionAllowed;
    }
    
    public void setDiagonalMotionGestureAllowed(boolean diagonalMotionGestureAllowed) {
        this.diagonalMotionGestureAllowed = diagonalMotionGestureAllowed;
    }

    public int getDiagonalTolerance() {
        return diagonalTolerance;
    }

    public void setDiagonalTolerance(int diagonalTolerance) {
        this.diagonalTolerance = diagonalTolerance;
    }

    public int getMinimumMotionGesture() {
        return minimumMotionGesture;
    }

    public void setMinimumMotionGesture(int minimumMotionGesture) {
        this.minimumMotionGesture = minimumMotionGesture;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public BasicColor getColor() {
        return color;
    }

    public void setColor(BasicColor color) {
        this.color = color;
    }

    public int getMouseButtonMask() {
        return mouseButtonMask;
    }

    public void setMouseButtonMask(int mouseButtonMask) {
        this.mouseButtonMask = mouseButtonMask;
    }

    /*
    public boolean setCommandsActions(Collection tmp) {
        if (!(tmp instanceof BasicArrayList))
            return false;
        this.activeCommands = (BasicArrayList) ((BasicArrayList) tmp).clone();
        return true;
    }
    */
}
