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

import java.util.HashMap;

import org.allbinary.logic.string.StringMaker;

public class BufferedImageInfoFactory
{
    private static HashMap hashMap = new HashMap();

    private BufferedImageInfoFactory()
    {
    }
    
    public static BufferedImageInfo getInstance(
        int width, int height, int type)
    {
        StringMaker stringbuffer = new StringMaker();
        
        stringbuffer.append(width);
        stringbuffer.append(height);
        stringbuffer.append(type);
        
        String key = stringbuffer.toString();
        
        BufferedImageInfo bufferedImageInfo = 
            (BufferedImageInfo) hashMap.get(key);
        
        if(bufferedImageInfo == null)
        {
            bufferedImageInfo = 
                new BufferedImageInfo(
                width, height, type);

            hashMap.put(key, bufferedImageInfo);
        }
        return bufferedImageInfo;
    }
}
