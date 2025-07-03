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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.PoolInterface;
import org.allbinary.logic.util.cache.PoolInterfaceFactory;
import org.allbinary.logic.util.cache.PoolTypeFactory;
import org.allbinary.string.CommonStrings;

public class BufferedImagePoolSingleton
{
    private static PoolInterface poolInterface = null;
    
    static
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final String STATIC_BLOCK = "Static Block";
        final String instance = "BufferedImagePoolSingleton";
        
        try
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.START, instance, STATIC_BLOCK));
            
            poolInterface =
                PoolInterfaceFactory.getInstance(
                new BufferedImageCacheableFactory(),
                PoolTypeFactory.getInstance().VECTOR_POOL, 
                CachePolicyFactory.getInstance().MAX_TIME_THOUSAND_MAX);
            
            LogUtil.put(LogFactory.getInstance(commonStrings.END, instance, STATIC_BLOCK));
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, instance, STATIC_BLOCK, e));
        }
    }
    
    private BufferedImagePoolSingleton()
    {
    }
    
    public static PoolInterface getInstance()
    {
        return poolInterface;
    }
}
