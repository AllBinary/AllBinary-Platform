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
import org.allbinary.graphics.opengles.OpenGLUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.PositionStrings;
import org.allbinary.media.image.ImageCopyUtil;

public class AllBinaryJ2SEImageRotationAnimationFactory 
    extends BaseImageAnimationFactory
{
    protected final short angleIncrement;
    private final boolean resizeCanvasForRotation;
    
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

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory, final boolean resizeCanvasForRotation) throws Exception
    {

        this(image, width, height, angleIncrement, animationBehaviorFactory, resizeCanvasForRotation);

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;

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

        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
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
        this.resizeCanvasForRotation = false;
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int width, final int height,
            final short angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = angleIncrement;
        this.resizeCanvasForRotation = false;
    }

    public AllBinaryJ2SEImageRotationAnimationFactory(final Image image, final int width, final int height,
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
    
    final LogUtil logUtil = LogUtil.getInstance();
    public Animation getInstance(final int instanceId) throws Exception {
        
        final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();
        final Image canvasImage = this.getCanvasImage();
        Image scaledImage = animationFactoryImageScaleUtil.createImage(canvasImage, this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
        final OpenGLUtil openGLUtil = OpenGLUtil.getInstance();
        scaledImage = openGLUtil.add(scaledImage);
        //final Image image = imageCopyUtil.createImage(this.image);
        final Image copyOfScaledImage = imageCopyUtil.createImageForRotation(scaledImage);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {

            logUtil.put(new StringMaker().append(PositionStrings.getInstance().DX_LABEL).append((float) this.animationFactoryInitializationVisitor.dx).append(PositionStrings.getInstance().DY_LABEL).append((float) this.animationFactoryInitializationVisitor.dy).toString(), this, "getInstance");
            animationFactoryImageScaleUtil.processAdjust(this);

            return new AllBinaryAdjustedJ2SEImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());

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