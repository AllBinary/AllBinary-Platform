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
import org.allbinary.graphics.color.BasicColorUtil;
import org.allbinary.image.sprite.AnimationFactorySpriteScaleUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class OneRowSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory {
    
    public static OneRowSpriteIndexedAnimationFactory createFactoryDX(final Image image, final int dx, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {

        final OneRowSpriteIndexedAnimationFactory oneRowSpriteIndexedAnimationFactory = new OneRowSpriteIndexedAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), image.getHeight(), image.getHeight(), 0,0, animationBehaviorFactory);

        oneRowSpriteIndexedAnimationFactory.initW(dx);

        return oneRowSpriteIndexedAnimationFactory;
    }

//    public OneRowSpriteIndexedAnimationFactory(final Image image, final int dx, final int dy, final Object unused)
//        throws Exception {
//
//        this(image, dx, dy);
//
//        this.animationFactoryInitializationVisitor.dx += -(this.animationFactoryInitializationVisitor.width >> 2);
//        this.animationFactoryInitializationVisitor.dy += -(this.animationFactoryInitializationVisitor.height >> 2);
//    }
    
    public static OneRowSpriteIndexedAnimationFactory createFactoryDXY(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        //this(image, dx, dy, AnimationBehaviorFactory.getInstance());
        //this(image, AnimationBehaviorFactory.getInstance());
        //Future imp may include Control fidelity for non square frames
        final OneRowSpriteIndexedAnimationFactory oneRowSpriteIndexedAnimationFactory = new OneRowSpriteIndexedAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), image.getHeight(), image.getHeight(), 0,0, animationBehaviorFactory);

        oneRowSpriteIndexedAnimationFactory.init(dx, dy);

        return oneRowSpriteIndexedAnimationFactory;
    }

    public static OneRowSpriteIndexedAnimationFactory createFactoryWH(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {
        
        //this(width, height, image, AnimationBehaviorFactory.getInstance());
        //Future imp may include Control fidelity for non square frames
        return new OneRowSpriteIndexedAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), width, height, 0,0, animationBehaviorFactory);
    }

    public static OneRowSpriteIndexedAnimationFactory createFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {

        //this(image, AnimationBehaviorFactory.getInstance());
        //Future imp may include Control fidelity for non square frames
        return new OneRowSpriteIndexedAnimationFactory(image, PrimitiveIntUtil.getArrayInstance(), image.getHeight(), image.getHeight(), 0,0, animationBehaviorFactory);

    }   

    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();

    public OneRowSpriteIndexedAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        super(image, sequenceArray, width, height, dx, dy, animationBehaviorFactory);
    }

    public void init(final int dx, final int dy) {
        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }
    public void initW(final int dx) {
        this.init(dx, 0);

        this.animationFactoryInitializationVisitor.dx += -(this.animationFactoryInitializationVisitor.width >> 2);
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        
        final Sprite sprite = this.animationFactorySpriteScaleUtil.createImage(this.getImage(), this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            return new AdjustedSpriteIndexedAnimation(sprite, this.getImage(), BasicColorUtil.getInstance().ZERO_ARRAY, this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new SpriteIndexedAnimation(sprite, this.getImage(), BasicColorUtil.getInstance().ZERO_ARRAY, this.animationBehaviorFactory.getOrCreateInstance());
        }
    }
    
}
