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
package allbinary.media.image.comparison;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import com.abcs.logic.util.cache.CacheInterface;
import com.abcs.logic.util.cache.CacheInterfaceFactory;
import com.abcs.logic.util.cache.CachePolicy;
import com.abcs.logic.util.cache.CacheType;

public class ImageComparisonResultCacheSingleton
{
    private static CacheInterface cacheInterface = null;
    
    static
    {
        try
        {
            LogUtil.put(new Log("Start", "ImageComparisonCacheSingleton", "Static Block"));
            
            cacheInterface = CacheInterfaceFactory.getInstance(
                CacheType.SHIFT_ONE_CACHE, CachePolicy.ONE_MINUTE_FIVE_MAX);
            
            LogUtil.put(new Log("End", "ImageComparisonCacheSingleton", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(new Log("Exception", "ImageComparisonCacheSingleton", "Static Block", e));
        }
    }
    
    private ImageComparisonResultCacheSingleton()
    {
    }
    
    public static CacheInterface getInstance()
    {
        return cacheInterface;
    }    
}
