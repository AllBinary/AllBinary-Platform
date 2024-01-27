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
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.game.layer.SpriteFactory;
import org.allbinary.graphics.color.BasicColorUtil;

public class SpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{

    public SpriteIndexedAnimationFactory(final Image image, final int width, final int height)
        throws Exception
    {
        this(image, width, height, AnimationBehaviorFactory.getInstance());
    }

    public SpriteIndexedAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception
    {
        super(image, width, height, animationBehaviorFactory);
    }

    public Animation getInstance() throws Exception
    {
        final Sprite sprite = SpriteFactory.getInstance().create(this.getImage(), this.width, this.height);

        return new SpriteIndexedAnimation(sprite, BasicColorUtil.getInstance().ZERO_ARRAY, this.animationBehaviorFactory.getOrCreateInstance());
    }

    public Animation getInstance(final Animation animationInterface) throws Exception
    {
        return this.getInstance();
    }
}
