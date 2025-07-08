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
package org.allbinary.animation.image;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.logic.util.cache.CacheableInterface;
import org.allbinary.logic.util.cache.CacheableInterfaceFactoryInterface;

public class ImageArrayRotationAnimationCacheableInterfaceFactory 
implements CacheableInterfaceFactoryInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public CacheableInterface getInstance(final Object key)
    throws Exception
    {
    	//logUtil.put("Creating new Instance", this, commonStrings.GET_INSTANCE);
        return new ImageArrayRotationAnimationCacheable(key, AnimationBehavior.getInstance());
    }	
}
