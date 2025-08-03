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
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.image.sprite.AnimationFactorySpriteScaleUtil;

public class ColorCompositeSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{
    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();
    
    private final BasicColor[] basicColorArray;

    public ColorCompositeSpriteIndexedAnimationFactory(
        final Image image, final BasicColor[] basicColorArray, final int width, final int height)
        throws Exception
    {
        this(image, basicColorArray, width, height, AnimationBehaviorFactory.getInstance());
    }
    
    public ColorCompositeSpriteIndexedAnimationFactory(
        final Image image, final BasicColor[] basicColorArray, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.basicColorArray = basicColorArray;
        //        this.dx = - (this.width >> 2);
        //      this.dy = - (this.height >> 2);
    }

    /*
    public SpriteIndexedAnimationFactory(MEImage image, int width, int height, int dx, int dy)
    throws Exception
    {
    this.image = image;
    this.width = width;
    this.height = height;
    this.dx = dx;
    this.dy = dy;
    }
     */

    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {
        final Sprite sprite = animationFactorySpriteScaleUtil.createImage(this.getImage(), 
            this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, 
            this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        return new SpriteIndexedAnimation(sprite, this.getImage(), this.basicColorArray, this.animationBehaviorFactory.getOrCreateInstance());
    }

    @Override
    public Animation getInstance(final Animation animationInterface) throws Exception
    {
        return this.getInstance(0);
    }
}
