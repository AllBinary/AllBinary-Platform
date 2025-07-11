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
package org.allbinary.media.image.comparison.pixel;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.CacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheTypeFactory;
import org.allbinary.media.image.comparison.color.ColorDelta;
import org.allbinary.media.image.comparison.color.ColorDeltaFactory;
import org.allbinary.string.CommonStrings;

public class PixelDeltaFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static AutomaticCacheInterface cacheInterface = null;
    
    static
    {
        final LogUtil logUtil = LogUtil.getInstance();
        final CommonStrings commonStrings = CommonStrings.getInstance();
        final String STATIC_BLOCK = "Static Block";
        final String instance = "PixelDeltaFactory";
        
        try
        {
            logUtil.put(commonStrings.START, instance, STATIC_BLOCK);
            
            cacheInterface = (AutomaticCacheInterface) 
                    CacheInterfaceFactory.getInstance(CacheTypeFactory.getInstance().CACHE, 
                CachePolicyFactory.getInstance().THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            logUtil.put(commonStrings.END, instance, STATIC_BLOCK);
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, instance, STATIC_BLOCK, e);
        }
    }
    
    private PixelDeltaFactory()
    {
    }
    
    public static PixelDelta getInstance(int x, int y, int rgb1, int rgb2)
    throws Exception
    {
        GPoint point = PointFactory.getInstance().getInstance(x, y);
        ColorDelta colorDelta = ColorDeltaFactory.getInstance(rgb1, rgb2);

        PixelDelta pixelDelta = (PixelDelta) 
           cacheInterface.get(PixelDelta.getKey(point, colorDelta));

        if(pixelDelta == null)
        {
            pixelDelta = new PixelDelta(point, colorDelta);
        }

        return pixelDelta;
    }
}
