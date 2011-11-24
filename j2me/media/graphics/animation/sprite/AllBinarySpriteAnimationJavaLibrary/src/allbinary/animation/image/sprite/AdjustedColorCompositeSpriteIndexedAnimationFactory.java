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
package allbinary.animation.image.sprite;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import allbinary.AndroidUtil;
import allbinary.animation.Animation;
import allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import allbinary.animation.image.BaseImageAnimationFactory;
import allbinary.graphics.color.BasicColor;

//TWB - Adjustments should be done in the resource creation and not at the animation level
public class AdjustedColorCompositeSpriteIndexedAnimationFactory
    extends BaseImageAnimationFactory
    implements ProceduralAnimationInterfaceFactoryInterface
{
    private int dx;
    private int dy;
    
    private BasicColor[] basicColorArray;

    public AdjustedColorCompositeSpriteIndexedAnimationFactory(
            Image image, BasicColor[] basicColorArray, int width, int height)
            throws Exception
        {
            super(image, width, height);

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
        Sprite sprite = new Sprite(this.getImage(),
              this.width, this.height);

        return new AdjustedSpriteIndexedAnimation(sprite, this.basicColorArray, this.dx, this.dy);
    }

    public Animation getInstance(Animation animationInterface) throws Exception
    {
        return this.getInstance();
    }
}
