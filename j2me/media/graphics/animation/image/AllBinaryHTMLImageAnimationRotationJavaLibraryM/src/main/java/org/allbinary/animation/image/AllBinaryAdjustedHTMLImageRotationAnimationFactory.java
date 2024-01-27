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

import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageCopyUtil;

public class AllBinaryAdjustedHTMLImageRotationAnimationFactory 
    extends AllBinaryHTMLImageRotationAnimationFactory
{
    private final int dx;
    private final int dy;

    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehavior.getInstance());
    }
    
    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehavior animationBehavior) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, animationBehavior);
    }

    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, AnimationBehavior.getInstance());
    }

    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement, final AnimationBehavior animationBehavior) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, animationBehavior);
    }

    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, 
        final short angleIncrement) throws Exception
    {

        super(image, width, height, angleIncrement, AnimationBehavior.getInstance());

        this.dx = dx;
        this.dy = dy;
    }

    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, 
        final short angleIncrement, final AnimationBehavior animationBehavior) throws Exception
    {

        super(image, width, height, angleIncrement, animationBehavior);

        this.dx = dx;
        this.dy = dy;
    }

    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy) throws Exception
    {

        super(image, width, height, AnimationBehavior.getInstance());

        this.dx = dx;
        this.dy = dy;
    }
    
    public AllBinaryAdjustedHTMLImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehavior animationBehavior) throws Exception
    {

        super(image, width, height, animationBehavior);

        this.dx = dx;
        this.dy = dy;
    }

    public Animation getInstance() throws Exception
    {
        final Image image = ImageCopyUtil.getInstance().createImage(this.image);
        //final Image image = ImageCreationUtil.getInstance().getInstance(this.image.getWidth(), this.image.getHeight());

        return new AllBinaryAdjustedHTMLImageRotationAnimation(
                this.image, image,
                AngleInfo.getInstance(this.angleIncrement), 
                AngleFactory.getInstance().TOTAL_ANGLE, this.dx, this.dy, this.animationBehavior);
    }

}
