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

import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 *
 * @author user
 */
public class ImageSegmentAnimation 
    extends ImageAnimation
{
    private int startX;
    private int startY;
    private int drawWidth;
    private int drawHeight;

    private CircularIndexUtil circularIndexUtil;

    //Will need to do this for partial row display
    //private int min;
    //private int max;
    //private int diff;

    private int[] sequenceArray;
    
    protected ImageSegmentAnimation(final Image image, final AnimationBehavior animationBehavior)
        throws Exception
    {
        this(image, PrimitiveIntUtil.getArrayInstance(), animationBehavior);
    }
    
    protected ImageSegmentAnimation(final Image image, final int[] sequenceArray, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(image, animationBehavior);

        this.startX = 0;
        this.startY = 0;
        this.drawWidth = this.getImage().getWidth();
        this.drawHeight = this.getImage().getHeight();

        //this.min = 0;
        //this.max = this.getImage().getHeight();
        //this.diff = this.max - this.min;
        //this.circularIndexUtil = CircularIndexUtil.getInstance(this.diff);
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.drawHeight);

        this.sequenceArray = sequenceArray;
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
        return this.circularIndexUtil.getSize();
    }

    public void setSequence(int[] sequenceArray)
    {
        this.sequenceArray = sequenceArray;
    }

    public int[] getSequence()
    {
        return this.sequenceArray;
    }

    public void paint(Graphics graphics, int x, int y)
    {
    }

    /**
     * @return the startX
     */
    public int getStartX()
    {
        return startX;
    }

    /**
     * @param startX the startX to set
     */
    public void setStartX(int startX)
    {
        this.startX = startX;
    }

    /**
     * @return the startY
     */
    public int getStartY()
    {
        return startY;
    }

    /**
     * @param startY the startY to set
     */
    public void setStartY(int startY)
    {
        this.startY = startY;
    }

    /**
     * @return the drawWidth
     */
    public int getDrawWidth()
    {
        return drawWidth;
    }

    /**
     * @param drawWidth the drawWidth to set
     */
    public void setDrawWidth(int drawWidth)
    {
        this.drawWidth = drawWidth;
    }

    /**
     * @return the drawHeight
     */
    public int getDrawHeight()
    {
        return drawHeight;
    }

    /**
     * @param drawHeight the drawHeight to set
     */
    public void setDrawHeight(int drawHeight)
    {
        this.drawHeight = drawHeight;
    }
}