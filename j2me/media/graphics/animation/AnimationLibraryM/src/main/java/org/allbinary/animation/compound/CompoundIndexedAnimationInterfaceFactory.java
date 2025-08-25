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
package org.allbinary.animation.compound;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;

public class CompoundIndexedAnimationInterfaceFactory
    extends CompoundAnimationInterfaceFactory {

    public CompoundIndexedAnimationInterfaceFactory(final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray)
    {
        this(basicAnimationInterfaceFactoryInterfaceArray, AnimationBehaviorFactory.getInstance());
    }

    public CompoundIndexedAnimationInterfaceFactory(final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray,
        final AnimationBehaviorFactory animationBehaviorFactory)
    {
        super(basicAnimationInterfaceFactoryInterfaceArray, animationBehaviorFactory);
    }

    @Override
    protected Animation[] createArray(final int size) {
        return new Animation[size];
    }
    
    @Override
    protected Animation getInstance(final Animation[] animationArray) {
        return new CompoundIndexedAnimation(animationArray, this.animationBehaviorFactory.getOrCreateInstance());
    }
    
}
