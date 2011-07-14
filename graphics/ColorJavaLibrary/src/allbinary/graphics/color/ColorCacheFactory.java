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
package allbinary.graphics.color;


import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import com.abcs.logic.util.cache.AutomaticCacheInterface;
import com.abcs.logic.util.cache.AutomaticCacheInterfaceFactory;
import com.abcs.logic.util.cache.CachePolicyFactory;
import com.abcs.logic.util.cache.CacheTypeFactory;


public class ColorCacheFactory
{
   private static AutomaticCacheInterface cacheInterface = null;
    
    static
    {
        try
        {
            LogUtil.put(new Log("Start", ColorCacheFactory.class, "Static Block"));
            
            cacheInterface = AutomaticCacheInterfaceFactory.getInstance(
                new ColorCacheableFactory(),
                CacheTypeFactory.getInstance().SHIFT_ONE_CACHE, 
                CachePolicyFactory.getInstance().THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            LogUtil.put(new Log("End", ColorCacheFactory.class, "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(new Log("Exception", ColorCacheFactory.class, "Static Block", e));
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
