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
import org.allbinary.animation.AnimationBehavior;

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class ImageArrayAnimation extends IndexedAnimation
{
    private Image[] imageArray;

    // private int totalAngle;
    private int totalFrames;

    protected CircularIndexUtil circularIndexUtil;
    
    public ImageArrayAnimation(final Image[] imageArray, final AnimationBehavior animationBehavior) throws Exception
    {
        super(animationBehavior);

        // LogUtil.put(LogFactory.getInstance(, this, ));

        this.setImageArray(imageArray);
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    public int getSize()
    {
        return this.totalFrames;
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public Image getImage(int index)
    {
        return imageArray[index];
    }

    public Image[] getImageArray()
    {
        return imageArray;
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
        graphics.drawImage(this.imageArray[this.circularIndexUtil.getIndex()], x, y, anchor);

        /*
         * for(int index = 0; index < NUMBER_OF_FRAMES; index++) {
         * graphics.drawImage(this.getImage(index), 0, index
         * this.getImage(index).getHeight(), Anchor.TOP_LEFT); }
         */
    }

}
