/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.animation.image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimationBehavior;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author User
 */
public class LazyImageRotationAnimationFactory implements AnimationInterfaceFactoryInterface {
    
    private final BaseImageAnimationFactory animationInterfaceFactoryInterface;
    
    public LazyImageRotationAnimationFactory(final BaseImageAnimationFactory animationInterfaceFactoryInterface) {
        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
    }
    
    public Animation getInstance() throws Exception {
        return new LazyImageRotationAnimation(this.animationInterfaceFactoryInterface, this.animationInterfaceFactoryInterface.animationBehaviorFactory.getOrCreateInstance());
    }
    
    public void setInitialScale(final ScaleProperties scaleProperties) {
        this.animationInterfaceFactoryInterface.setInitialScale(scaleProperties);
    }
    
}
