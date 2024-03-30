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
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;

import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.math.AngleFactory;
import org.allbinary.media.ScaleProperties;
import org.allbinary.media.image.ImageToRotationImageArrayUtil;

//TWB - Adjustments should be done in the resource creation and not at the animation level
public class PooledImageArrayRotationAnimationFactory implements
        AnimationInterfaceFactoryInterface
{

    // private int width;
    // private int height;

    private ImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo;
    private final AnimationBehaviorFactory animationBehaviorFactory;
    
    public PooledImageArrayRotationAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        this.animationBehaviorFactory = animationBehaviorFactory;

        // this(image, image.getWidth(), image.getHeight());
        this.init(image, image.getWidth(), image.getHeight(),
                -(image.getWidth() >> 2), -(image.getHeight() >> 2));
    }

    
//    public PooledAllBinaryImageArrayRotationAnimationFactory(MEImage image, int width, int height) throws Exception {
//
//        this.init(image, width, height, -(width >> 2), -(height >> 2));
//    }

    public PooledImageArrayRotationAnimationFactory(final Image image,
            final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        this.animationBehaviorFactory = animationBehaviorFactory;
        
        this.init(image, image.getWidth(), image.getHeight(), dx, dy);
    }

    public PooledImageArrayRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        this.animationBehaviorFactory = animationBehaviorFactory;
        
        this.init(image, width, height, dx, dy);
    }

    public PooledImageArrayRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        this.animationBehaviorFactory = animationBehaviorFactory;
        
        this.init(image, width, height, dx, dy, angleIncrement);
    }
    
    private void init(final Image image, final int width, final int height, final int dx, final int dy)
            throws Exception
    {
        final int totalAngle = AngleFactory.getInstance().TOTAL_ANGLE;
        
        final short angleIncrement = (short) (totalAngle / GameConfigurationCentral.getInstance().getGameControlFidelity());
        
        this.init(image, width, height, dx, dy, angleIncrement);
    }
    
    private void init(final Image image, final int width, final int height, final int dx, final int dy, final short angleIncrement)
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
        // return new AllBinarySpriteRotationAnimation(new MESprite(image, width, height), dx, dy);

        // return new AllBinaryImageRotationAnimation(this.imageArray,
        // AngleInfo.getInstance(angleIncrement), totalAngle, dx, dy);

        return new AdjustedImageArrayRotationAnimation(allBinaryImageRotationAnimationInfo, this.animationBehaviorFactory.getOrCreateInstance());
        //return (AnimationInterface) AllBinaryImageArrayRotationAnimationPool
          //      .getInstance().remove(allBinaryImageRotationAnimationInfo);
    }
    
    public void setInitialScale(final ScaleProperties scaleProperties) {
        
    }
    
}
