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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class AllBinaryAndroidImageRotationAnimationFactory 
    implements AnimationInterfaceFactoryInterface
{
    private Image image;

    private int angleIncrement;

    public AllBinaryAndroidImageRotationAnimationFactory(Image image, int width, int height)
            throws Exception
    {
        this.setImage(image);
        this.angleIncrement = AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity();
    }

    public AllBinaryAndroidImageRotationAnimationFactory(Image image, int width, int height,
            int angleIncrement) throws Exception
    {
        this.setImage(image);
        this.angleIncrement = angleIncrement;
    }
    
    public Animation getInstance() throws Exception
    {
        Image image = ImageCopyUtil.getInstance().createImage(this.getImage());
        
        return new AllBinaryAndroidImageRotationAnimation(
                this.getImage(), image,
                AngleInfo.getInstance(getAngleIncrement()), 
                AngleFactory.getInstance().TOTAL_ANGLE);
    }

    protected int getAngleIncrement()
    {
        return angleIncrement;
    }

    protected void setImage(Image image)
    {
        this.image = image;
    }

    protected Image getImage()
    {
        return image;
    }
}