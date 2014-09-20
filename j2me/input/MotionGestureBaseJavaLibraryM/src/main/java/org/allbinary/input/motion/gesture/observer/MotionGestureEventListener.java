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


public interface MotionGestureEventListener extends BaseMotionGestureEventListener {
    
    void onPressedMotionGestureEvent(MotionGestureEvent ev);
    void onUpMotionGestureEvent(MotionGestureEvent ev);
    void onDownMotionGestureEvent(MotionGestureEvent ev);
    void onLeftMotionGestureEvent(MotionGestureEvent ev);
    void onRightMotionGestureEvent(MotionGestureEvent ev);
    void onDiagonalDownRightMotionGestureEvent(MotionGestureEvent ev);
    void onDiagonalDownLeftMotionGestureEvent(MotionGestureEvent ev);
    void onDiagonalUpRightMotionGestureEvent(MotionGestureEvent ev);
    void onDiagonalUpLeftMotionGestureEvent(MotionGestureEvent ev);
    void released(MotionGestureEvent ev) throws Exception;
}
