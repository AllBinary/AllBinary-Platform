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
package org.allbinary.graphics.color;


import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.AutomaticCacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheTypeFactory;
import org.allbinary.string.CommonStrings;


public class ColorCacheFactory
{
   private static AutomaticCacheInterface cacheInterface = null;
    
    static
    {
        try
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.START, ColorCacheFactory.class, "Static Block"));
            
            cacheInterface = AutomaticCacheInterfaceFactory.getInstance(
                new ColorCacheableFactory(),
                CacheTypeFactory.getInstance().CACHE, 
                CachePolicyFactory.getInstance().THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            LogUtil.put(LogFactory.getInstance("End", ColorCacheFactory.class, "Static Block"));
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, ColorCacheFactory.class, "Static Block", e));
        }
    }
    
    private ColorCacheFactory()
    {
    }

    public static AutomaticCacheInterface getInstance()
    {
        return cacheInterface;
    }    
}
