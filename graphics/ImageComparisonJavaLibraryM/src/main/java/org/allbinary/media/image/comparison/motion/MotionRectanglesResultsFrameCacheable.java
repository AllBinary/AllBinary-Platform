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

import java.awt.image.BufferedImage;

import com.abcs.logic.util.cache.CacheableInterface;

public class MotionRectanglesResultsFrameCacheable
    implements CacheableInterface
{
    private MotionRectangles motionRectangles;
    
    private Long frame;
    
    public MotionRectanglesResultsFrameCacheable(
        MotionRectangles motionRectangles, Long frame)
    {
        this.frame = frame;
        this.setMotionRectangles(motionRectangles);
    }
    
    public Object getKey()
    {
        return this.frame;
    }    

    public MotionRectangles getMotionRectangles()
    {
        return motionRectangles;
    }

    public void setMotionRectangles(MotionRectangles motionRectangles)
    {
        this.motionRectangles = motionRectangles;
    }
    
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(" Frame: ");
        stringBuffer.append(this.getKey());
        return stringBuffer.toString();
    }
}
