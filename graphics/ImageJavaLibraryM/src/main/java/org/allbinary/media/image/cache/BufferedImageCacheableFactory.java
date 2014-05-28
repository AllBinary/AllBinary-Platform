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

import com.abcs.logic.util.cache.CacheableInterface;
import com.abcs.logic.util.cache.CacheableInterfaceFactoryInterface;

public class BufferedImageCacheableFactory
    implements CacheableInterfaceFactoryInterface
{
    
    public BufferedImageCacheableFactory()
    {
    }
    
    public CacheableInterface getInstance(Object key)
    {
        return new BufferedImageCacheable((BufferedImageInfo) key);
    }
}
