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

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.RotationAnimation;

/**
 *
 * @author User
 */
public class LazyImageRotationAnimation extends RotationAnimation {
 
    private final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface;
    
    public LazyImageRotationAnimation(final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface, final AnimationBehavior animationBehavior) {
        super(animationBehavior);
        
        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
        
    }
}
