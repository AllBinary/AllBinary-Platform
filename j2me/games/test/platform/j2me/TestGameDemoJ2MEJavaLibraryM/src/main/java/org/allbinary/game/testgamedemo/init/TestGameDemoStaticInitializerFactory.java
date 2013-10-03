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

import allbinary.game.init.BasicBuildGameInitializerFactory;
import allbinary.game.init.GameInitializationInterface;
import allbinary.game.init.GameInitializationInterfaceFactoryInterface;
import allbinary.game.resource.ResourceInitialization;
import org.allbinary.game.testgamedemo.resource.TestGameDemoJ2MEEarlyResourceInitialization;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class TestGameDemoStaticInitializerFactory 
extends BasicBuildGameInitializerFactory
{
    private static GameInitializationInterface STATIC = new J2METestGameDemoStaticInitializer(
            new ResourceInitialization[]{
                        new TestGameDemoJ2MEEarlyResourceInitialization(),
                        new TestGameDemoResourceInitialization(),
                        new TestGameDemoJ2MEAnimationInterfaceFactoryEarlyResourceInitialization(),
                        new TestGameDemoJ2MEAnimationInterfaceFactoryResourceInitialization(),
                        }, 15);

    public GameInitializationInterface getInstance()
    {
        return STATIC;
    }
}
