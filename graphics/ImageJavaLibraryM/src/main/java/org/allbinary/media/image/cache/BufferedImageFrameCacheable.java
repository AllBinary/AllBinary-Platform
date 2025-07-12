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
package org.allbinary.media.image.cache;

import java.awt.image.BufferedImage;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.cache.CacheableInterface;

public class BufferedImageFrameCacheable
    implements CacheableInterface
{
    private Long frame;
    
    private BufferedImage bufferedImage;
    
    public BufferedImageFrameCacheable(
        BufferedImage bufferedImage, Long frame)
    {
        this.frame = frame;
        this.setBufferedImage(bufferedImage);
    }
    
    public Object getKey()
    {
        return this.frame;
    }

    public BufferedImage getBufferedImage()
    {
        return bufferedImage;
    }

    private void setBufferedImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }

    public Long getFrame()
    {
        return frame;
    }

    public void setFrame(Long frame)
    {
        this.frame = frame;
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
