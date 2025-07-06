/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.unit;

import org.allbinary.game.layer.resources.BasicGameResources;

import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.animation.NullRotationAnimationFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

public class DecalAnimationInterfaceFactory
{
    private static final DecalAnimationInterfaceFactory instance = 
        new DecalAnimationInterfaceFactory();

    public static DecalAnimationInterfaceFactory getInstance()
    {
        return instance;
    }

    public AnimationInterfaceFactoryInterface getInstance(
            BasicGameResources basicGameResources,
            BasicColor basicColor)
        throws Exception
    {
        AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface;
        
        if(basicColor == BasicColorFactory.getInstance().RED)
        {
            animationInterfaceFactoryInterface = 
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                        basicGameResources.RESOURCE);
        }
        else
        {
            animationInterfaceFactoryInterface = NullRotationAnimationFactory.getFactoryInstance();
        }
        
        return animationInterfaceFactoryInterface;
    }
}
