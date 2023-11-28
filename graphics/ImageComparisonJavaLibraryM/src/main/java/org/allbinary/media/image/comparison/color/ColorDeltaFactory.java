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
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.CacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheTypeFactory;

public class ColorDeltaFactory
{
    private static AutomaticCacheInterface cacheInterface = null;
    
    static
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            
            LogUtil.put(LogFactory.getInstance(commonStrings.START, "ColorDeltaFactory", "Static Block"));
            
            cacheInterface = (AutomaticCacheInterface) CacheInterfaceFactory.getInstance(CacheTypeFactory.getInstance().CACHE,
                CachePolicyFactory.getInstance().THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            LogUtil.put(LogFactory.getInstance(commonStrings.END, "ColorDeltaFactory", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "ColorDeltaFactory", "Static Block", e));
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
