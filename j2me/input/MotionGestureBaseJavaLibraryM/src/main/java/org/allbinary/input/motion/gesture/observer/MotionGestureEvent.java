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

import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.graphics.GPoint;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

public class MotionGestureEvent extends AllBinaryEventObject {
    
    private MotionGestureInput motionGesture;

    private final int id;
    
    private GPoint previousPoint;
    private GPoint currentPoint;
    
    public MotionGestureEvent(Object source, int id, MotionGestureInput motionGesture) {
        super(source);
        
        this.id = id;
        this.setMotionGesture(motionGesture);
    }

    public MotionGestureInput getMotionGesture() {
        return motionGesture;
    }

    public void setMotionGesture(MotionGestureInput motionGesture) {
        this.motionGesture = motionGesture;
    }

    /**
     * @return the previousPoint
     */
    public GPoint getPreviousPoint()
    {
        return previousPoint;
    }

    /**
     * @param previousPoint the previousPoint to set
     */
    public void setPreviousPoint(GPoint previousPoint)
    {
        this.previousPoint = previousPoint;
    }

    /**
     * @return the currentPoint
     */
    public GPoint getCurrentPoint()
    {
        return currentPoint;
    }

    /**
     * @param currentPoint the currentPoint to set
     */
    public void setCurrentPoint(GPoint currentPoint)
    {
        this.currentPoint = currentPoint;
    }
    
    private static final String CURRENT = " Current: ";
    private static final String PREVIOUS = " Previous: ";
    private static final String HASHCODE = " hashcode: ";

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(this.motionGesture);
        stringBuffer.append(CURRENT);
        stringBuffer.append(this.currentPoint);
        stringBuffer.append(PREVIOUS);
        stringBuffer.append(this.previousPoint);
        stringBuffer.append(HASHCODE);
        stringBuffer.append(this.hashCode());
        return stringBuffer.toString();
    }

    public int getId()
    {
        return id;
    }

}
