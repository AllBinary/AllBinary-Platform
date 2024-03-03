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
public class LeftToRightImageAnimationFactory
        extends BaseImageAnimationFactory {

    public LeftToRightImageAnimationFactory(final Image image, final int dx, final int dy, final int[] sequenceArray)
        throws Exception {
        this(image, sequenceArray, dx, dy, AnimationBehaviorFactory.getInstance());
    }

    public LeftToRightImageAnimationFactory(final Image image, final int[] sequenceArray, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
        throws Exception {
        super(image, sequenceArray, image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory); //, width, height);
    }

    public LeftToRightImageAnimationFactory(final Image image, final int[] sequenceArray)
        throws Exception {
        this(image, sequenceArray, AnimationBehaviorFactory.getInstance());
    }

    public LeftToRightImageAnimationFactory(final Image image, final int[] sequenceArray, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
        throws Exception {
        super(image, sequenceArray, image.getWidth(), image.getHeight(), animationBehaviorFactory); //, width, height);
    }

    public Animation getInstance() throws Exception {
        
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(this.getImage(), width, height, scaleWidth, scaleHeight);
        
        if (dx != 0 || dy != 0) {
            return new AdjustedLeftToRightImageAnimation(scaledImage, this.getSequenceArray(), this.dx, this.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new LeftToRightImageAnimation(scaledImage, this.getSequenceArray(), this.animationBehaviorFactory.getOrCreateInstance());
        }
    }
}
