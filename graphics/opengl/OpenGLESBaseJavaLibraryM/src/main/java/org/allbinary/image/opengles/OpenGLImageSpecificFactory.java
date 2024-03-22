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

import org.allbinary.logic.communication.log.PreLogUtil;

public class OpenGLImageSpecificFactory
{
    private static final OpenGLImageSpecificFactory instance = new OpenGLImageSpecificFactory();

    private OpenGLImageFactory imageFactory;
    
    public static OpenGLImageSpecificFactory getInstance()
    {
        return instance;
    }

    public void setImageFactory(OpenGLImageFactory imageFactory)
    {
        this.imageFactory = imageFactory;
        
        PreLogUtil.put("Selected Image Factory: " + this.imageFactory, this, "setImageFactory");
    }

    public OpenGLImageFactory getImageFactory()
    {
        return imageFactory;
    }
}
