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

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.animation.RotationAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.image.ImageModifierUtil;

public class ImageArrayBaseRotationAnimation extends RotationAnimation //implements AutoCloseable
{
    private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();
    
    private final Image[] originalImageArray;
    private Image[] imageArray;

    private Image currentImage;
    
    private int totalFrames;

    public ImageArrayBaseRotationAnimation(final Image[] originalImageArray, final AngleInfo angleInfo, final AnimationBehavior animationBehavior) 
        throws Exception {
        
        super(angleInfo, animationBehavior);

        //LogUtil.put(LogFactory.getInstance("Constructing", this, "AllBinaryImageRotationAnimation"));

        this.originalImageArray = originalImageArray;
        this.setImageArray(imageModifierUtil.getImageArray(originalImageArray));
        //this.setImageArray(originalImageArray);
        
        this.currentImage = this.imageArray[this.circularIndexUtil.getIndex()]; 
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }

    public void setAlpha(final int alpha) {
        
        if(this.alpha != alpha) {

            super.setAlpha(alpha);
            imageModifierUtil.reset();
            final int index = this.circularIndexUtil.getIndex();
            imageModifierUtil.setAlpha(this.originalImageArray[index], this.imageArray[index], index, this.alpha);
        }
    }
    
    public void nextRotation()
    {
        super.nextRotation();
        
        final int index = this.circularIndexUtil.getIndex();
        imageModifierUtil.setAlpha(this.originalImageArray[index], this.imageArray[index], index, this.alpha);
        this.currentImage = this.imageArray[index];
    }

    public void previousRotation()
    {
        super.previousRotation();
        
        final int index = this.circularIndexUtil.getIndex();
        imageModifierUtil.setAlpha(this.originalImageArray[index], this.imageArray[index], index, this.alpha);
        this.currentImage = this.imageArray[index];
    }

    public void setFrame(final int index2)
    {
        super.setFrame(index2);
        
        final int index = this.circularIndexUtil.getIndex();
        imageModifierUtil.setAlpha(this.originalImageArray[index], this.imageArray[index], index, this.alpha);
        this.currentImage = this.imageArray[index];
    }
    
    public void setSequence(final int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    protected void setImageArray(final Image[] imageArray)
    {
        this.imageArray = imageArray;
        this.totalFrames = imageArray.length;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.totalFrames);
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        graphics.drawImage(this.currentImage, x, y, anchor);
    }
    
    public void close() throws Exception {
        
        final DisposalUtil disposalUtil = DisposalUtil.getInstance();
        
        final int size2 = this.imageArray.length;
        for(int index = 0; index < size2; index++) {
            disposalUtil.dispose(this.imageArray[index]);
        }

        final int size = this.originalImageArray.length;
        for(int index = 0; index < size; index++) {
            disposalUtil.dispose(this.originalImageArray[index]);
        }
        
        disposalUtil.dispose(this.currentImage);
    }
 
    protected void finalize() throws Throwable {
        
        final DisposalUtil disposalUtil = DisposalUtil.getInstance();
        
        final int size2 = this.imageArray.length;
        for(int index = 0; index < size2; index++) {
            disposalUtil.dispose(this.imageArray[index]);
        }

        final int size = this.originalImageArray.length;
        for(int index = 0; index < size; index++) {
            disposalUtil.dispose(this.originalImageArray[index]);
        }
        
        disposalUtil.dispose(this.currentImage);
    }
    
}
