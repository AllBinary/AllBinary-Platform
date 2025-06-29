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

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.DisposalUtil;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.media.image.ImageModifierUtil;
import org.allbinary.media.image.ImageRotationUtil;

public class AllBinaryJ2SEImageRotationAnimation 
    extends ImageBaseRotationAnimation
    //implements AutoCloseable
{
    private final ImageRotationUtil imageRotationUtil = ImageRotationUtil.getInstance();
    private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();

    //private final int width;
    //private final int height;

    //private final int halfWidth;
    //private final int halfHeight;
    
    //private final float increment;
    
    private final Image realOriginalImage;
    private final Image[] originalImageArray = new Image[1];
    
    private final Image[] twoImages = new Image[2];
    
    private Image imageToShow;
    private int bufferedImageIndex;
    
    private float scaleX;
    private float scaleY;
    private float maxScaleX;
    private float maxScaleY;
    
    private ModifierBaseProcessor alphaProcessor = ModifierBaseProcessor.getInstance();
    private ModifierBaseProcessor setColorProcessor = ModifierBaseProcessor.getInstance();
    private ModifierBaseProcessor changeColorProcessor = ModifierBaseProcessor.getInstance();
    private ScaleBaseProcessor scaleProcessor = ScaleProcessor.getInstance();
    
    protected AllBinaryJ2SEImageRotationAnimation(
            final Image originalImage, final Image image,
            final AngleInfo angleInfo, final short totalAngle,
            final AnimationBehavior animationBehavior) throws Exception
    {
        super(image, angleInfo, totalAngle, animationBehavior);

        this.realOriginalImage = originalImage;
        this.originalImageArray[0] = originalImage;
        
        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        //this.halfWidth = (this.getImage().getWidth() >> 1);
        //this.halfHeight = (this.getImage().getHeight() >> 1);
        
        //this.increment = (short)(this.angleInfo.getAngleIncrementInfo().getAngleIncrement() * 2.44);
        
        this.imageToShow = image;
        this.twoImages[0] = image;
        this.twoImages[1] = ImageCopyUtil.getInstance().createImageForRotation(image);
        
        //LogUtil.put(LogFactory.getInstance(this.toString(), this, commonStrings.CONSTRUCTOR));
    }

    @Override
    public void setBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColor() == null || this.getBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.setBasicColor(basicColor);
        
        if(changed) {
            this.setColorProcessor = SetColorProcessor.getInstance();
            this.updateImage();
        }
    }

    @Override
    public void changeBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColor() == null || this.getBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.changeBasicColor(basicColor);
        
        if(changed) {
            this.changeColorProcessor = ChangeColorProcessor.getInstance();
            this.updateImage();
        }
    }
    
    @Override
    public void setAlpha(final int alpha) {
        
        boolean changed = false;
        if(this.alpha != alpha) {
            changed = true;
        }
        
        super.setAlpha(alpha);

        if(changed) {
            this.alphaProcessor = AlphaProcessor.getInstance();
            this.updateImage();
        }
    }

    @Override
    public void setScale(final float scaleX, final float scaleY) {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("scaleX: ").append(scaleX).append("scaleY: ").append(scaleY).toString(), this, "setScale"));
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleProcessor.update(this.realOriginalImage, this.originalImageArray, this.twoImages, this.bufferedImageIndex, this.scaleX, this.scaleY, this.maxScaleX, this.maxScaleY);
        this.updateImage();
    }

    @Override
    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
        //LogUtil.put(LogFactory.getInstance("maxScaleX: " + maxScaleX, this, "setMaxScale"));
        this.maxScaleX = maxScaleX;
        this.maxScaleY = maxScaleY;
        this.scaleProcessor.update(this.realOriginalImage, this.originalImageArray, this.twoImages, this.bufferedImageIndex, this.scaleX, this.scaleY, this.maxScaleX, this.maxScaleY);
        this.updateImage();
    }
    
    public void nextRotation()
    {
        super.nextRotation();
        this.updateImage();
    }

    public void previousRotation()
    {
        super.previousRotation();
        this.updateImage();
    }

    private void updateImage() {

        this.imageRotationUtil.rotateImage(this.originalImageArray[0], this.twoImages[this.bufferedImageIndex], this.angleInfo.getAngle() + 90);
        this.alphaProcessor.update(imageModifierUtil, null, this.twoImages[this.bufferedImageIndex], 0, this.alpha);
        this.setColorProcessor.update(imageModifierUtil, null, this.twoImages[this.bufferedImageIndex], 0, this.basicColor);
        this.changeColorProcessor.update(imageModifierUtil, null, this.twoImages[this.bufferedImageIndex], 0, this.changeBasicColor);
        this.swap();
    }

    public void setFrame(final int index)
    {
        //LogUtil.put(LogFactory.getInstance("index: " + index, this, "setRotation"));

        //final int currentFrame = this.circularIndexUtil.getIndex();
        //LogUtil.put(LogFactory.getInstance("currentFrame: " + currentFrame, this, "setRotation"));
        
        super.setFrame(index);

        this.updateImage();
    }
    
    public void swap() {
        
        this.imageToShow = this.twoImages[this.bufferedImageIndex];

        if(this.bufferedImageIndex == 0) {
            this.bufferedImageIndex = 1;
        } else {
            this.bufferedImageIndex = 0;
        }
    }
    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        graphics.drawImage(this.imageToShow, x, y, anchor);
    }

    public void close() throws Exception {
        super.close();

        final DisposalUtil disposalUtil = DisposalUtil.getInstance();
        
        final int size2 = this.twoImages.length;
        for(int index = 0; index < size2; index++) {
            disposalUtil.dispose(this.twoImages[index]);
        }

        final int size = this.originalImageArray.length;
        for(int index = 0; index < size; index++) {
            disposalUtil.dispose(this.originalImageArray[index]);
        }
        
        disposalUtil.dispose(this.realOriginalImage);
        disposalUtil.dispose(this.imageToShow);
    }
 
    protected void finalize() throws Throwable {
        super.finalize();
        
        final DisposalUtil disposalUtil = DisposalUtil.getInstance();
        
        final int size2 = this.twoImages.length;
        for(int index = 0; index < size2; index++) {
            disposalUtil.dispose(this.twoImages[index]);
        }

        final int size = this.originalImageArray.length;
        for(int index = 0; index < size; index++) {
            disposalUtil.dispose(this.originalImageArray[index]);
        }
        
        disposalUtil.dispose(this.realOriginalImage);
        disposalUtil.dispose(this.imageToShow);
    }
    
}