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

import org.allbinary.AndroidUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.game.layer.SpriteFactory;
import org.allbinary.graphics.color.BasicColor;

//TWB - Adjustments should be done in the resource creation and not at the animation level
public class AdjustedColorCompositeSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{
    private int dx;
    private int dy;

    private BasicColor[] basicColorArray;

    public AdjustedColorCompositeSpriteIndexedAnimationFactory(
            final Image image, final BasicColor[] basicColorArray, final int width, final int height, final int dx, final int dy)
            throws Exception
    {
        this(image, basicColorArray, width, height, dx, dy, AnimationBehaviorFactory.getInstance());
    }

    public AdjustedColorCompositeSpriteIndexedAnimationFactory(
            final Image image, final BasicColor[] basicColorArray, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
        {
            super(image, width, height, animationBehaviorFactory);

            this.basicColorArray = basicColorArray;
            ////this.dx = - (this.width >> 2);
            ////this.dy = - (this.height >> 2);
            
            ////this.dx = - this.width / 12;
            ////this.dy = - this.height / 12;
            //this.dx = - this.width / 20;
            //this.dy = - this.height / 10;

            //J2ME
            this.dx = dx;
            this.dy = dy;
        }

    public AdjustedColorCompositeSpriteIndexedAnimationFactory(
            final Image image, final BasicColor[] basicColorArray, final int width, final int height)
            throws Exception
    {
            this(image, basicColorArray, width, height, AnimationBehaviorFactory.getInstance());
    }
    
    public AdjustedColorCompositeSpriteIndexedAnimationFactory(
            final Image image, final BasicColor[] basicColorArray, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
        {
            super(image, width, height, animationBehaviorFactory);

            this.basicColorArray = basicColorArray;
            ////this.dx = - (this.width >> 2);
            ////this.dy = - (this.height >> 2);

            ////this.dx = - this.width / 12;
            ////this.dy = - this.height / 12;
            //this.dx = - this.width / 20;
            //this.dy = - this.height / 10;

            //J2ME
            this.dx = - (this.width >> 2);
            this.dy = - (this.height >> 2);

            if(AndroidUtil.isAndroid())
            {
                this.dx+= 3;
            }
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
        final Sprite sprite = SpriteFactory.getInstance().create(this.getImage(), this.width, this.height);

        return new AdjustedSpriteIndexedAnimation(sprite, this.basicColorArray, this.dx, this.dy, this.animationBehaviorFactory.getOrCreateInstance());
    }

    public Animation getInstance(Animation animationInterface) throws Exception
    {
        return this.getInstance();
    }
}
