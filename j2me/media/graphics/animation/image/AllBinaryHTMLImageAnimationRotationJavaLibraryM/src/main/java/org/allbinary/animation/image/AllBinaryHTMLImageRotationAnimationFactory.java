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
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageScaleUtil;

public class AllBinaryHTMLImageRotationAnimationFactory 
    implements AnimationInterfaceFactoryInterface
{ 
    private final ImageCache imageCache = ImageCacheFactory.getInstance();
    private final ImageScaleUtil imageScaleUtil = ImageScaleUtil.getInstance();
    
    protected Image image;

    protected final short angleIncrement;
    protected final AnimationBehaviorFactory animationBehaviorFactory;

    public int width;
    public int height;

    public int scaleWidth;
    public int scaleHeight;

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
        this.image = image;
        this.angleIncrement = angleIncrement;
        this.animationBehaviorFactory = animationBehaviorFactory;
        
        this.width = width;
        this.height = height;
    }
    
    public Animation getInstance() throws Exception
    {
        Image scaledImage;

//        final CommonStrings commonStrings = CommonStrings.getInstance();
//        final StringMaker stringMaker = new StringMaker();
        if (scaleWidth != 0 && scaleHeight != 0) {
            final float scaleX = ((float) scaleWidth) / ((float) this.width);
            final float scaleY = ((float) scaleHeight) / ((float) this.height);
//            stringMaker.delete(0, stringMaker.length());
//            LogUtil.put(LogFactory.getInstance(stringMaker.append("width: ").append(width).append(" height: ").append(height).toString(), this, commonStrings.PROCESS));
//            stringMaker.delete(0, stringMaker.length());
//            LogUtil.put(LogFactory.getInstance(stringMaker.append("0scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
            if ((scaleX == 1 && scaleY == 1) || (scaleX == 0 || scaleY == 0)) {
                scaledImage = ImageCopyUtil.getInstance().createImage(this.image);
            } else {
//                stringMaker.delete(0, stringMaker.length());
//                LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
                scaledImage = imageScaleUtil.createImage(imageCache, this.image, scaleX, 1, scaleY, 1, true);
//                stringMaker.delete(0, stringMaker.length());
//                LogUtil.put(LogFactory.getInstance(stringMaker.append("scaledImage.getHeight(): ").append(scaledImage.getHeight()).append(" this.height * scaleY: ").append(this.height * scaleY).toString(), this, commonStrings.PROCESS));
            }

        } else {
            scaledImage = ImageCopyUtil.getInstance().createImage(this.image);
        }
        
        //final Image image = ImageCopyUtil.getInstance().createImage(this.image);
        //final Image image = ImageCreationUtil.getInstance().getInstance(this.image.getWidth(), this.image.getHeight());
        final Image copyOfScaledImage = ImageCopyUtil.getInstance().createImage(scaledImage);
        
        return new AllBinaryHTMLImageRotationAnimation(
                scaledImage, copyOfScaledImage,
                AngleInfo.getInstance(this.angleIncrement), 
                AngleFactory.getInstance().TOTAL_ANGLE, this.animationBehaviorFactory.getOrCreateInstance());
    }

    protected short getAngleIncrement()
    {
        return angleIncrement;
    }

    public void setInitialSize(final int width, final int height) {
        this.scaleWidth = width;
        this.scaleHeight = height;
    }

}