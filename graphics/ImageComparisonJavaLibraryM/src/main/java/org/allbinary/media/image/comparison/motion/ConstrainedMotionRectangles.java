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

import java.awt.Rectangle;

import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;
import java.awt.image.BufferedImage;
import org.allbinary.logic.communication.log.LogFactory;

public class ConstrainedMotionRectangles extends MotionRectangles
{
    private static final String NAME = "constrainedMotionRectangles_";
    
    private MotionRectangleConstraintsInterface motionRectangleConstraintsInterface;
    
    //GenerateImageWithBoundingBoxesAroundMovingArtifacts
    public ConstrainedMotionRectangles(
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface,
        MotionRectangles motionRectangles)
        throws Exception
    {
        super(NAME, motionRectangles.getImageComparisonResult());
        
        this.setMotionRectangleConstraintsInterface(motionRectangleConstraintsInterface);

        //LogUtil.put(LogFactory.getInstance(
          //  "After - Number Of Rectangles: " + this.getVector().size(), this, "Constructor"));
    }
    
    public void applyMotionRectangleConstraints(MotionRectangles motionRectangles)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start - Size Before: " + motionRectangles.getVector().size(),
            this, "applyMotionRectangleConstraints"));

        BufferedImage bufferedImageArray[] =
            motionRectangles.getImageComparisonResult().getBufferedImages();

        BufferedImage bufferedImage = bufferedImageArray[1];
        
        Vector vector = new Vector();
        
        Iterator iterator = motionRectangles.getVector().iterator();
        
        while(iterator.hasNext())
        {
            MotionRectangle motionRectangle =
                (MotionRectangle) iterator.next();
            
            Rectangle rectangle =
                motionRectangle.getRectangle();
            
            boolean isTooSmall =
                this.getMotionRectangleConstraintsInterface().isTooSmall(
                rectangle);
            
            if(isTooSmall)
            {
                //LogUtil.put(LogFactory.getInstance("Target is to small: " +
                  //  rectangle, this, "getTargetableRectangles"));
                continue;
            }

            boolean isAreaTooSmall =
                this.getMotionRectangleConstraintsInterface().isAreaTooSmall(
                rectangle);
            
            if(isAreaTooSmall)
            {
                //LogUtil.put(LogFactory.getInstance("Target area is to small: " +
                  //  rectangle, this, "getTargetableRectangles"));
                continue;
            }
            
            boolean isTooBig =
                this.getMotionRectangleConstraintsInterface().isTooBig(
                rectangle);
            
            if(isTooBig)
            {
                //LogUtil.put(LogFactory.getInstance("Target is to big: " +
                  //  rectangle, this, "getTargetableRectangles"));
                continue;
            }
        
            boolean isValid =
                this.getMotionRectangleConstraintsInterface().isValid(
                this.getImageComparisonResult().getFrameTwo(),
                bufferedImage, motionRectangle.getRectangle());

            if(!isValid)
            {
                continue;
            }
            
            if(!isTooSmall && !isAreaTooSmall && !isTooBig && isValid)
               vector.add(motionRectangle);
        }
        this.setVector(vector);
    }
    
    public MotionRectangleConstraintsInterface getMotionRectangleConstraintsInterface()
    {
        return motionRectangleConstraintsInterface;
    }

    public void setMotionRectangleConstraintsInterface(MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
    {
        this.motionRectangleConstraintsInterface = motionRectangleConstraintsInterface;
    }
    
}
