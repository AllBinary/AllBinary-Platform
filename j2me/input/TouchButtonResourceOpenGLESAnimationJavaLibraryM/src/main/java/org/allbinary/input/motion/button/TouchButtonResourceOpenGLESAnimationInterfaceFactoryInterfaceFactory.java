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
package org.allbinary.input.motion.button;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.opengles.OpenGLImageCacheFactory;

public class TouchButtonResourceOpenGLESAnimationInterfaceFactoryInterfaceFactory 
    extends TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory
{
    public TouchButtonResourceOpenGLESAnimationInterfaceFactoryInterfaceFactory()
    {
        super("OpenGL TouchButton Animations");
    }

    @Override
    public void init(int level) 
    throws Exception
    {
        super.init(OpenGLImageCacheFactory.getInstance(), level);
        //throw new Exception("OpenGL Resource Loader Only");
    }
    
    @Override
    public boolean isFeature()
    {
        if (Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
        {
            return true;
        } else
        {
            return false;
        }
    }

}
