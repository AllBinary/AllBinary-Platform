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
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.image.sprite.AnimationFactorySpriteScaleUtil;

public class OneRowSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory {

    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();

    public OneRowSpriteIndexedAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        super(image, width, height, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }
    
    public OneRowSpriteIndexedAnimationFactory(final Image image, final int dx)
        throws Exception {

        this(image, dx, 0);

        this.animationFactoryInitializationVisitor.dx += -(this.animationFactoryInitializationVisitor.width >> 2);
    }

    public OneRowSpriteIndexedAnimationFactory(final Image image, final int dx, final int dy, final Object unused)
        throws Exception {

        this(image, dx, dy);

        this.animationFactoryInitializationVisitor.dx += -(this.animationFactoryInitializationVisitor.width >> 2);
        this.animationFactoryInitializationVisitor.dy += -(this.animationFactoryInitializationVisitor.height >> 2);
    }
    
    public OneRowSpriteIndexedAnimationFactory(final Image image, final int dx, final int dy)
        throws Exception {
        
        this(image, dx, dy, AnimationBehaviorFactory.getInstance());
    }
   
    public OneRowSpriteIndexedAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        this(image, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }
    
    public OneRowSpriteIndexedAnimationFactory(final int width, final int height, final Image image)
        throws Exception {
        
        this(width, height, image, AnimationBehaviorFactory.getInstance());
    }

    public OneRowSpriteIndexedAnimationFactory(final int width, final int height, final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //Future imp may include Control fidelity for non square frames
        super(image, width, height, animationBehaviorFactory);
    }

    public OneRowSpriteIndexedAnimationFactory(final Image image)
           throws Exception {
        
        this(image, AnimationBehaviorFactory.getInstance());
    }   
    
    public OneRowSpriteIndexedAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //Future imp may include Control fidelity for non square frames
        super(image, image.getHeight(), image.getHeight(), animationBehaviorFactory);
    }

    public Animation getInstance(final int instanceId) throws Exception {
        
        final Sprite sprite = animationFactorySpriteScaleUtil.createImage(this.getImage(), this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            return new AdjustedSpriteIndexedAnimation(sprite, this.getImage(), this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new SpriteIndexedAnimation(sprite, this.getImage(), this.animationBehaviorFactory.getOrCreateInstance());
        }
    }
    
}
