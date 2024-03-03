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
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageToRotationImageArrayUtil;

public class ImageArrayRotationAnimationFactory 
    extends BaseImageAnimationFactory {

    private Image[] imageArray;

    private int angleIncrement;

    public ImageArrayRotationAnimationFactory(final Image image, final int dx, final int dy) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public ImageArrayRotationAnimationFactory(final Image image, final int dx, final int dy, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, animationBehaviorFactory);
    }

    public ImageArrayRotationAnimationFactory(final Image image, final int dx, final int dy, final int angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, AnimationBehaviorFactory.getInstance());
    }
    
    public ImageArrayRotationAnimationFactory(final Image image, final int dx, final int dy, final int angleIncrement, final Object unused, final AnimationBehaviorFactory animationBehaviorFactory) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement, animationBehaviorFactory);
    }

    public ImageArrayRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final int angleIncrement) throws Exception
    {

        this(image, width, height, dx, dy, angleIncrement, AnimationBehaviorFactory.getInstance());
    }
    
    public ImageArrayRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final int angleIncrement, 
            final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, angleIncrement, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }

    public ImageArrayRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy) throws Exception
    {
        this(image, width, height, dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public ImageArrayRotationAnimationFactory(final Image image,
            final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {

        this(image, width, height, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }

    public ImageArrayRotationAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception
    {
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = AngleFactory.getInstance().TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity();
        
        this.init();
    }

    public ImageArrayRotationAnimationFactory(final Image image, final int width, final int height, final int angleIncrement, final AnimationBehaviorFactory animationBehaviorFactory) 
        throws Exception {
        
        super(image, width, height, animationBehaviorFactory);

        this.angleIncrement = angleIncrement;
        
        this.init();
    }
    
    private void init() throws Exception
    {
        this.setImageArray(ImageToRotationImageArrayUtil.getInstance().generate(
                this.getImage(), this.getAngleIncrement(), AngleFactory.getInstance().TOTAL_ANGLE));
    }

    public Animation getInstance() throws Exception
    {
        final Image[] scaledImageArray = new Image[this.imageArray.length];
        final int size = scaledImageArray.length;
        final Image image = this.getImage();
        for(int index = 0; index < size; index++) {
            scaledImageArray[index] = animationFactoryImageScaleUtil.createImage(image, image.getWidth(), image.getHeight(), scaleWidth, scaleHeight);
        }
        
        if (dx != 0 || dy != 0) {
            
            animationFactoryImageScaleUtil.processAdjust(this);
            
            return new AdjustedImageArrayRotationAnimation(
                scaledImageArray,
                AngleInfo.getInstance((short) this.getAngleIncrement()),
                AngleFactory.getInstance().TOTAL_ANGLE, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());

        } else {
            return new ImageArrayRotationAnimation(scaledImageArray,
                AngleInfo.getInstance((short) this.angleIncrement), AngleFactory.getInstance().TOTAL_ANGLE, this.animationBehaviorFactory.getOrCreateInstance());
        }
    }

    protected void setImageArray(final Image[] imageArray)
    {
        this.imageArray = imageArray;
        
//        if(this.imageArray == null) {
//            throw new RuntimeException();
//        } else {
//            final int size = this.imageArray.length;
//            for (int index = 0; index < size; index++) {
//                if (this.imageArray[index] == null) {
//                    throw new RuntimeException("null at: " + index);
//                }
//            } 
//        }
    }

    protected int getAngleIncrement()
    {
        return angleIncrement;
    }
    
    public void setInitialSize(final int width, final int height) {
        this.scaleWidth = width;
        this.scaleHeight = height;
    }
    
}