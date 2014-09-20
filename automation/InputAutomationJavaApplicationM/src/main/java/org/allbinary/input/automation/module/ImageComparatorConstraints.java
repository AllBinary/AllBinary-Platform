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

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.collision.RectangleCollisionUtil;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import java.awt.image.BufferedImage;

abstract public class ImageComparatorConstraints
    implements ImageComparatorConstraintsInterface
{
    private Vector avoidVector;
    
    private int maxNonMatchingPixelDeltas;
    
    private int doImageComparisonEveryNthFrame;
    
    public ImageComparatorConstraints(
        int doImageComparisonEveryNthFrame)
    {
        this.doImageComparisonEveryNthFrame = doImageComparisonEveryNthFrame;
        this.setAvoidVector(new Vector());
    }
    
    public Vector getAvoidVector()
    {
        return avoidVector;
    }
    
    public boolean isCollisionWithAvoidRectangles(Rectangle rectangle)
    {
        boolean isCollsionWithAvoidRectangles = false;
        
        Iterator iterator = this.getAvoidVector().iterator();
        while(iterator.hasNext())
        {
            Rectangle avoidRectangle = (Rectangle) iterator.next();
            if(RectangleCollisionUtil.isCollision(avoidRectangle, rectangle))
            {
                //LogUtil.put(new Log(
                // rectangle + " collided with " + avoidRectangle, this, "isCollisionWithAvoidRectangles"));
                isCollsionWithAvoidRectangles = true;
            }
        }
        return isCollsionWithAvoidRectangles;
    }
    
    public boolean isCollisionWithAvoidRectangles(Point point)
    {
        boolean isCollsionWithAvoidRectangles = false;
        
        Iterator iterator = this.getAvoidVector().iterator();
        while(iterator.hasNext())
        {
            Rectangle avoidRectangle = (Rectangle) iterator.next();
            if(RectangleCollisionUtil.isCollision(avoidRectangle, point))
            {
                //LogUtil.put(new Log(
                // rectangle + " collided with " + avoidRectangle, this, "isCollisionWithAvoidRectangles"));
                isCollsionWithAvoidRectangles = true;
                break;
            }
        }
        return isCollsionWithAvoidRectangles;
    }
    
    public void setAvoidVector(Vector avoidVector)
    {
        this.avoidVector = avoidVector;
    }
    
    public int getMaxNonMatchingPixelDeltas()
    {
        return maxNonMatchingPixelDeltas;
    }
    
    public void setMaxNonMatchingPixelDeltas(int maxNonMatchingPixelDeltas)
    {
        this.maxNonMatchingPixelDeltas = maxNonMatchingPixelDeltas;
    }
    
    public boolean isFrameAllowed(int frame)
    {
        int remainder = ((frame + 1) % this.doImageComparisonEveryNthFrame);
        LogUtil.put(new Log(" Frame: " + frame + " remainder: " + remainder + " this.doImageComparisonEveryNthFrame: " + this.doImageComparisonEveryNthFrame, this, "isCollisionWithAvoidRectangles"));
        
        if(remainder == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    abstract public boolean isImageValid(BufferedImage bufferedImage) throws Exception;
    
    public void log()
    {
        Iterator iterator = this.getAvoidVector().iterator();
        while(iterator.hasNext())
        {
            Rectangle avoidRectangle = (Rectangle) iterator.next();
            LogUtil.put(new Log(
                "Avoid Rectangle: " + avoidRectangle, this, "log"));
        }
    }
}
