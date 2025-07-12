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


import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.cache.CacheableInterface;

public class ImageComparisonResultFrameCacheable
    implements CacheableInterface
{
    private Long frame;
    
    private ImageComparisonResult imageComparisonResult;
    
    public ImageComparisonResultFrameCacheable(
        ImageComparisonResult imageComparisonResult, Long frame)
    {
        this.setImageComparisonResult(imageComparisonResult);
        this.frame = frame;
    }
    
    public Object getKey()
    {
        return this.frame;
    }

    public Long getFrame()
    {
        return frame;
    }

    public void setFrame(Long frame)
    {
        this.frame = frame;
    }

    public ImageComparisonResult getImageComparisonResult()
    {
        return imageComparisonResult;
    }

    public void setImageComparisonResult(ImageComparisonResult imageComparisonResult)
    {
        this.imageComparisonResult = imageComparisonResult;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(" Frame: ");
        stringBuffer.append(this.getFrame());
        return stringBuffer.toString();
    }
}
