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


import org.allbinary.math.AngleInfo;
import android.graphics.Matrix;
import javax.microedition.lcdui.Graphics;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.media.image.AndroidImageUtil;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.media.image.ImageModifierUtil;

public class AllBinaryNoFlickerAndroidImageRotationAnimation 
extends AllBinaryImageBaseRotationAnimation
{
    private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();
    private final AndroidImageUtil androidImageUtil = AndroidImageUtil.getInstance();
    
    private final Matrix matrix = new Matrix();

    //private final int width;
    //private final int height;

    private final int halfWidth;
    private final int halfHeight;
    
    private final float increment;
    
    private final Image originalImage;
    
    private final Image[] twoImages = new Image[2];
    
    private Image imageToShow;
    private int bufferedImageIndex;
    
    protected AllBinaryNoFlickerAndroidImageRotationAnimation(
            final Image originalImage, final Image image,
            final AngleInfo angleInfo, final short totalAngle) throws Exception
    {
        super(image, angleInfo, totalAngle);

        this.originalImage = originalImage;
        
        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        this.halfWidth = (this.getImage().getWidth() >> 1);
        this.halfHeight = (this.getImage().getHeight() >> 1);
        
        this.increment = (short)(this.angleInfo.getAngleIncrementInfo().getAngleIncrement() * 2.44);
        
        this.imageToShow = image;
        this.twoImages[0] = image;
        this.twoImages[1] = ImageCopyUtil.getInstance().createImage(image);
        
        //LogUtil.put(LogFactory.getInstance(this.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
    }

    public void setBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColor() == null || this.getBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.setBasicColor(basicColor);
        
        imageModifierUtil.setBasicColor(basicColor);
        
        if(changed) {
            matrix.setRotate(0, this.halfWidth, this.halfHeight);
            this.updateImage();
        }
    }
    
    public void setAlpha(final int alpha) {
        
        boolean changed = false;
        if(this.alpha != alpha) {
            changed = true;
        }
        
        super.setAlpha(alpha);

        imageModifierUtil.setAlpha(this.originalImage, this.imageToShow, 0, this.alpha);

        if(changed) {
            matrix.setRotate(0, this.halfWidth, this.halfHeight);
            this.updateImage();
        }
    }
    
    public void nextRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());

        matrix.setRotate(this.increment, this.halfWidth, this.halfHeight);
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);
        
        this.updateImage();
    }

    public void previousRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.previous());

        matrix.setRotate(-this.increment, this.halfWidth, this.halfHeight);
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);
        
        this.updateImage();
    }

    private void updateImage() {

        androidImageUtil.rotate(this.twoImages[this.bufferedImageIndex], originalImage, matrix, imageModifierUtil.paint);
        this.swap();
    }

    public void setFrame(final int index)
    {
        //LogUtil.put(LogFactory.getInstance("index: " + index, this, "setRotation"));

        final int currentFrame = this.circularIndexUtil.getIndex();
        //LogUtil.put(LogFactory.getInstance("currentFrame: " + currentFrame, this, "setRotation"));
        
        this.circularIndexUtil.setIndex(index);

        final int newFrame = this.circularIndexUtil.getIndex();
        //LogUtil.put(LogFactory.getInstance("newFrame: " + newFrame, this, "setRotation"));
        
        this.angleInfo.adjustAngle(newFrame);
        
        matrix.setRotate((newFrame - currentFrame) * this.increment, this.halfWidth, this.halfHeight);

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
    
    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.imageToShow, x, y, anchor);
    }
    
}