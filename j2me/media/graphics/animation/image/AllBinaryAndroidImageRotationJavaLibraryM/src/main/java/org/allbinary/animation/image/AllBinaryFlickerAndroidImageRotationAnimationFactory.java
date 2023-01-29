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

public class AllBinaryFlickerAndroidImageRotationAnimationFactory 
    implements AnimationInterfaceFactoryInterface
{
    private Image image;

    private final short angleIncrement;

    public AllBinaryFlickerAndroidImageRotationAnimationFactory(final Image image, final int width, final int height)
            throws Exception
    {
        this.setImage(image);
        this.angleIncrement = (short) (AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity());
    }

    public AllBinaryFlickerAndroidImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement) throws Exception
    {
        this.setImage(image);
        this.angleIncrement = angleIncrement;
    }
    
    public Animation getInstance() throws Exception
    {
        final Image image = ImageCopyUtil.getInstance().createImage(this.getImage());
        
        return new AllBinaryFlickerAndroidImageRotationAnimation(
                this.getImage(), image,
                AngleInfo.getInstance(this.angleIncrement), 
                AngleFactory.getInstance().TOTAL_ANGLE);
    }

    protected short getAngleIncrement()
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