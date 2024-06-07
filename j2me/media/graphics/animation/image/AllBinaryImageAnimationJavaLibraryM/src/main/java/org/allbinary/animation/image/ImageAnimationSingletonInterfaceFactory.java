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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.SingletonAnimationInterfaceFactory;
import org.allbinary.image.AnimationFrameToImageUtil;

public class ImageAnimationSingletonInterfaceFactory
extends SingletonAnimationInterfaceFactory
{
    private ImageAnimationSingletonInterfaceFactory(Animation animationInterface)
    {
        super(animationInterface);
    }

    public ImageAnimationSingletonInterfaceFactory(Image image)
        throws Exception
    {
        super(new ImageAnimation(image, AnimationBehavior.getInstance()));
    }

    public ImageAnimationSingletonInterfaceFactory(
            Animation animationInterface, int width, int height)
        throws Exception
    {
        this(new ImageAnimation(
                AnimationFrameToImageUtil.getInstance().getInstance(
                        width, height, animationInterface), AnimationBehavior.getInstance()));
    }
}
