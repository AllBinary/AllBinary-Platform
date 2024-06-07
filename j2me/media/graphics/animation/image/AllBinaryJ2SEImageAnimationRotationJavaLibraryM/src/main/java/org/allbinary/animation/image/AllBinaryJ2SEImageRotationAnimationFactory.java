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
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class AllBinaryJ2SEImageRotationAnimationFactory 
    extends BaseImageAnimationFactory
{
    protected final short angleIncrement;

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int dx, final int dy)
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory)
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory);
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement, final Object unused) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, AnimationBehaviorFactory.getInstance());
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, animationBehaviorFactory);
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement) throws Exception
    {

        this(image, width, height, dx, dy, angleIncrement, AnimationBehaviorFactory.getInstance());
        
    }
    
    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, angleIncrement, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy) throws Exception
    {

        this(image, width, height, dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image)
            throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), animationBehaviorFactory);
    }
    
    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = (short) (AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity());
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = angleIncrement;
    }
    
    public Animation getInstance() throws Exception {
        
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(this.getImage(), width, height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
        //final Image image = ImageCopyUtil.getInstance().createImage(this.image);
        final Image copyOfScaledImage = ImageCopyUtil.getInstance().createImage(scaledImage);

        if (dx != 0 || dy != 0) {

            animationFactoryImageScaleUtil.processAdjust(this);

            return new AllBinaryAdjustedJ2SEImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, this.dx, this.dy, this.animationBehaviorFactory.getOrCreateInstance());

        } else {
            return new AllBinaryJ2SEImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, this.animationBehaviorFactory.getOrCreateInstance());
        }
        
    }

    protected short getAngleIncrement()
    {
        return angleIncrement;
    }
        
}