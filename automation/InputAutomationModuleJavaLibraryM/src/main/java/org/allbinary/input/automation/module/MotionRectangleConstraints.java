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
package org.allbinary.input.automation.module;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;

public class MotionRectangleConstraints
    implements MotionRectangleConstraintsInterface
{
    private Dimension minDimension;
    private Dimension maxDimension;
    
    private int maxMotionRectangles;
    
    private int minArea;
    
    public MotionRectangleConstraints()
    {
    }
    
    public Dimension getMinDimension()
    {
        return this.minDimension;
    }
    
    public Dimension getMaxDimension()
    {
        return this.maxDimension;
    }
    
    public boolean isTooSmall(Rectangle rectangle)
    {
        /*
        logUtil.put(
            rectangle.width + " < " + this.getMinDimension().width + " || " + 
            rectangle.height + " < " + this.getMinDimension().height, this, "isTooSmall");
        */
        
        if(rectangle.width < this.getMinDimension().width ||
            rectangle.height < this.getMinDimension().height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isAreaTooSmall(Rectangle rectangle)
    {
        if(rectangle.width * rectangle.height < this.getMinArea())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isTooBig(Rectangle rectangle)
    {
        /*
        logUtil.put(
            rectangle.width + " > " + this.getMaxDimension().width + " || " + 
            rectangle.height + " > " + this.getMaxDimension().height, this, "isTooBig");
         */
        if(rectangle.width > this.getMaxDimension().width ||
            rectangle.height > this.getMaxDimension().height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isValid(Long frame, BufferedImage bufferedImage, Rectangle rectangle) throws Exception {
        throw new RuntimeException();
    }
    
    public void setMinDimension(Dimension minDimension)
    {
        this.minDimension = minDimension;
    }

    public void setMaxDimension(Dimension maxDimension)
    {
        this.maxDimension = maxDimension;
    }

    public int getMaxMotionRectangles()
    {
        return maxMotionRectangles;
    }

    public void setMaxMotionRectangles(int maxMotionRectangles)
    {
        this.maxMotionRectangles = maxMotionRectangles;
    }

    public int getMinArea()
    {
        return minArea;
    }

    public void setMinArea(int minArea)
    {
        this.minArea = minArea;
    }
}
