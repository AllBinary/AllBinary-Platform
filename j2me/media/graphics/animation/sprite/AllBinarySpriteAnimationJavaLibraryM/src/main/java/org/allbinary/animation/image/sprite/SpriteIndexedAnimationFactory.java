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

public class SpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{

    public SpriteIndexedAnimationFactory(final Image image, final int width, final int height)
        throws Exception
    {
        super(image, width, height);
    }

    public Animation getInstance() throws Exception
    {
        Sprite sprite = new Sprite(this.getImage(), this.width, this.height);

        return new SpriteIndexedAnimation(sprite);
    }

    public Animation getInstance(final Animation animationInterface) throws Exception
    {
        return this.getInstance();
    }
}
