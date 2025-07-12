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

public class BufferedImageCacheable
    implements CacheableInterface
{
    private BufferedImageInfo key;
    
    private BufferedImage bufferedImage;
    
    public BufferedImageCacheable(BufferedImageInfo key)
    {
        this.key = key;
        this.setBufferedImage(new BufferedImage(
            key.getWidth(), key.getHeight(), key.getType()));
    }
    
    public Object getKey()
    {
        return this.key;
    }

    public BufferedImage getBufferedImage()
    {
        return bufferedImage;
    }

    private void setBufferedImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(" ");
        stringBuffer.append(this.getKey().toString());
        return stringBuffer.toString();
    }
}
