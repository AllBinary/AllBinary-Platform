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
package org.allbinary.animation;

import org.allbinary.media.ScaleProperties;

public class SingletonAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface
{
    private final Animation animationInterface;

    public SingletonAnimationInterfaceFactory(Animation animationInterface)
    {
        this.animationInterface = animationInterface;
    }

    public Animation getInstance(final int instanceId) throws Exception
    {
        return this.animationInterface;
    }

    public Animation getInstance(Animation animationInterface)
        throws Exception
    {
        return this.animationInterface;
    }
    
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }
    
}
