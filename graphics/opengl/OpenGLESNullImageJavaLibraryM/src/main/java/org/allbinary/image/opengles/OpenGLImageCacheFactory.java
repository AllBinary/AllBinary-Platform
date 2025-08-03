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
package org.allbinary.image.opengles;

import org.allbinary.image.ImageCache;

public class OpenGLImageCacheFactory
{
    private static ImageCache IMAGE_CACHE = ImageCache.NULL_IMAGE_CACHE;

    /*
    public static void init(GL10 gl)
    {
        ((OpenGLImageCache) IMAGE_CACHE).setGl(gl);
    }
    */

    public static ImageCache getInstance()
    {
        return IMAGE_CACHE;
    }
    
    public static void init()
    {
        if(IMAGE_CACHE == null)
        {
            //IMAGE_CACHE = new OpenGLImageCache();
        }
    }
}
