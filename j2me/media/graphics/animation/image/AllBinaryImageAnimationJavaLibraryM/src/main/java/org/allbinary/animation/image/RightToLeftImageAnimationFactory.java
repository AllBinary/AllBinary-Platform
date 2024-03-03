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
public class RightToLeftImageAnimationFactory
    extends BaseImageAnimationFactory {

    public RightToLeftImageAnimationFactory(final Image image, final int dx, final int dy)
        throws Exception {
        this(image, dx, dy, AnimationBehaviorFactory.getInstance());
    }

    public RightToLeftImageAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
        throws Exception {
        super(image, image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory); //, width, height);        
    }

    public RightToLeftImageAnimationFactory(final Image image)
        throws Exception {
        this(image, AnimationBehaviorFactory.getInstance());
    }

    public RightToLeftImageAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
        throws Exception {
        super(image, image.getWidth(), image.getHeight(), animationBehaviorFactory); //, width, height);
    }
    
    public Animation getInstance() throws Exception {
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(this.getImage(), width, height, scaleWidth, scaleHeight);

        if (dx != 0 || dy != 0) {
            return new AdjustedRightToLeftImageAnimation(scaledImage, this.dx, this.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new RightToLeftImageAnimation(scaledImage, this.animationBehaviorFactory.getOrCreateInstance());
        }
    }
}
