/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationBehaviorFactory;

/**
 *
 * @author User
 */
public class AllBinaryPlatformImageRotationAnimationFactory extends AllBinaryAndroidImageRotationAnimationFactory {
    
    public AllBinaryPlatformImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory, final boolean resizeCanvasForRotation) throws Exception
    {
        super(image, width, height, angleIncrement, animationBehaviorFactory, resizeCanvasForRotation);
    }
    
}
