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

import org.allbinary.animation.image.TestGameDemoImageBasedAnimationInterfaceFactoryInterfaceFactory;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.resource.ResourceInitialization;
import org.allbinary.game.testgamedemo.init.TestGameDemoStaticInitializer;

public class J2METestGameDemoStaticInitializer
    extends TestGameDemoStaticInitializer
{
    public J2METestGameDemoStaticInitializer(
            ResourceInitialization[] resourceInitializationArray, int portion)
    {
        super(resourceInitializationArray, portion);
    }

    protected void initFeatureResources() 
    throws Exception
    {
        FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().add(
                new TestGameDemoImageBasedAnimationInterfaceFactoryInterfaceFactory());
    }
}
