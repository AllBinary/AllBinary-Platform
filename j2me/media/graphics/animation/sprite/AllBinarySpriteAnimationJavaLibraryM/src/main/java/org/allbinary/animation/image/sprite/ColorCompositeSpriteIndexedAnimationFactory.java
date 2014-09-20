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
import org.allbinary.graphics.color.BasicColor;

public class ColorCompositeSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{
    //private int dx;
    //private int dy;

    private BasicColor[] basicColorArray;

    public ColorCompositeSpriteIndexedAnimationFactory(
        Image image, BasicColor[] basicColorArray, int width, int height)
        throws Exception
    {
        super(image, width, height);

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

    public Animation getInstance() throws Exception
    {
        Sprite sprite = new Sprite(this.getImage(), this.width, this.height);

        return new SpriteIndexedAnimation(sprite, this.basicColorArray);
    }

    public Animation getInstance(Animation animationInterface) throws Exception
    {
        return this.getInstance();
    }
}
