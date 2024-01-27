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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.game.layer.SpriteFactory;

public class AllBinarySpriteRotationAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface {

    protected int dx;
    protected int dy;

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int dx, final int dy)
        throws Exception {
        this(image, dx, dy, AnimationBehavior.getInstance());
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehavior animationBehavior)
        throws Exception {
        this(image, animationBehavior);

        this.dx = dx;
        this.dy = dy;
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image)
        throws Exception {
        this(image, AnimationBehavior.getInstance());
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final AnimationBehavior animationBehavior)
        throws Exception {
        //90 degrees per row with 4 rows
        //Future imp may include Control fidelity for non square frames
        super(image, (image.getHeight() >> 2), (image.getHeight() >> 2), animationBehavior);
        //int frameSize = (image.getHeight() >> 2);
        //this.width = frameSize;
        //this.height = frameSize;
    }

    public AllBinarySpriteRotationAnimationFactory(final Image image, final String nullish)
        throws Exception {
        this(image, nullish, AnimationBehavior.getInstance());
    }
    
    public AllBinarySpriteRotationAnimationFactory(final Image image, final String nullish, final AnimationBehavior animationBehavior)
        throws Exception {
        //90 degrees per row with 4 rows
        //Future imp may include Control fidelity for non square frames
        super(image, (image.getHeight()), (image.getHeight()), animationBehavior);
        //int frameSize = (image.getHeight() >> 2);
        //this.width = frameSize;
        //this.height = frameSize;
    }

    public Animation getInstance()
        throws Exception {
        final Sprite sprite = SpriteFactory.getInstance().create(this.getImage(), this.width, this.height);

        if (dx != 0 || dy != 0) {
            return new AllBinaryAdjustedSpriteRotationAnimation(sprite, dx, dy, this.animationBehavior);
        } else {
            return new AllBinarySpriteRotationAnimation(sprite, this.animationBehavior);
        }
    }

    public Animation getInstance(final Animation animationInterface) throws Exception {
        return this.getInstance();
    }
}
