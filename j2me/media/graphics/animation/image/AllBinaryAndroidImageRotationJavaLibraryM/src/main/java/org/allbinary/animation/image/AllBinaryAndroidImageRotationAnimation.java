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

public class AllBinaryAndroidImageRotationAnimation 
extends AllBinaryImageBaseRotationAnimation
{
    private final Matrix matrix = new Matrix();

    //private final int width;
    //private final int height;

    private final int halfWidth;
    private final int halfHeight;
    
    private final int inc;
    
    private final Image originalImage;
    
    protected AllBinaryAndroidImageRotationAnimation(
            Image originalImage, Image image,
            AngleInfo angleInfo, short totalAngle) throws Exception
    {
        super(image, angleInfo, totalAngle);

        this.originalImage = originalImage;

        this.circularIndexUtil = CircularIndexUtil.getInstance(
                totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement());

        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        this.halfWidth = (image.getWidth() >> 1);
        this.halfHeight = (image.getHeight() >> 1);
        
        inc = this.angleInfo.getAngleIncrementInfo().getAngleIncrement();
    }

    public void nextRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());

        matrix.setRotate(inc, this.halfWidth, this.halfHeight);
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        Image image = this.getImage();
        image.getBitmap().eraseColor(Color.TRANSPARENT);
        image.getCanvas().concat(matrix);
        image.getGraphics().drawImage(originalImage, 0, 0, 0);
    }

    public void previousRotation()
    {
        this.angleInfo.adjustAngle(this.circularIndexUtil.previous());

        matrix.setRotate(-inc, this.halfWidth, this.halfHeight);        
        //matrix.setRotate(this.angleInfo.getAngle(), this.halfWidth, this.halfHeight);

        Image image = this.getImage();
        image.getBitmap().eraseColor(Color.TRANSPARENT);
        image.getCanvas().concat(matrix);
        image.getGraphics().drawImage(originalImage, 0, 0, 0);
    }

    public void setFrame(int index)
    {
        int currentFrame = this.circularIndexUtil.getIndex();
        
        this.circularIndexUtil.setIndex(index);

        int newFrame = this.circularIndexUtil.getIndex();
        
        this.angleInfo.adjustAngle(newFrame);
                
        if(newFrame > currentFrame)
        {
            matrix.setRotate((newFrame - currentFrame) * inc, this.halfWidth, this.halfHeight);
            Image image = this.getImage();
            image.getBitmap().eraseColor(Color.TRANSPARENT);
            image.getCanvas().concat(matrix);
            image.getGraphics().drawImage(originalImage, 0, 0, 0);
        }
        else
            if(newFrame < currentFrame)
        {
            matrix.setRotate((currentFrame - newFrame) * -inc, this.halfWidth, this.halfHeight);
            Image image = this.getImage();
            image.getBitmap().eraseColor(Color.TRANSPARENT);
            image.getCanvas().concat(matrix);
            image.getGraphics().drawImage(originalImage, 0, 0, 0);
        }
    }  
}