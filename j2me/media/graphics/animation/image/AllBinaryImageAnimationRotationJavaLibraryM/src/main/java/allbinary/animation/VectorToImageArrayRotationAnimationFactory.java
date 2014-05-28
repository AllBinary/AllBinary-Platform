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
package allbinary.animation;

import javax.microedition.lcdui.Image;

import allbinary.game.configuration.GameConfigurationCentral;
import allbinary.graphics.color.BasicColor;
import allbinary.image.AnimationFrameToImageUtil;
import allbinary.math.AngleFactory;
import allbinary.math.AngleInfo;
import allbinary.vector.VectorInfo;
import org.allbinary.media.image.ImageToRotationImageArrayUtil;

public class VectorToImageArrayRotationAnimationFactory implements
        AnimationInterfaceFactoryInterface
{
    private Image image;
    private Image[] imageArray;

    private int angleIncrement;

    public VectorToImageArrayRotationAnimationFactory(VectorInfo vectorInfo,
            BasicColor basicColor) throws Exception
    {
        this.setImage(AnimationFrameToImageUtil.getInstance().getInstanceTranslate(
                vectorInfo.getWidth(), vectorInfo.getHeight(), 
                new VectorAnimation(vectorInfo.getPoints(), basicColor)));

        this.init();
    }

    protected void init() throws Exception
    {
        AngleFactory angleFactory = AngleFactory.getInstance();
        
        this.angleIncrement = angleFactory.TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity();

        this.setImageArray(ImageToRotationImageArrayUtil.getInstance().generate(
                this.getImage(), this.getAngleIncrement(), angleFactory.TOTAL_ANGLE));
    }

    public Animation getInstance() throws Exception
    {
        // return new AllBinarySpriteRotationAnimation(new MESprite(image,
        // width, height), dx, dy);

        return new AllBinaryAdjustedImageArrayRotationAnimation(
                this.getImageArray(), AngleInfo.getInstance(getAngleIncrement()), 
                AngleFactory.getInstance().TOTAL_ANGLE);
    }

    protected Image getImage()
    {
        return image;
    }

    protected int getAngleIncrement()
    {
        return angleIncrement;
    }

    protected void setImageArray(Image[] imageArray)
    {
        this.imageArray = imageArray;
    }

    protected Image[] getImageArray()
    {
        return imageArray;
    }

    protected void setImage(Image image)
    {
        this.image = image;
    }
}