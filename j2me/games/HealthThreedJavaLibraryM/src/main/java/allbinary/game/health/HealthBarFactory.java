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
package allbinary.game.health;

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.layer.AllBinaryLayer;

public class HealthBarFactory
{
    public HealthBar getInstance(
            AllBinaryLayer layerInterface,
            Health healthInterface, 
            int location, int direction)
    throws Exception
    {
        if(OpenGLFeatureUtil.getInstance().isAnyThreed())
        {
            FeaturedAnimationInterfaceFactoryInterfaceFactory 
            featuredAnimationInterfaceFactoryInterfaceFactory =
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();

            HealthBarAnimationInterfaceFactory healthBarAnimationInterfaceFactory =
                    (HealthBarAnimationInterfaceFactory)
                    featuredAnimationInterfaceFactoryInterfaceFactory.get(Health.NAME);

            return new HealthBar(layerInterface, healthInterface, 
                    healthBarAnimationInterfaceFactory.getInstance(layerInterface, location), 
                    direction);
        }
        else
        {
            return new HealthBar(layerInterface, healthInterface, 
                    new HealthBarTwodAnimation(layerInterface, location), 
                    direction);
        }
    }
}
