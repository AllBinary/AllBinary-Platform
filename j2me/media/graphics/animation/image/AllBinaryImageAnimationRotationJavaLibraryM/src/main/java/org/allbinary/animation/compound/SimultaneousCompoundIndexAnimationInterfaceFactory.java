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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;

public class SimultaneousCompoundIndexAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface {

    private final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray;
    private final AnimationBehavior animationBehavior;

    public SimultaneousCompoundIndexAnimationInterfaceFactory(
        final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray,
        final AnimationBehavior animationBehavior) {
        
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
        this.animationBehavior = animationBehavior;

    }

    public Animation getInstance() throws Exception {
        final int size = this.basicAnimationInterfaceFactoryInterfaceArray.length;
        final IndexedAnimation[] animationInterfaceArray = new IndexedAnimation[size];

        for (int index = 0; index < size; index++) {
            animationInterfaceArray[index] = (IndexedAnimation) this.basicAnimationInterfaceFactoryInterfaceArray[index].getInstance();
        }

        return this.getInstance(animationInterfaceArray);
    }

    protected Animation getInstance(final IndexedAnimation[] animationInterfaceArray) {
        return new SimultaneousCompoundIndexAnimation(animationInterfaceArray, this.animationBehavior);
    }

    public AnimationInterfaceFactoryInterface[] getBasicAnimationInterfaceFactoryInterfaceArray() {
        return basicAnimationInterfaceFactoryInterfaceArray;
    }

    public void setInitialSize(final int width, final int height) {

    }

}
