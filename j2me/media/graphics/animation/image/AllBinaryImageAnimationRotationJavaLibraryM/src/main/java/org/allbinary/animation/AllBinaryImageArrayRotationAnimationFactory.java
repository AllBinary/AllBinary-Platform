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
package org.allbinary.animation;

import javax.microedition.lcdui.Image;

import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageToRotationImageArrayUtil;

public class AllBinaryImageArrayRotationAnimationFactory 
    implements AnimationInterfaceFactoryInterface
{
    private Image image;
    // private int width;
    // private int height;

    private Image[] imageArray;

    private int angleIncrement;

    public AllBinaryImageArrayRotationAnimationFactory(Image image, int width, int height)
            throws Exception
    {
        this.image = image;
        // this.width = width;
        // this.height = height;

        this.angleIncrement = AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity();

        this.init();
    }

    public AllBinaryImageArrayRotationAnimationFactory(Image image, int width, int height,
            int angleIncrement) throws Exception
    {
        this.image = image;
        // this.width = width;
        // this.height = height;

        this.angleIncrement = angleIncrement;

        this.init();
    }
    
    private void init() throws Exception
    {
        this.setImageArray(ImageToRotationImageArrayUtil.getInstance().generate(
                this.image, this.getAngleIncrement(), AngleFactory.getInstance().TOTAL_ANGLE));
    }

    public Animation getInstance() throws Exception
    {
        // return new AllBinarySpriteRotationAnimation(new MESprite(image,
        // width, height), dx, dy);

        return new AllBinaryImageArrayRotationAnimation(this.getImageArray(),
                AngleInfo.getInstance((short) this.angleIncrement), AngleFactory.getInstance().TOTAL_ANGLE);

    }

    protected void setImageArray(Image[] imageArray)
    {
        this.imageArray = imageArray;
    }

    protected Image[] getImageArray()
    {
        return imageArray;
    }

    protected int getAngleIncrement()
    {
        return angleIncrement;
    }
}