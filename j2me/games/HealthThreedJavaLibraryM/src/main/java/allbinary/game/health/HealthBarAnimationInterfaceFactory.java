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

import allbinary.animation.Animation;
import allbinary.animation.AnimationInterfaceFactoryInterface;
import allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import allbinary.layer.AllBinaryLayer;

public class HealthBarAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface
{
    public HealthBarAnimationInterfaceFactory()
    {
    }

    public HealthBarAnimation getInstance(AllBinaryLayer layerInterface, int location) throws Exception
    {
        return new HealthBarThreedAnimation(layerInterface, location);
    }
    
    public Animation getInstance() throws Exception
    {
        return null;
    }

    public Animation getInstance(Animation animationInterface)
        throws Exception
    {
        return null;
    }
}
