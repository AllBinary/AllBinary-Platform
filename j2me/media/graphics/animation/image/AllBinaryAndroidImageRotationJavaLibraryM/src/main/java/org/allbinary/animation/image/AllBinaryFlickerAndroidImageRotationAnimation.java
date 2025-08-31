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

import android.graphics.Matrix;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.AndroidImageUtil;
import org.allbinary.media.image.ImageModifierUtil;

public class AllBinaryFlickerAndroidImageRotationAnimation 
extends ImageBaseRotationAnimation
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();
    private final AndroidImageUtil androidImageUtil = AndroidImageUtil.getInstance();
    
    private final Matrix matrix = new Matrix();

    //private final int width;
    //private final int height;

    private final int halfWidth;
    private final int halfHeight;
    
    private final short increment;
    
    private final Image originalImage;
    
    protected AllBinaryFlickerAndroidImageRotationAnimation(
            final Image originalImage, final Image image,
            final AngleInfo angleInfo, final short totalAngle, 
            final AnimationBehavior animationBehavior) throws Exception
    {
        super(image, angleInfo, totalAngle, animationBehavior);

        this.originalImage = originalImage;

        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        this.halfWidth = (image.getWidth() >> 1);
        this.halfHeight = (image.getHeight() >> 1);
        
        this.increment = (short) (this.angleInfo.getAngleIncrementInfo().getAngleIncrement());
        
        //logUtil.put(this.toString(), this, commonStrings.CONSTRUCTOR);
    }
    
    public void setBasicColorP(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColorP() == null || this.getBasicColorP().intValue() != basicColor.intValue()) {
            imageModifierUtil.setColor(this.originalImage, this.getImage(), 0, basicColor);
            changed = true;
        }
        
        super.setBasicColorP(basicColor);

        if(changed) {
            matrix.setRotate(0, this.halfWidth, this.halfHeight);
            this.updateImage();
        }
    }

    @Override
    public void changeBasicColor(final BasicColor basicColor) {
        
        this.setBasicColorP(basicColor);
    }
    
    public void setAlpha(final int alpha) {
        
        boolean changed = false;
        if(this.alphaP != alpha) {
            changed = true;
        }
        
        super.setAlpha(alpha);

        imageModifierUtil.setAlpha(this.originalImage, this.getImage(), 0, this.alphaP);

        if(changed) {
            matrix.setRotate(0, this.halfWidth, this.halfHeight);
            this.updateImage();
        }
    }
    
    public void nextRotation()
    {
        super.nextRotation();
        //logUtil.put("Frame: " + this.getFrame(), this, "nextRotation");

        matrix.setRotate(this.increment, this.halfWidth, this.halfHeight);
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        this.updateImage();
    }

    public void previousRotation()
    {
        super.previousRotation();
        //logUtil.put("Frame: " + this.getFrame(), this, "previousRotation");

        matrix.setRotate(-this.increment, this.halfWidth, this.halfHeight);        
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        this.updateImage();
    }

    private void updateImage() {

        androidImageUtil.rotate(this.getImage(), originalImage, matrix, imageModifierUtil.paint);
    }
    
    public void setFrame(final int index)
    {
        //logUtil.put(commonLabels.INDEX_LABEL + index, this, "setRotation");

        final int currentFrame = this.circularIndexUtil.getIndex();
        //logUtil.put("currentFrame: " + currentFrame, this, "setRotation");
        
        this.circularIndexUtil.setIndex(index);

        final int newFrame = this.circularIndexUtil.getIndex();
        //logUtil.put("newFrame: " + newFrame, this, "setRotation");

        this.angleInfo.adjustAngle(newFrame);

        matrix.setRotate((newFrame - currentFrame) * increment, this.halfWidth, this.halfHeight);

        this.updateImage();
    }  
}