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
package org.allbinary.input.media.image.capture;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.CacheInterface;
import org.allbinary.logic.util.cache.CacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheTypeFactory;

public class CapturedBufferedImagesCacheSingleton
{
    private static AutomaticCacheInterface cacheInterface = null;
    
    private CapturedBufferedImagesCacheSingleton() {
    }
    
    public static CacheInterface getInstance() {
	return cacheInterface;
    }
    
    static {
        final CommonStrings commonStrings = CommonStrings.getInstance();
	try {

	    LogUtil.put(LogFactory.getInstance(commonStrings.START,"CapturedBufferedImagesCacheSingleton","Static Block"));
	    cacheInterface = (AutomaticCacheInterface) 
                    CacheInterfaceFactory.getInstance((CacheTypeFactory.getInstance().SET),
						    (CachePolicyFactory.getInstance().ONE_MINUTE_FIVE_MAX));
	    LogUtil.put(LogFactory.getInstance(commonStrings.END, "CapturedBufferedImagesCacheSingleton","Static Block"));
	} catch (Exception e) {
	    LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,"CapturedBufferedImagesCacheSingleton","Static Block", e));
	}
    }
}
