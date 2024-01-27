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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class AllBinaryAdjustedAndroidImageRotationAnimationFactory 
    extends AllBinaryFlickerAndroidImageRotationAnimationFactory
    //extends AllBinaryNoFlickerAndroidImageRotationAnimationFactory
{
    private final int dx;
    private final int dy;

    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image) 
    throws Exception
    {
        this(image, AnimationBehavior.getInstance());
    }
    
    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image, final AnimationBehavior animationBehavior) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), -(image.getWidth() >> 2), -(image.getHeight() >> 2), animationBehavior);
    }
    
    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehavior.getInstance());
    }

    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehavior animationBehavior) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, animationBehavior);
    }
        
    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, AnimationBehavior.getInstance());
    }
        
    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement, final AnimationBehavior animationBehavior) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, animationBehavior);
    }
    
    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement, final AnimationBehavior animationBehavior) throws Exception
    {

        super(image, width, height, angleIncrement, animationBehavior);

        this.dx = dx;
        this.dy = dy;
    }
    
    public AllBinaryAdjustedAndroidImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehavior animationBehavior) 
        throws Exception {

        super(image, width, height);

        this.dx = dx;
        this.dy = dy;
    }

    public Animation getInstance() throws Exception
    {
        final Image image = ImageCopyUtil.getInstance().createImage(this.getImage());
        
        return new AllBinaryAdjustedAndroidImageRotationAnimation(
                this.getImage(), image, 
                AngleInfo.getInstance(this.getAngleIncrement()), 
                AngleFactory.getInstance().TOTAL_ANGLE, dx, dy, animationBehavior);
    }

}
