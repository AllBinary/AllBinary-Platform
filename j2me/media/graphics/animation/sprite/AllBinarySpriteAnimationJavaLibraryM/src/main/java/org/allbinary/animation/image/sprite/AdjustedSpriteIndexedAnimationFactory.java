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
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.game.layer.SpriteFactory;

public class AdjustedSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{
    private int dx;
    private int dy;

    public AdjustedSpriteIndexedAnimationFactory(
        final Image image, final int width, final int height, final int dx, final int dy)
        throws Exception
    {
        super(image, width, height);

        this.dx = dx;
        this.dy = dy;
    }

    public Animation getInstance() throws Exception
    {
        final Sprite sprite = SpriteFactory.getInstance().create(this.getImage(), this.width, this.height);

        return new AdjustedSpriteIndexedAnimation(sprite, dx, dy);
    }

    public Animation getInstance(final Animation animationInterface)
        throws Exception
    {
        return this.getInstance();
    }
}
