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
import org.allbinary.math.AngleFactory;
import org.allbinary.media.image.ImageToRotationImageArrayUtil;

//TWB - Adjustments should be done in the resource creation and not at the animation level
public class PooledImageArrayRotationAnimationFactory implements
        AnimationInterfaceFactoryInterface
{

    // private int width;
    // private int height;

    private ImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo;

    public PooledImageArrayRotationAnimationFactory(Image image)
            throws Exception
    {
        // this(image, image.getWidth(), image.getHeight());
        this.init(image, image.getWidth(), image.getHeight(),
                -(image.getWidth() >> 2), -(image.getHeight() >> 2));
    }

    /*
     * public PooledAllBinaryImageArrayRotationAnimationFactory(MEImage image,
     * int width, int height) throws Exception {
     * 
     * this.init(image, width, height, -(width >> 2), -(height >> 2)); }
     */

    public PooledImageArrayRotationAnimationFactory(Image image,
            int dx, int dy) throws Exception
    {
        this.init(image, image.getWidth(), image.getHeight(), dx, dy);
    }

    public PooledImageArrayRotationAnimationFactory(Image image,
            int width, int height, int dx, int dy) throws Exception
    {
        this.init(image, width, height, dx, dy);
    }

    public PooledImageArrayRotationAnimationFactory(Image image,
            int width, int height, int dx, int dy, short angleIncrement) throws Exception
    {
        this.init(image, width, height, dx, dy, angleIncrement);
    }
    
    private void init(Image image, int width, int height, int dx, int dy)
            throws Exception
    {
        final int totalAngle = AngleFactory.getInstance().TOTAL_ANGLE;
        
        final short angleIncrement = (short) (totalAngle / GameConfigurationCentral.getInstance().getGameControlFidelity());
        
        this.init(image, width, height, dx, dy, angleIncrement);
    }
    
    private void init(Image image, int width, int height, int dx, int dy, short angleIncrement)
            throws Exception
    {

        final int totalAngle = AngleFactory.getInstance().TOTAL_ANGLE;
        
        // this.width = width;
        // this.height = height;

        final Image[] imageArray = ImageToRotationImageArrayUtil.getInstance().generate(image,
                angleIncrement, totalAngle);

        allBinaryImageRotationAnimationInfo = new ImageArrayRotationAnimationInfo(
                imageArray, angleIncrement, totalAngle, dx, dy);
    }

    public Animation getInstance() throws Exception
    {
        // return new AllBinarySpriteRotationAnimation(new MESprite(image,
        // width, height), dx, dy);

        // return new AllBinaryImageRotationAnimation(this.imageArray,
        // AngleInfo.getInstance(angleIncrement), totalAngle, dx, dy);

        return new AdjustedImageArrayRotationAnimation(
                allBinaryImageRotationAnimationInfo);
        //return (AnimationInterface) AllBinaryImageArrayRotationAnimationPool
          //      .getInstance().remove(allBinaryImageRotationAnimationInfo);
    }
    
    public void setInitialSize(final int width, final int height) {
        
    }
    
}
