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
package org.allbinary.game.testgamedemo.init;

import org.allbinary.animation.image.TestGameDemoEarlyResourcesImageBasedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.animation.image.TestGameDemoEarlyResourcesOpenGLESImageBasedAnimationInterfaceFactoryInterfaceFactory;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.resource.ResourceInitialization;

public class TestGameDemoJ2MEAnimationInterfaceFactoryEarlyResourceInitialization
extends ResourceInitialization
{
    public TestGameDemoJ2MEAnimationInterfaceFactoryEarlyResourceInitialization()
    {
    }

    public void init()
    throws Exception
    {
        FeaturedAnimationInterfaceFactoryInterfaceFactory 
        featuredAnimationInterfaceFactoryInterfaceFactory = 
            FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();

        //Early Loading Here
        featuredAnimationInterfaceFactoryInterfaceFactory.add(
                new TestGameDemoEarlyResourcesImageBasedAnimationInterfaceFactoryInterfaceFactory());

        featuredAnimationInterfaceFactoryInterfaceFactory.add(
                new TestGameDemoEarlyResourcesOpenGLESImageBasedAnimationInterfaceFactoryInterfaceFactory());
    }
}
