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
package org.allbinary.media.image.comparison.motion;

import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

public class MotionRectanglesResultsEvent
    extends AllBinaryEventObject
{
    private final Long frame;
    
    private MotionRectangles motionRectangles;
    
    public MotionRectanglesResultsEvent(
        Object object, Long frame, MotionRectangles motionRectangles)
    {
        super(object);
        
        this.frame = frame;
        this.setMotionRectangles(motionRectangles);
    }
    
    public MotionRectangles getMotionRectangles()
    {
        return motionRectangles;
    }
    
    public void setMotionRectangles(MotionRectangles motionRectangles)
    {
        this.motionRectangles = motionRectangles;
    }
    
    public Long getFrame()
    {
        return frame;
    }
}