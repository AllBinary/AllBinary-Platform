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
package org.allbinary.media.image.io;

import java.awt.image.BufferedImage;

import javax.imageio.IIOImage;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.cache.CacheableInterface;

public class IIOImageCacheable
    implements CacheableInterface
{    
    private String key;
 
    private IIOImage iioImage;

    public IIOImageCacheable(String key)
    {
        this.key = key;
    }
    
    public Object getKey()
    {
        return this.key;
    }
    
    public void setBufferedImage(BufferedImage bufferedImage)
    {
        this.setIioImage(new IIOImage(bufferedImage, null, null));
    }

    public IIOImage getIioImage()
    {
        return iioImage;
    }

    private void setIioImage(IIOImage iioImage)
    {
        this.iioImage = iioImage;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(this.getClass().getName());
        stringBuffer.append(" Reusable IIOImage");
        return stringBuffer.toString();
    }
    
}
