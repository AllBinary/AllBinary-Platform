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
package allbinary.media.image.comparison.pixel;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import java.awt.Point;

import allbinary.input.automation.PointFactory;
import allbinary.media.image.comparison.color.ColorDelta;
import allbinary.media.image.comparison.color.ColorDeltaFactory;
import com.abcs.logic.util.cache.CacheInterface;
import com.abcs.logic.util.cache.CacheInterfaceFactory;
import com.abcs.logic.util.cache.CachePolicy;
import com.abcs.logic.util.cache.CacheType;

public class PixelDeltaFactory
{
    private static CacheInterface cacheInterface = null;
    
    static
    {
        try
        {
            LogUtil.put(new Log("Start", "PixelDeltaFactory", "Static Block"));
            
            cacheInterface = CacheInterfaceFactory.getInstance(
                CacheType.SHIFT_ONE_CACHE, 
                CachePolicy.THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            LogUtil.put(new Log("End", "PixelDeltaFactory", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(new Log("Exception", "PixelDeltaFactory", "Static Block", e));
        }
    }
    
    private PixelDeltaFactory()
    {
    }
    
    public static PixelDelta getInstance(int x, int y, int rgb1, int rgb2)
    throws Exception
    {
        Point point = PointFactory.getInstance(x, y);
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
