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
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.media.ScaleProperties;

public class CompoundAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface {

    protected final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArrayP;
    protected final AnimationBehaviorFactory animationBehaviorFactory;

    public CompoundAnimationInterfaceFactory(
        final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray,
        final AnimationBehaviorFactory animationBehaviorFactory) {
        this.basicAnimationInterfaceFactoryInterfaceArrayP = basicAnimationInterfaceFactoryInterfaceArray;
        this.animationBehaviorFactory = animationBehaviorFactory;
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        final int size = this.basicAnimationInterfaceFactoryInterfaceArrayP.length;
        final Animation[] animationInterfaceArray = this.createArray(size);

        for (int index = 0; index < size; index++) {
            animationInterfaceArray[index] = this.basicAnimationInterfaceFactoryInterfaceArrayP[index].getInstance(instanceId);
        }

        return this.getInstance(animationInterfaceArray);
    }

    protected Animation[] createArray(final int size) {
        return NullAnimationFactory.getFactoryInstance().EMPTY_ARRAY;
    }
    
    protected Animation getInstance(final Animation[] animationInterfaceArray) {
        return NullAnimationFactory.getFactoryInstance().getInstance(0);
    }
    
    public AnimationInterfaceFactoryInterface[] getBasicAnimationInterfaceFactoryInterfaceArray() {
        return basicAnimationInterfaceFactoryInterfaceArrayP;
    }
    
    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {
        final int size = this.basicAnimationInterfaceFactoryInterfaceArrayP.length;

        for (int index = 0; index < size; index++) {
            this.basicAnimationInterfaceFactoryInterfaceArrayP[index].setInitialScale(scaleProperties);
        }
    }

}
