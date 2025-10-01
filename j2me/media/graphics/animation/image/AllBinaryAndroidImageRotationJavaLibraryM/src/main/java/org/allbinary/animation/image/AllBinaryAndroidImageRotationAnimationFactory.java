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
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class AllBinaryAndroidImageRotationAnimationFactory 
    extends BaseImageAnimationFactory
{
    private final short angleIncrement;
    private final boolean resizeCanvasForRotation;

    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final Object unused) 
    throws Exception
    {
        this(image, unused, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), -(image.getWidth() >> 2), -(image.getHeight() >> 2), animationBehaviorFactory);
    }
    
    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehaviorFactory.getInstance());
    }

    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory);
    }
        
    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused, final short angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, AnimationBehaviorFactory.getInstance());
    }
        
    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int dx, final int dy, final short angleIncrement, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, animationBehaviorFactory);
    }
    
    public AllBinaryAndroidImageRotationAnimationFactory(Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, angleIncrement, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;
    }
    
    public AllBinaryAndroidImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) 
        throws Exception {

        this(image, width, height, animationBehaviorFactory);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;
    }

    public AllBinaryAndroidImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory, final boolean resizeCanvasForRotation) throws Exception
    {

        this(image, width, height, angleIncrement, animationBehaviorFactory, resizeCanvasForRotation);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        this.animationFactoryInitializationVisitor.originalDx = dx;
        this.animationFactoryInitializationVisitor.originalDy = dy;
    }

    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = (short) (AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity());
        this.resizeCanvasForRotation = false;
    }

    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = angleIncrement;
        this.resizeCanvasForRotation = false;
    }

    public AllBinaryAndroidImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory, final boolean resizeCanvasForRotation) throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = angleIncrement;
        this.resizeCanvasForRotation = resizeCanvasForRotation;
    }

    private Image getCanvasImage() throws Exception {
        final Features features = Features.getInstance();
        if(this.resizeCanvasForRotation && !features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL)) {
            return ImageCopyUtil.getInstance().createImage(this.getImage(), 1.44f, false);
        } else {
            return this.getImage();
        }
    }
    
//    private Image secondImage = null;
    
    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {
        final Image canvasImage = this.getCanvasImage();
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(canvasImage, 
            this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, 
            this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
//        if(this.resizeCanvasForRotation) {
//            if (this.animationFactoryInitializationVisitor.dx == 0 || this.animationFactoryInitializationVisitor.dy == 0) {
//                this.animationFactoryInitializationVisitor.dx -= scaledImage.getWidth() / 8;
//                this.animationFactoryInitializationVisitor.dy -= scaledImage.getHeight() / 8;
//                this.animationFactoryInitializationVisitor.originalDx = this.animationFactoryInitializationVisitor.dx;
//                this.animationFactoryInitializationVisitor.originalDy = this.animationFactoryInitializationVisitor.dy;
//            }
//        }
        final Image copyOfScaledImage = ImageCopyUtil.getInstance().createImage(scaledImage);
//        Image copyOfScaledImage = secondImage;
//        if(copyOfScaledImage == null) {
//            secondImage = copyOfScaledImage = ImageCopyUtil.getInstance().createImage(scaledImage);
//        }

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            
            //logUtil.put(new StringMaker().append(PositionStrings.getInstance().DX_LABEL).append((float) this.animationFactoryInitializationVisitor.dx).append(PositionStrings.getInstance().DY_LABEL).append((float) this.animationFactoryInitializationVisitor.dy).toString(), this, "getInstance");
            animationFactoryImageScaleUtil.processAdjust(this);
            
            return new AllBinaryAdjustedAndroidImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, 
                this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, animationBehaviorFactory.getOrCreateInstance());

        } else {
            //return new AllBinaryNoFlickerAndroidImageRotationAnimation(
            return new AllBinaryFlickerAndroidImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, this.animationBehaviorFactory.getOrCreateInstance());

        }
        
    }

}