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
import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 *
 * @author user
 */
public class RightToLeftImageAnimationFactory
    extends BaseImageAnimationFactory {

//    public RightToLeftImageAnimationFactory(final Image image, final int dx, final int dy)
//        throws Exception {
//        this(image, dx, dy, AnimationBehaviorFactory.getInstance());
//    }

    public RightToLeftImageAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
        throws Exception {
        super(image, PrimitiveIntUtil.getArrayInstance(), image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory); //, width, height);
    }

//    public RightToLeftImageAnimationFactory(final Image image)
//        throws Exception {
//        this(image, AnimationBehaviorFactory.getInstance());
//    }

//    public RightToLeftImageAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory) //, int width, int height)
//        throws Exception {
//        super(image, PrimitiveIntUtil.getArrayInstance(), image.getWidth(), image.getHeight(), 0,0, animationBehaviorFactory); //, width, height);
//    }

    @Override    
    public Animation getInstance(final int instanceId) throws Exception {
        final Image scaledImage = this.animationFactoryImageScaleUtil.createImage(this.getImage(), this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            this.animationFactoryImageScaleUtil.processAdjust(this);
            
            return new AdjustedRightToLeftImageAnimation(scaledImage, this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new RightToLeftImageAnimation(scaledImage, this.animationBehaviorFactory.getOrCreateInstance());
        }
    }
}
