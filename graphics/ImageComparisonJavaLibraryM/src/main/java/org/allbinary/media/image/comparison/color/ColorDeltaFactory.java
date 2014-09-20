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


import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;
import com.abcs.logic.util.cache.CacheInterface;
import com.abcs.logic.util.cache.CacheInterfaceFactory;
import com.abcs.logic.util.cache.CachePolicy;
import com.abcs.logic.util.cache.CacheType;

public class ColorDeltaFactory
{
    private static CacheInterface cacheInterface = null;
    
    static
    {
        try
        {
            LogUtil.put(new Log("Start", "ColorDeltaFactory", "Static Block"));
            
            cacheInterface = CacheInterfaceFactory.getInstance(
                CacheType.SHIFT_ONE_CACHE,
                CachePolicy.THIRTY_MINUTES_TEN_THOUSAND_MAX);
            
            LogUtil.put(new Log("End", "ColorDeltaFactory", "Static Block"));
        }
        catch(Exception e)
        {
            LogUtil.put(new Log("Exception", "ColorDeltaFactory", "Static Block", e));
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
