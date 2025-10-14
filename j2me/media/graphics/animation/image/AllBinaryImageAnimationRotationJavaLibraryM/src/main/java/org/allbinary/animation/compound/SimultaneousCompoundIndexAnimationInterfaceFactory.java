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
import org.allbinary.media.ScaleProperties;

public class SimultaneousCompoundIndexAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface {

    private final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray;
    private final AnimationBehaviorFactory animationBehaviorFactory;

    public SimultaneousCompoundIndexAnimationInterfaceFactory(
        final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray,
        final AnimationBehaviorFactory animationBehaviorFactory) {
        
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
        this.animationBehaviorFactory = animationBehaviorFactory;

    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        final int size = this.basicAnimationInterfaceFactoryInterfaceArray.length;
        final IndexedAnimation[] animationInterfaceArray = new IndexedAnimation[size];

        for (int index = 0; index < size; index++) {
            animationInterfaceArray[index] = (IndexedAnimation) this.basicAnimationInterfaceFactoryInterfaceArray[index].getInstance(instanceId);
        }

        return this.getInstance(animationInterfaceArray);
    }

    protected Animation getInstance(final IndexedAnimation[] animationInterfaceArray) {
        return new SimultaneousCompoundIndexAnimation(animationInterfaceArray, this.animationBehaviorFactory.getOrCreateInstance());
    }

    public AnimationInterfaceFactoryInterface[] getBasicAnimationInterfaceFactoryInterfaceArray() {
        return basicAnimationInterfaceFactoryInterfaceArray;
    }

    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {

    }

}
