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

public class OneRowJ2SESpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory {

    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();

    public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        super(image, width, height, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }
    
    public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        this(image, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }

    public OneRowJ2SESpriteIndexedAnimationFactory(final int width, final int height, final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //Future imp may include Control fidelity for non square frames
        super(image, width, height, animationBehaviorFactory);
    }

    public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //Future imp may include Control fidelity for non square frames
        super(image, image.getHeight(), image.getHeight(), animationBehaviorFactory);
    }

    public Animation getInstance(final int instanceId) throws Exception {

        final Sprite sprite = animationFactorySpriteScaleUtil.createImage(this.getImage(), 
            this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, 
            this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            return new AdjustedSpriteIndexedAnimation(sprite, this.getImage(), 
                this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new SpriteIndexedAnimation(sprite, this.getImage(), this.animationBehaviorFactory.getOrCreateInstance());
        }
    }

}
