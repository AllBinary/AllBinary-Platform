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
package org.allbinary.animation;

import org.allbinary.logic.util.cache.CacheableInterface;
import org.allbinary.logic.util.cache.CacheableInterfaceFactoryInterface;

public class AllBinaryImageArrayRotationAnimationCacheableInterfaceFactory 
implements CacheableInterfaceFactoryInterface
{
    public CacheableInterface getInstance(Object key)
    throws Exception
    {
    	//LogUtil.put(LogFactory.getInstance("Creating new Instance", this, CommonStrings.getInstance().GET_INSTANCE));
        return new AllBinaryImageArrayRotationAnimationCacheable(key);
    }	
}
