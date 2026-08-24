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
import javax.microedition.lcdui.NullImage;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ImageArrayAnimation extends IndexedAnimation
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private Image[] imageArray = NullImage.NULL_IMAGE_ARRAY;

    // private int totalAngle;
    private int totalFrames;

    @JsProperty
    protected CircularIndexUtil circularIndexUtil = CircularIndexUtil.NULL_CIRCULAR_INDEX_UTIL;
    
    @JsConstructor
    public ImageArrayAnimation(final Image[] imageArray, final AnimationBehavior animationBehavior) throws Exception
    {
        super(animationBehavior);

        // this.logUtil.putF(, this, );

        this.setImageArray(imageArray);
    }

    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
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
        return this.totalFrames;
    }

    @Override
    @JsMethod
    public void setSequence(int[] sequence)
    {

    }

    @Override
    @JsMethod
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    @JsMethod
    public Image getImage(int index)
    {
        return this.imageArray[index];
    }

    @JsMethod
    public Image[] getImageArray()
    {
        return this.imageArray;
    }

    @JsMethod
    protected void setImageArray(Image[] imageArray)
    {
        this.imageArray = imageArray;
        this.totalFrames = imageArray.length;
        this.circularIndexUtil = CircularIndexUtil.createInstance(this.totalFrames);
    }

    private int anchor = Anchor.TOP_LEFT;
    
    @Override
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.imageArray[this.circularIndexUtil.getIndex()], x, y, this.anchor);

        /*
         * for(int index = 0; index < NUMBER_OF_FRAMES; index++) {
         * graphics.drawImage(this.getImage(index), 0, index
         * this.getImage(index).getHeight(), Anchor.TOP_LEFT); }
         */
    }

}
