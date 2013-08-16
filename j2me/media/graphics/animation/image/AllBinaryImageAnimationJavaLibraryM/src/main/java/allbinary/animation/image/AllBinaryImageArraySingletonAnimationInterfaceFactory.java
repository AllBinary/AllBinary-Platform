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
package allbinary.animation.image;

import javax.microedition.lcdui.Image;

import allbinary.animation.Animation;
import allbinary.animation.IndexedAnimation;
import allbinary.animation.SingletonAnimationInterfaceFactory;
import allbinary.image.IndexedAnimationToImageArrayUtil;

public class AllBinaryImageArraySingletonAnimationInterfaceFactory
extends SingletonAnimationInterfaceFactory
{
    public AllBinaryImageArraySingletonAnimationInterfaceFactory(Animation animationInterface)
    {
        super(animationInterface);
    }
    
    public AllBinaryImageArraySingletonAnimationInterfaceFactory(IndexedAnimation animationInterface, int width, int height)
    throws Exception
    {
        this(new AllBinaryImageArrayAnimation(IndexedAnimationToImageArrayUtil.getInstance(width, height, animationInterface)));
    }    

    public AllBinaryImageArraySingletonAnimationInterfaceFactory(Image[] imageArray)
    throws Exception
    {
        this(new AllBinaryImageArrayAnimation(imageArray));
    }    
}
