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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;

/**
 *
 * @author user
 */
public class TopToBottomImageAnimationFactory
        extends BaseImageAnimationFactory {

    public TopToBottomImageAnimationFactory(final Image image, final int[] sequenceArray)
        throws Exception {
        this(image, sequenceArray, AnimationBehaviorFactory.getInstance());
    }

    public TopToBottomImageAnimationFactory(final Image image, final int[] sequenceArray, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
        throws Exception {
        super(image, sequenceArray, image.getWidth(), image.getHeight(), animationBehaviorFactory); //, width, height);
    }

    public Animation getInstance(final int instanceId) throws Exception {
        
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(this.getImage(), 
            this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, 
            this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
        
        return new TopToBottomImageAnimation(scaledImage, this.getSequenceArray(), this.animationBehaviorFactory.getOrCreateInstance());
    }
}
