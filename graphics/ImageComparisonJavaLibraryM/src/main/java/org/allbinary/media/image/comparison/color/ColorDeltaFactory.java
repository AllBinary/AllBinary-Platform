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
package org.allbinary.media.image.comparison.color;


import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.CacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheTypeFactory;

public class ColorDeltaFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static AutomaticCacheInterface cacheInterface = null;
    
    static
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final String STATIC_BLOCK = "Static Block";
        final String instance = "ColorDeltaFactory";
        try
        {
            
            logUtil.put(commonStrings.START, instance, STATIC_BLOCK);
            
            cacheInterface = (AutomaticCacheInterface) CacheInterfaceFactory.getInstance(CacheTypeFactory.getInstance().CACHE,
                CachePolicyFactory.getInstance().THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            logUtil.put(commonStrings.END, instance, STATIC_BLOCK);
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, instance, STATIC_BLOCK, e);
        }
    }
    
    private ColorDeltaFactory()
    {
    }
    
    public static ColorDelta getInstance(int rgb1, int rgb2)
    throws Exception
    {
        ColorDelta colorDelta = (ColorDelta) 
            cacheInterface.get(ColorDelta.getKey(rgb1, rgb2));
        
        if(colorDelta == null)
        {
            colorDelta = new ColorDelta(rgb1, rgb2);
        }

        return colorDelta;
    }
}
