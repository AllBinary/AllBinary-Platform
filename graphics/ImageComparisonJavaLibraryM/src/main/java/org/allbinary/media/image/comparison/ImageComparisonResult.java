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

import java.awt.image.BufferedImage;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.media.image.comparison.pixel.PixelDelta;
import org.allbinary.string.CommonStrings;

public class ImageComparisonResult
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final BufferedImage[] bufferedImages = new BufferedImage[2];
    
    public final String name;
    
    public final boolean isSameHeight;
    public final boolean isSameWidth;
    
    public final int imageHeight;
    public final int imageWidth;
    
    private final Vector nonMatchingPixelVector;

    private final Long frameOne;
    private final Long frameTwo;
    
    public int pixelsThatMatch;
    public int pixelsIgnored;
    
    private int tolerance;
    
    private float matchingPercent = -1;
    
    public ImageComparisonResult(
        final String name,
        final BufferedImage bufferedImage,
        final BufferedImage bufferedImage2,
        final Long frameOne,
        final Long frameTwo,
        final int tolerance)
    {
        this.name = name;

        this.nonMatchingPixelVector = new Vector();
        
        this.bufferedImages[0] = bufferedImage;
        this.bufferedImages[1] = bufferedImage2;
        
        this.frameOne = frameOne;
        this.frameTwo = frameTwo;
        
        this.setTolerance(0);
        
        int imageHeight = bufferedImage.getHeight();   
        int imageWidth = bufferedImage.getWidth();
        
        if(bufferedImage.getHeight() != bufferedImage2.getHeight())
        {
            isSameHeight = false;
            
            if(imageHeight > bufferedImage2.getHeight())
            {
                imageHeight = bufferedImage2.getHeight();
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
                imageWidth = bufferedImage2.getWidth();
            }
        }
        else
        {
            isSameWidth = true;
        }
        
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        if(!isSameWidth || !isSameHeight)
        {
            final ImageUtil imageUtil = ImageUtil.getInstance();
            logUtil.put("Images were not the same size? Most likely a resolution change.", this, this.commonStrings.CONSTRUCTOR);
            logUtil.put("1: " + imageUtil.toString(bufferedImage), this, this.commonStrings.CONSTRUCTOR);
            logUtil.put("2: " + imageUtil.toString(bufferedImage2), this, this.commonStrings.CONSTRUCTOR);
        }
    }
    
    public float getMatchingPercent()
    {
        if(this.matchingPercent == -1)
        {
            this.matchingPercent = (float) pixelsThatMatch / (this.imageWidth * this.imageHeight);
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
        return new StringMaker().append("ImageComparisonInfo: ").append(this.name)
                .append(" Number Of Matching Pixels: ").append(this.pixelsThatMatch)
                .append("\nNumber Of Non-Matching Pixels: ").append(this.getNonMatchingPixelVector().size())
                .append("\nNumber Of Pixels Ignored: ").append(this.pixelsIgnored)
                .append("\nMatching Percentage: ").append(this.getMatchingPercent()).toString();
    }
    
    public BufferedImage[] getBufferedImages()
    {
        return bufferedImages;
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

    public Long getFrameTwo()
    {
        return frameTwo;
    }
    
}
