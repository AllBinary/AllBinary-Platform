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
        this(image, width, height, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }
    
    public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        this(image, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }

    public OneRowJ2SESpriteIndexedAnimationFactory(final int width, final int height, final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //90 degrees per row with 4 rows
        //Future imp may include Control fidelity for non square frames
        super(image, width, height, animationBehaviorFactory);
    }

    public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //90 degrees per row with 4 rows
        //Future imp may include Control fidelity for non square frames
        super(image, image.getHeight(), image.getHeight(), animationBehaviorFactory);
    }

    public Animation getInstance()
        throws Exception {

        final Sprite sprite = animationFactorySpriteScaleUtil.createImage(this.getImage(), width, height, scaleWidth, scaleHeight);

        if (dx != 0 || dy != 0) {
            return new AdjustedSpriteIndexedAnimation(sprite, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new SpriteIndexedAnimation(sprite, this.animationBehaviorFactory.getOrCreateInstance());
        }
    }

    public void setInitialSize(final int width, final int height) {
        this.scaleWidth = width;
        this.scaleHeight = height;
    }

}
