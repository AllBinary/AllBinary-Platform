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
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;

import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;

public class OpenGLESGL10ImageFactory extends OpenGLImageFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    /*
    public Image getInstance(GL10 gl, Image image, boolean matchColor)
    {
        return new OpenGLESGL10Image(gl, image, matchColor);
    }
    */

    @Override
    public Image getInstance(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        //PreLogUtil.put("opengl " + image.getName(), this, commonStrings.GET_INSTANCE);
        return new OpenGLESGL10Image(image, bitmapFactory, textureFactory);
    }
    
}
