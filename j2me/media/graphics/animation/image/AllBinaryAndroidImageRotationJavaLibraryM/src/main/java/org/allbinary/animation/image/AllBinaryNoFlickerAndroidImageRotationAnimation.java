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

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.math.AngleInfo;
import android.graphics.Color;
import android.graphics.Matrix;
import javax.microedition.lcdui.Graphics;
import org.allbinary.media.image.AndroidImageUtil;
import org.allbinary.media.image.ImageCopyUtil;

public class AllBinaryNoFlickerAndroidImageRotationAnimation 
extends AllBinaryImageBaseRotationAnimation
{
    private final AndroidImageUtil androidImageUtil = AndroidImageUtil.getInstance();
    
    private final Matrix matrix = new Matrix();

    //private final int width;
    //private final int height;

    private final int halfWidth;
    private final int halfHeight;
    
    private final int inc;
    
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
        
        this.circularIndexUtil = CircularIndexUtil.getInstance(
                totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement());

        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        this.halfWidth = (this.getImage().getWidth() >> 1);
        this.halfHeight = (this.getImage().getHeight() >> 1);
        
        inc = this.angleInfo.getAngleIncrementInfo().getAngleIncrement();
        
        this.imageToShow = image;
        this.twoImages[0] = image;
        this.twoImages[1] = ImageCopyUtil.getInstance().createImage(image);
        
        //LogUtil.put(LogFactory.getInstance("inc: " + inc, this, "setRotation"));
    }

    public void nextRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());

        matrix.setRotate(inc, this.halfWidth, this.halfHeight);
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        androidImageUtil.rotate(this.twoImages[this.bufferedImageIndex], originalImage, matrix);
        this.imageToShow = this.twoImages[this.bufferedImageIndex];
        this.swap();
    }

    public void previousRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.previous());

        matrix.setRotate(-inc, this.halfWidth, this.halfHeight);        
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        androidImageUtil.rotate(this.twoImages[this.bufferedImageIndex], originalImage, matrix);     
        this.imageToShow = this.twoImages[this.bufferedImageIndex];
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
                
        matrix.setRotate((newFrame - currentFrame) * inc, this.halfWidth, this.halfHeight);
        androidImageUtil.rotate(this.twoImages[this.bufferedImageIndex], originalImage, matrix);     
        this.imageToShow = this.twoImages[this.bufferedImageIndex];
        this.swap();
    }
    
    public void swap() {
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