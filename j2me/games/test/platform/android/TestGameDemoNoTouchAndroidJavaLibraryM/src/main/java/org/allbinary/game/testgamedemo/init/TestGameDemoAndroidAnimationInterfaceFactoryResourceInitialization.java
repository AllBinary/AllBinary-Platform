package org.allbinary.game.testgamedemo.init;

import org.allbinary.animation.image.TestGameDemoImageBasedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.animation.image.TestGameDemoOpenGLESImageBasedAnimationInterfaceFactoryInterfaceFactory;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.resource.ResourceInitialization;

public class TestGameDemoAndroidAnimationInterfaceFactoryResourceInitialization 
extends ResourceInitialization
{
    public TestGameDemoAndroidAnimationInterfaceFactoryResourceInitialization()
    {
    }
    
    public void init()
    throws Exception
    //throws Exception
    {
        //Normal Loading Here
        FeaturedAnimationInterfaceFactoryInterfaceFactory featuredAnimationInterfaceFactoryInterfaceFactory = 
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();

        featuredAnimationInterfaceFactoryInterfaceFactory.add(
                new TestGameDemoImageBasedAnimationInterfaceFactoryInterfaceFactory());
        
        featuredAnimationInterfaceFactoryInterfaceFactory.add(
                new TestGameDemoOpenGLESImageBasedAnimationInterfaceFactoryInterfaceFactory());
    }
}
