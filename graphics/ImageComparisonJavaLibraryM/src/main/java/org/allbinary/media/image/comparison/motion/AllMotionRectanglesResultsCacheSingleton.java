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
package org.allbinary.media.image.comparison.motion;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.util.cache.CacheInterface;
import org.allbinary.logic.util.cache.CacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicy;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheType;
import org.allbinary.logic.util.cache.CacheTypeFactory;

public class AllMotionRectanglesResultsCacheSingleton
{
    private static CacheInterface cacheInterface = null;
    
    static
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            
            LogUtil.put(LogFactory.getInstance(commonStrings.START, "MotionRectanglesResultsCacheSingleton", "Static Block"));
            
            cacheInterface = CacheInterfaceFactory.getInstance(CacheTypeFactory.getInstance().CACHE, CachePolicyFactory.getInstance().ONE_MINUTE_FIVE_MAX);
            
            LogUtil.put(LogFactory.getInstance(commonStrings.END, "MotionRectanglesResultsCacheSingleton", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "MotionRectanglesResultsCacheSingleton", "Static Block", e));
        }
    }
    
    private AllMotionRectanglesResultsCacheSingleton()
    {
    }
    
    public static CacheInterface getInstance()
    {
        return cacheInterface;
    }    
}
