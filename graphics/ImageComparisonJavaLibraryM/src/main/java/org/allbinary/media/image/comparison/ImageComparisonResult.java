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
package org.allbinary.media.image.comparison;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.media.image.comparison.pixel.PixelDelta;
import java.awt.image.BufferedImage;

import java.util.Vector;
import org.allbinary.logic.communication.log.LogFactory;

public class ImageComparisonResult
{
    public boolean isSameHeight;
    public boolean isSameWidth;
    
    public int pixelsThatMatch;
    public int pixelsIgnored;
    
    public int imageHeight;
    public int imageWidth;
    
    private int tolerance;
    
    private Vector nonMatchingPixelVector;
    
    private BufferedImage bufferedImages[];
    
    private float matchingPercent = -1;

    private Long frameOne;
    private Long frameTwo;
    
    public ImageComparisonResult(
        BufferedImage bufferedImage,
        BufferedImage bufferedImage2,
        Long frameOne,
        Long frameTwo,
        int tolerance)
    {
        this.nonMatchingPixelVector = new Vector();
        
        this.setBufferedImages(new BufferedImage[2]);
        this.getBufferedImages()[0] = bufferedImage;
        this.getBufferedImages()[1] = bufferedImage2;
        
        this.setFrameOne(frameOne);
        this.setFrameTwo(frameTwo);
        
        this.setTolerance(0);
        
        this.imageHeight = bufferedImage.getHeight();
        this.imageWidth = bufferedImage.getWidth();
        
        if(bufferedImage.getHeight() != bufferedImage2.getHeight())
        {
            isSameHeight = false;
            
            if(imageHeight > bufferedImage2.getHeight())
            {
                this.imageHeight = bufferedImage2.getHeight();
            }
        }
        else
        {
            isSameHeight = true;
        }
        
        if(bufferedImage.getWidth() != bufferedImage2.getWidth())
        {
            isSameWidth = false;
            
            if(imageWidth > bufferedImage2.getWidth())
            {
                this.imageWidth = bufferedImage2.getWidth();
            }
        }
        else
        {
            isSameWidth = true;
        }
        
        if(!isSameWidth || !isSameHeight)
        {
            LogUtil.put(LogFactory.getInstance("Images were not the same size? Most likely a resolution change.", this, "Constructor"));
            LogUtil.put(LogFactory.getInstance("1: " + ImageUtil.toString(bufferedImage), this, "Constructor"));
            LogUtil.put(LogFactory.getInstance("2: " + ImageUtil.toString(bufferedImage2), this, "Constructor"));
        }
    }
    
    private float getMatchingPercent()
    {
        if(this.matchingPercent == -1)
        {
            this.matchingPercent = (float)pixelsThatMatch / (this.imageWidth * this.imageHeight);
        }
        
        return this.matchingPercent;
    }
    
    public void add(PixelDelta pixel)
    {
        this.nonMatchingPixelVector.add(pixel);
    }
    
    public Vector getNonMatchingPixelVector()
    {
        return this.nonMatchingPixelVector;
    }
    
    public String toString()
    {
        return "ImageComparisonInfo: " +
            " Number Of Matching Pixels: " + this.pixelsThatMatch +
            "\nNumber Of Non-Matching Pixels: " + this.getNonMatchingPixelVector().size() +
            "\nNumber Of Pixels Ignored: " + this.pixelsIgnored +
            "\nMatching Percentage: " + this.getMatchingPercent();
    }
    
    public BufferedImage[] getBufferedImages()
    {
        return bufferedImages;
    }
    
    private void setBufferedImages(BufferedImage[] bufferedImages)
    {
        this.bufferedImages = bufferedImages;
    }
    
    public int getTolerance()
    {
        return tolerance;
    }
    
    private void setTolerance(int tolerance)
    {
        this.tolerance = tolerance;
    }

    public Long getFrameOne()
    {
        return frameOne;
    }

    private void setFrameOne(Long frameOne)
    {
        this.frameOne = frameOne;
    }

    public Long getFrameTwo()
    {
        return frameTwo;
    }

    private void setFrameTwo(Long frameTwo)
    {
        this.frameTwo = frameTwo;
    }
    
}
