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
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.SingletonAnimationInterfaceFactory;
import org.allbinary.image.IndexedAnimationToImageArrayUtil;

public class ImageArraySingletonAnimationInterfaceFactory
extends SingletonAnimationInterfaceFactory
{
    public ImageArraySingletonAnimationInterfaceFactory(final Animation animationInterface)
    {
        super(animationInterface);
    }
    
    public ImageArraySingletonAnimationInterfaceFactory(final IndexedAnimation animationInterface, final int width, final int height)
    throws Exception
    {
        this(new ImageArrayAnimation(IndexedAnimationToImageArrayUtil.getInstance(width, height, animationInterface), AnimationBehavior.getInstance()));
    }    

    public ImageArraySingletonAnimationInterfaceFactory(Image[] imageArray)
    throws Exception
    {
        this(new ImageArrayAnimation(imageArray, AnimationBehavior.getInstance()));
    }    
}
