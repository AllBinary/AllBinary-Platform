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
package allbinary.media.image.io;

import com.abcs.logic.util.cache.CacheableInterface;
import com.abcs.logic.util.cache.CacheableInterfaceFactoryInterface;

public class IIOImageCacheableFactory
    implements CacheableInterfaceFactoryInterface
{
    
    public IIOImageCacheableFactory()
    {
    }
    
    public CacheableInterface getInstance(Object key)
    {
        return new IIOImageCacheable((String) key);
    }
}
