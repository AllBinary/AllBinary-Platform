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

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.animation.RotationAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageModifierUtil;

public class AllBinaryImageArrayBaseRotationAnimation extends RotationAnimation
{
    private final ImageModifierUtil imageModifierUtil = new ImageModifierUtil();
    
    private Image[] imageArray;

    private Image currentImage;
    
    private int totalFrames;

    public AllBinaryImageArrayBaseRotationAnimation(Image[] imageArray, AngleInfo angleInfo) throws Exception
    {
        super(angleInfo);

        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));

        this.setImageArray(imageArray);
        
        this.currentImage = this.imageArray[this.circularIndexUtil.getIndex()]; 
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }

    public void setAlpha(final int alpha) {
        
        if(this.alpha != alpha) {

            super.setAlpha(alpha);

            final int size = this.imageArray.length;
            //LogUtil.put(LogFactory.getInstance("total: " + size, this, "setAlpha"));
            for (int index = 0; index < size; index++) {
                imageModifierUtil.setAlpha(this.imageArray[index], this.imageArray[index], this.alpha);
            }
            
        }        
    }
    
    public void nextRotation()
    {
        super.nextRotation();
        
        this.currentImage = this.imageArray[this.circularIndexUtil.getIndex()];
    }

    public void previousRotation()
    {
        super.previousRotation();
        
        this.currentImage = this.imageArray[this.circularIndexUtil.getIndex()];
    }

    public void setFrame(int index)
    {
        super.setFrame(index);
        
        this.currentImage = this.imageArray[this.circularIndexUtil.getIndex()];
    }
    
    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    protected void setImageArray(Image[] imageArray)
    {
        this.imageArray = imageArray;
        this.totalFrames = imageArray.length;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.totalFrames);
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.currentImage, x, y, anchor);
    }
}
