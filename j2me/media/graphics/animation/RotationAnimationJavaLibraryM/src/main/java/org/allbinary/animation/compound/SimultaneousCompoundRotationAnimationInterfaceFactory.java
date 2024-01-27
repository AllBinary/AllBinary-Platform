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
import org.allbinary.animation.RotationAnimation;

public class SimultaneousCompoundRotationAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface {

    private final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray;
    protected final AnimationBehaviorFactory animationBehaviorFactory;

    public SimultaneousCompoundRotationAnimationInterfaceFactory(
        final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray,
        final AnimationBehaviorFactory animationBehaviorFactory) {
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
        this.animationBehaviorFactory = animationBehaviorFactory;
    }

    public Animation getInstance() throws Exception {
        int size = this.basicAnimationInterfaceFactoryInterfaceArray.length;
        RotationAnimation[] animationInterfaceArray = new RotationAnimation[size];

        for (int index = 0; index < size; index++) {
            animationInterfaceArray[index] = (RotationAnimation) this.basicAnimationInterfaceFactoryInterfaceArray[index].getInstance();
        }

        return this.getInstance(animationInterfaceArray);
    }

    protected Animation getInstance(final RotationAnimation[] animationInterfaceArray) {
        return new SimultaneousCompoundRotationAnimation(animationInterfaceArray, this.animationBehaviorFactory.getOrCreateInstance());
    }

    public AnimationInterfaceFactoryInterface[] getBasicAnimationInterfaceFactoryInterfaceArray() {
        return basicAnimationInterfaceFactoryInterfaceArray;
    }

    public void setInitialSize(final int width, final int height) {

    }

}
