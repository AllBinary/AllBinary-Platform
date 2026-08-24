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

import jsinterop.annotations.JsType;

import org.allbinary.media.ScaleProperties;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class SingletonAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface
{
    private final Animation animationInterface;

    @JsConstructor
    public SingletonAnimationInterfaceFactory(final Animation animationInterface)
    {
        this.animationInterface = animationInterface;
    }

    @Override
    @JsMethod
    public Animation getInstance(final int instanceId) throws Exception
    {
        return this.animationInterface;
    }

    @Override
    @JsMethod
    public Animation getInstanceAnimation(final Animation animationInterface)
        throws Exception
    {
        return this.animationInterface;
    }
    
    @Override
    @JsMethod
    public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }
    
}
