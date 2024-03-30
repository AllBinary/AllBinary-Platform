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
package org.allbinary.animation.image.sprite;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Player;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.caption.CaptionIndexedAnimation;
import org.allbinary.animation.image.ImageAnimation;
import org.allbinary.image.AnimationFactoryImageScaleUtil;
import org.allbinary.image.sprite.AnimationFactorySpriteScaleUtil;
import org.allbinary.media.ScaleProperties;
import org.allbinary.media.audio.Sound;

public class ImageCaptionIndexedAnimationFactory
    implements AnimationInterfaceFactoryInterface {

    protected final AnimationFactoryImageScaleUtil animationFactoryImageScaleUtil = AnimationFactoryImageScaleUtil.getInstance();
    private final AnimationFactorySpriteScaleUtil animationFactorySpriteScaleUtil = AnimationFactorySpriteScaleUtil.getInstance();

    private Image captionImage;
    private Image spriteMovieImage;

    private int frameWidth;
    private int frameHeight;

    private int captionDx;
    private int captionDy;

    private int dx;
    private int dy;

    private int time;

    private Sound soundInterface;

    public ScaleProperties scaleProperties = ScaleProperties.instance;
    
    private final AnimationBehaviorFactory animationBehaviorFactory;

    public ImageCaptionIndexedAnimationFactory(
        final Image captionImage, final Image spriteMovieImage,
        final Sound soundInterface,
        final int frameWidth, final int frameHeight,
        final int captionDx, final int captionDy, final int dx, final int dy, final int time) {
        this(captionImage, spriteMovieImage, soundInterface, frameWidth, frameHeight, captionDx, captionDy, dx, dy, time, AnimationBehaviorFactory.getInstance());
    }

    public ImageCaptionIndexedAnimationFactory(
        final Image captionImage, final Image spriteMovieImage,
        final Sound soundInterface,
        final int frameWidth, final int frameHeight,
        final int captionDx, final int captionDy, final int dx, final int dy, final int time,
        final AnimationBehaviorFactory animationBehaviorFactory) {
        this.captionImage = captionImage;
        this.spriteMovieImage = spriteMovieImage;

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.captionDx = captionDx;
        this.captionDy = captionDy;

        this.dx = dx;
        this.dy = dy;

        this.time = time;

        this.soundInterface = soundInterface;

        this.animationBehaviorFactory = animationBehaviorFactory;

    }

    public Animation getInstance() throws Exception {
        final Image scaledImage = animationFactoryImageScaleUtil.createImage(this.captionImage, this.captionImage.getWidth(), this.captionImage.getHeight(), this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);
        final Animation animationInterface = new ImageAnimation(scaledImage, this.animationBehaviorFactory.getOrCreateInstance());

        final Sprite sprite = animationFactorySpriteScaleUtil.createImage(this.spriteMovieImage, this.frameWidth, this.frameHeight, this.scaleProperties.scaleWidth, this.scaleProperties.scaleHeight);

        final IndexedAnimation movieIndexedAnimationInterface = new SpriteIndexedAnimation(sprite, this.animationBehaviorFactory.getOrCreateInstance());

        Player player = this.soundInterface.getPlayer();

        if (player == null) {
            throw new Exception("Sound Was not Initialized");
        }

        return new CaptionIndexedAnimation(animationInterface, movieIndexedAnimationInterface,
            player, this.captionDx, this.captionDy, dx, dy, time, this.animationBehaviorFactory.getOrCreateInstance());
    }

    public void setInitialScale(final ScaleProperties scaleProperties) {
        
        this.scaleProperties = scaleProperties;
        
    }

}
