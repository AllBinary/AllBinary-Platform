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

import javax.microedition.lcdui.Image;

import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;

public class OpenGLImageFactory
{
    public static OpenGLImageFactory NULL_OPENGL_IMAGE_FACTORY = new OpenGLImageFactory();
    
//    public Image getInstance(GL10 gl, Image image, boolean matchColor)
//    {
//        throw new RuntimeException();
//        //return null;
//    }

    public Image getInstance(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        throw new RuntimeException();
        //return null;
    }
    
}
