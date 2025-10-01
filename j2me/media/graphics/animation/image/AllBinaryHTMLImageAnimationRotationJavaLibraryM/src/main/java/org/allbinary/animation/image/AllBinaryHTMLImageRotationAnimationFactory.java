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

public class AllBinaryHTMLImageRotationAnimationFactory 
    extends BaseImageAnimationFactory
{ 

    protected final short angleIncrement;

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory);
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused, final short angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, AnimationBehaviorFactory.getInstance());
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, animationBehaviorFactory);
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, 
        final short angleIncrement) throws Exception
    {

        this(image, width, height, angleIncrement, AnimationBehaviorFactory.getInstance());

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;

    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, 
        final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, angleIncrement, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy) throws Exception
    {

        this(image, width, height, AnimationBehaviorFactory.getInstance());

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;
    }
    
    public AllBinaryHTMLImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image)
            throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), AnimationBehaviorFactory.getInstance());
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), animationBehaviorFactory);
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int width, final int height)
            throws Exception
    {
        this(image, width, height, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        this(image, width, height, (short) (AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity()), animationBehaviorFactory);
    }

    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement) throws Exception
    {
        this(image, width, height, angleIncrement, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryHTMLImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = angleIncrement;
    }
    
    public Animation getInstance(final int instanceId) throws Exception
    {
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(this.getImage(), 
            this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, 
            this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
        //final Image image = ImageCopyUtil.getInstance().createImage(this.image);
        //final Image image = ImageCreationUtil.getInstance().getInstance(this.image.getWidth(), this.image.getHeight());
        final Image copyOfScaledImage = ImageCopyUtil.getInstance().createImage(scaledImage);


        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
  
            //logUtil.put(new StringMaker().append(PositionStrings.getInstance().DX_LABEL).append((float) this.animationFactoryInitializationVisitor.dx).append(PositionStrings.getInstance().DY_LABEL).append((float) this.animationFactoryInitializationVisitor.dy).toString(), this, "getInstance");
            animationFactoryImageScaleUtil.processAdjust(this);
            
            return new AllBinaryAdjustedHTMLImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, 
                this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new AllBinaryHTMLImageRotationAnimation(
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