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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.logic.util.cache.CacheInterfaceFactory;
import org.allbinary.logic.util.cache.CachePolicyFactory;
import org.allbinary.logic.util.cache.CacheTypeFactory;
import org.allbinary.media.image.comparison.color.ColorDelta;
import org.allbinary.media.image.comparison.color.ColorDeltaFactory;

public class PixelDeltaFactory
{
    private static AutomaticCacheInterface cacheInterface = null;
    
    static
    {
        try
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.START, "PixelDeltaFactory", "Static Block"));
            
            cacheInterface = (AutomaticCacheInterface) 
                    CacheInterfaceFactory.getInstance(CacheTypeFactory.getInstance().CACHE, 
                CachePolicyFactory.getInstance().THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            LogUtil.put(LogFactory.getInstance(commonStrings.END, "PixelDeltaFactory", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", "PixelDeltaFactory", "Static Block", e));
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
