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
package org.allbinary.image;

import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.opengles.OpenGLImageCacheFactory;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;

public class GameFeatureImageCacheFactory
{
    public static final ImageCache getInstance()
    {
        if(Features.getInstance().isDefault(
                OpenGLFeatureFactory.getInstance().OPENGL))
        {
            return OpenGLImageCacheFactory.getInstance();
        }
        else
        {
            return ImageCacheFactory.getInstance();
        }
    }
    
    public static void init()
    {
        ImageCacheFactory.init();
        OpenGLImageCacheFactory.init();
    }

    public static void releaseAll()
    {
        ImageCacheFactory.getInstance().releaseAll();
        OpenGLImageCacheFactory.getInstance().releaseAll();
    }
    
}
