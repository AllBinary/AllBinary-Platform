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
package allbinary.media.image.cache;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import com.abcs.logic.util.cache.CachePolicyFactory;
import com.abcs.logic.util.cache.PoolInterface;
import com.abcs.logic.util.cache.PoolInterfaceFactory;
import com.abcs.logic.util.cache.PoolType;
import com.abcs.logic.util.cache.PoolTypeFactory;

public class BufferedImagePoolSingleton
{
    private static PoolInterface poolInterface = null;
    
    static
    {
        try
        {
            LogUtil.put(new Log("Start", "BufferedImagePoolSingleton", "Static Block"));
            
            poolInterface =
                PoolInterfaceFactory.getInstance(
                new BufferedImageCacheableFactory(),
                PoolTypeFactory.getInstance().SHIFT_ONE_VECTOR_POOL, 
                CachePolicyFactory.getInstance().MAX_TIME_THOUSAND_MAX);
            
            LogUtil.put(new Log("End", "BufferedImagePoolSingleton", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(new Log("Exception", "BufferedImagePoolSingleton", "Static Block", e));
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
