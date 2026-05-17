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
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class AllBinarySpriteRotationAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface {

    //AnimationBehaviorFactory.getInstance()
            //
    public static AllBinarySpriteRotationAnimationFactory createWHDY(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {

        final AllBinarySpriteRotationAnimationFactory spriteRotationAnimationFactory = new AllBinarySpriteRotationAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), (image.getHeight() >> 2), (image.getHeight() >> 2), 0,0, animationBehaviorFactory);

        spriteRotationAnimationFactory.initWH(dx, dy);

        return spriteRotationAnimationFactory;
    }

    public static AllBinarySpriteRotationAnimationFactory createWH(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        return AllBinarySpriteRotationAnimationFactory.createWHDY(image, 0, 0, animationBehaviorFactory);
    }

    public static AllBinarySpriteRotationAnimationFactory createDXY(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        final AllBinarySpriteRotationAnimationFactory spriteRotationAnimationFactory = new AllBinarySpriteRotationAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), image.getHeight(), image.getHeight(), 0,0, animationBehaviorFactory);

        spriteRotationAnimationFactory.init(dx, dy);

        return spriteRotationAnimationFactory;
    }

    public static AllBinarySpriteRotationAnimationFactory createDXYQ(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        final AllBinarySpriteRotationAnimationFactory spriteRotationAnimationFactory = new AllBinarySpriteRotationAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), (image.getHeight() >> 2), (image.getHeight() >> 2), 0,0, animationBehaviorFactory);

        spriteRotationAnimationFactory.init(dx, dy);

        return spriteRotationAnimationFactory;
        
    }

    public static AllBinarySpriteRotationAnimationFactory createQ(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //Future imp may include Control fidelity for non square frames
        //4 rows
        return new AllBinarySpriteRotationAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), (image.getHeight() >> 2), (image.getHeight() >> 2), 0,0, animationBehaviorFactory);
        //int frameSize = (image.getHeight() >> 2);
        //this.width = frameSize;
        //this.height = frameSize;

    }

    public static AllBinarySpriteRotationAnimationFactory createWHF(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        //Future imp may include Control fidelity for non square frames
        return new AllBinarySpriteRotationAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), image.getHeight(), image.getHeight(), 0,0, animationBehaviorFactory);
        //super(image, unused == null ? image.getHeight() : (image.getHeight() >> 2), unused == null ? image.getHeight() : (image.getHeight() >> 2), animationBehaviorFactory);
        //int frameSize = (image.getHeight() >> 2);
        //this.width = frameSize;
        //this.height = frameSize;
    }

    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();

    public AllBinarySpriteRotationAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        super(image, sequenceArray, width, height, dx, dy, animationBehaviorFactory);
    }

    public void init(final int dx, final int dy) {
        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }

    private void initWH(final int dx, final int dy) {
        this.init(dx, dy);
        this.animationFactoryInitializationVisitor.dx += -this.animationFactoryInitializationVisitor.width / 5;
        this.animationFactoryInitializationVisitor.dy += -this.animationFactoryInitializationVisitor.height / 5;
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        
        final Sprite sprite = this.animationFactorySpriteScaleUtil.createImage(this.getImage(), this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            return new AllBinaryAdjustedSpriteRotationAnimation(sprite, this.getImage(), this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new AllBinarySpriteRotationAnimation(sprite, this.getImage(), this.animationBehaviorFactory.getOrCreateInstance());
        }
    }

    @Override
    public Animation getInstanceAnimation(final Animation animationInterface) throws Exception {
        return this.getInstance(0);
    }
}
