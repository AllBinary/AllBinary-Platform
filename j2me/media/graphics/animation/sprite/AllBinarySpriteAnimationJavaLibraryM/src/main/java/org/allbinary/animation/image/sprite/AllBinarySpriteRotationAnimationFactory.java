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
package org.allbinary.animation.image.sprite;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.image.sprite.AnimationFactorySpriteScaleUtil;

public class AllBinarySpriteRotationAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface {

    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused)
        throws Exception {
        
        this(image, dx, dy);

        this.animationFactoryInitializationVisitor.dx += -this.animationFactoryInitializationVisitor.width / 5;
        this.animationFactoryInitializationVisitor.dy += -this.animationFactoryInitializationVisitor.height / 5;
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int dx, final int dy, final int unused)
        throws Exception {
        
        this(image, null, AnimationBehaviorFactory.getInstance());

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }
    
    public AllBinarySpriteRotationAnimationFactory(final Image image, final Object unused, final Object unused2)
        throws Exception {
        
        this(image, 0, 0, unused);
        
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int dx, final int dy)
        throws Exception {
        
        this(image, dx, dy, AnimationBehaviorFactory.getInstance());
        
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        
        this(image, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image)
        throws Exception {
        
        this(image, AnimationBehaviorFactory.getInstance());
        
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        //Future imp may include Control fidelity for non square frames
        //4 rows
        super(image, (image.getHeight() >> 2), (image.getHeight() >> 2), animationBehaviorFactory);
        //int frameSize = (image.getHeight() >> 2);
        //this.width = frameSize;
        //this.height = frameSize;
    }
    
    public AllBinarySpriteRotationAnimationFactory(final Image image, final Object unused)
        throws Exception {
        
        //this(image, unused, AnimationBehaviorFactory.getInstance());
        super(image, (image.getHeight() >> 2), (image.getHeight() >> 2), AnimationBehaviorFactory.getInstance());
        
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        //Future imp may include Control fidelity for non square frames
        super(image, (image.getHeight()), (image.getHeight()), animationBehaviorFactory);
        //super(image, unused == null ? image.getHeight() : (image.getHeight() >> 2), unused == null ? image.getHeight() : (image.getHeight() >> 2), animationBehaviorFactory);
        //int frameSize = (image.getHeight() >> 2);
        //this.width = frameSize;
        //this.height = frameSize;
    }

    public Animation getInstance(final int instanceId) throws Exception {
        
        final Sprite sprite = animationFactorySpriteScaleUtil.createImage(this.getImage(), this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            return new AllBinaryAdjustedSpriteRotationAnimation(sprite, this.getImage(), this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new AllBinarySpriteRotationAnimation(sprite, this.getImage(), this.animationBehaviorFactory.getOrCreateInstance());
        }
    }

    public Animation getInstance(final Animation animationInterface) throws Exception {
        return this.getInstance(0);
    }
}
