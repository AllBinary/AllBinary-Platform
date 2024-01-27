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
import android.graphics.Matrix;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.media.image.AndroidImageUtil;

public class AllBinaryFlickerAndroidImageRotationAnimation 
extends ImageBaseRotationAnimation
{
    private final AndroidImageUtil androidImageUtil = AndroidImageUtil.getInstance();
    
    private final Matrix matrix = new Matrix();

    //private final int width;
    //private final int height;

    private final int halfWidth;
    private final int halfHeight;
    
    private final short inc;
    
    private final Image originalImage;
    
    //private short rotation;
    
    protected AllBinaryFlickerAndroidImageRotationAnimation(
            final Image originalImage, final Image image,
            final AngleInfo angleInfo, final short totalAngle, 
            final AnimationBehavior animationBehavior) throws Exception
    {
        super(image, angleInfo, totalAngle, animationBehavior);

        this.originalImage = originalImage;

        this.circularIndexUtil = CircularIndexUtil.getInstance(
                totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement());

        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        this.halfWidth = (image.getWidth() >> 1);
        this.halfHeight = (image.getHeight() >> 1);
        
        inc = (short) (this.angleInfo.getAngleIncrementInfo().getAngleIncrement());
        
        //LogUtil.put(LogFactory.getInstance("inc: " + inc, this, "setRotation"));
    }

    public void nextRotation()
    {
        super.nextRotation();

        matrix.setRotate(inc, this.halfWidth, this.halfHeight);
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        androidImageUtil.rotate(this.getImage(), originalImage, matrix);
    }

    public void previousRotation()
    {
        super.previousRotation();

        matrix.setRotate(-inc, this.halfWidth, this.halfHeight);        
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        androidImageUtil.rotate(this.getImage(), originalImage, matrix);
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

        androidImageUtil.rotate(this.getImage(), originalImage, matrix);
    }  
}