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
import org.allbinary.media.ScaleProperties;

public class CompoundAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface {

    protected AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray;
    protected final AnimationBehaviorFactory animationBehaviorFactory;

    public CompoundAnimationInterfaceFactory(
        final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray,
        final AnimationBehaviorFactory animationBehaviorFactory) {
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
        this.animationBehaviorFactory = animationBehaviorFactory;
    }

    public Animation getInstance(final int instanceId) throws Exception {
        final int size = this.basicAnimationInterfaceFactoryInterfaceArray.length;
        final Animation[] animationInterfaceArray = this.createArray(size);

        for (int index = 0; index < size; index++) {
            animationInterfaceArray[index] = this.basicAnimationInterfaceFactoryInterfaceArray[index].getInstance(instanceId);
        }

        return this.getInstance(animationInterfaceArray);
    }

    protected Animation[] createArray(final int size) {
        return null;
    }
    
    protected Animation getInstance(final Animation[] animationInterfaceArray) {
        return null;
    }
    
    public AnimationInterfaceFactoryInterface[] getBasicAnimationInterfaceFactoryInterfaceArray() {
        return basicAnimationInterfaceFactoryInterfaceArray;
    }
    
    public void setInitialScale(final ScaleProperties scaleProperties) {
        final int size = this.basicAnimationInterfaceFactoryInterfaceArray.length;

        for (int index = 0; index < size; index++) {
            this.basicAnimationInterfaceFactoryInterfaceArray[index].setInitialScale(scaleProperties);
        }
    }

}
