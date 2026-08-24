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

import jsinterop.annotations.JsType;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.TsUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.string.CommonLabels;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class MotionGestureEvent extends AllBinaryEventObject {
    
    private MotionGestureInput motionGesture = TouchMotionGestureFactory.getInstance().NO_MOTION;

    private final int id;
    
    private GPoint previousPoint = PointFactory.getInstance().ZERO_ZERO;
    private GPoint currentPoint = PointFactory.getInstance().ZERO_ZERO;
    
    @JsConstructor
    public MotionGestureEvent(Object source, int id, MotionGestureInput motionGesture) {
        super(source);
        
        this.id = id;
        this.setMotionGesture(motionGesture);
    }

    @JsMethod
    public MotionGestureInput getMotionGesture() {
        return this.motionGesture;
    }

    @JsMethod
    public void setMotionGesture(MotionGestureInput motionGesture) {
        this.motionGesture = motionGesture;
    }

    /**
     * @return the previousPoint
     */
    @JsMethod
    public GPoint getPreviousPoint()
    {
        return this.previousPoint;
    }

    /**
     * @param previousPoint the previousPoint to set
     */
    @JsMethod
    public void setPreviousPoint(GPoint previousPoint)
    {
        this.previousPoint = previousPoint;
    }

    /**
     * @return the currentPoint
     */
    @JsMethod
    public GPoint getCurrentPoint()
    {
        return this.currentPoint;
    }

    /**
     * @param currentPoint the currentPoint to set
     */
    @JsMethod
    public void setCurrentPoint(GPoint currentPoint)
    {
        this.currentPoint = currentPoint;
    }
    
    private static final String CURRENT = CommonLabels.getInstance().CURRENT;
    private static final String PREVIOUS = " Previous: ";
    private static final String HASHCODE = " hashcode: ";

    @JsMethod
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();
        final StringUtil stringUtil = StringUtil.getInstance();
        
        stringBuffer.append(stringUtil.toString(this.motionGesture));
        stringBuffer.append(MotionGestureEvent.CURRENT);
        stringBuffer.append(stringUtil.toString(this.currentPoint));
        stringBuffer.append(MotionGestureEvent.PREVIOUS);
        stringBuffer.append(stringUtil.toString(this.previousPoint));
        stringBuffer.append(MotionGestureEvent.HASHCODE);
        stringBuffer.appendint(TsUtil.getInstance().hashCode(this));
        return stringBuffer.toString();
    }

    @JsMethod
    public int getId()
    {
        return this.id;
    }

}
