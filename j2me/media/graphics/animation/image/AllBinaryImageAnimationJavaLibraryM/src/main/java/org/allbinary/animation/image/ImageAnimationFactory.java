/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
import org.allbinary.graphics.opengles.OpenGLUtil;
import org.allbinary.media.image.ImageCopyUtil;

/**
 *
 * @author User
 */
public class ImageAnimationFactory extends BaseImageAnimationFactory {

    public ImageAnimationFactory(final Image image)
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), 0, 0, AnimationBehaviorFactory.getInstance());
    }
    
    public ImageAnimationFactory(final Image image, final int dx, final int dy)
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehaviorFactory.getInstance());
    }

    public ImageAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
    throws Exception
    {
        super(image, width, height, 0, 0, animationBehaviorFactory);
    }
    
    public ImageAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
    throws Exception
    {
        super(image, width, height, dx, dy, animationBehaviorFactory);
    }
    
    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {
        //final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();
        Image scaledImage = animationFactoryImageScaleUtil.createImage(this.getImage(), 
            this.animationFactoryInitializationVisitor.width, this.animationFactoryInitializationVisitor.height, 
            this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
        final OpenGLUtil openGLUtil = OpenGLUtil.getInstance();
        scaledImage = openGLUtil.add(scaledImage);
        //final Image image = imageCopyUtil.createImage(this.image);
        //final Image copyOfScaledImage = imageCopyUtil.createImageForRotation(scaledImage);
        
        //final Image image = ImageCopyUtil.getInstance().createImage(this.getImage());
        //final Image copyOfScaledImage = ImageCopyUtil.getInstance().createImage(scaledImage);

        if (this.animationFactoryInitializationVisitor.dx != 0 || this.animationFactoryInitializationVisitor.dy != 0) {
            
            animationFactoryImageScaleUtil.processAdjust(this);
            
            return new AdjustedImageAnimation(scaledImage, this.animationFactoryInitializationVisitor.dx, this.animationFactoryInitializationVisitor.dy, this.animationBehaviorFactory.getOrCreateInstance());

        } else {
            return new ImageAnimation(scaledImage, this.animationBehaviorFactory.getOrCreateInstance());
        }
        
    }
    
}