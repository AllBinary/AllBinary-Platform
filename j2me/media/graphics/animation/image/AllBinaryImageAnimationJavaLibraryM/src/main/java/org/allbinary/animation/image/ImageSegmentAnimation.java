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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

/**
 *
 * @author user
 */

@JsType
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
    
    @JsConstructor
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
        this.circularIndexUtil = CircularIndexUtil.createInstance(this.drawHeight);

        this.sequenceArray = sequenceArray;
    }

    @Override
    @JsMethod
    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    @Override
    @JsMethod
    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    @Override
    @JsMethod
    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    @Override
    @JsMethod
    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    @Override
    @JsMethod
    public int getSize()
    {
        return this.circularIndexUtil.getSize();
    }

    @Override
    @JsMethod
    public void setSequence(int[] sequenceArray)
    {
        this.sequenceArray = sequenceArray;
    }

    @Override
    @JsMethod
    public int[] getSequence()
    {
        return this.sequenceArray;
    }

    @Override
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
    }

    /**
     * @return the startX
     */
    @JsMethod
    public int getStartX()
    {
        return this.startX;
    }

    /**
     * @param startX the startX to set
     */
    @JsMethod
    public void setStartX(int startX)
    {
        this.startX = startX;
    }

    /**
     * @return the startY
     */
    @JsMethod
    public int getStartY()
    {
        return this.startY;
    }

    /**
     * @param startY the startY to set
     */
    @JsMethod
    public void setStartY(int startY)
    {
        this.startY = startY;
    }

    /**
     * @return the drawWidth
     */
    @JsMethod
    public int getDrawWidth()
    {
        return this.drawWidth;
    }

    /**
     * @param drawWidth the drawWidth to set
     */
    @JsMethod
    public void setDrawWidth(int drawWidth)
    {
        this.drawWidth = drawWidth;
    }

    /**
     * @return the drawHeight
     */
    @JsMethod
    public int getDrawHeight()
    {
        return this.drawHeight;
    }

    /**
     * @param drawHeight the drawHeight to set
     */
    @JsMethod
    public void setDrawHeight(int drawHeight)
    {
        this.drawHeight = drawHeight;
    }
}