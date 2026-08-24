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

import org.allbinary.DisposalUtil;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ImageBaseRotationAnimation extends RotationAnimation //implements AutoCloseable
{
    private final Image image;

    //private static final int currentFrame = 0;
    //private static final int totalFrames = 1;
    @JsConstructor
    public ImageBaseRotationAnimation(final Image image, final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(angleInfo, CircularIndexUtil.createInstance(totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement()), animationBehavior);
        
        this.image = image;
    }

    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
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

    @JsProperty
    protected int anchor = Anchor.TOP_LEFT;
    
    @Override
    @JsMethod
    public void paintXY(final Graphics graphics, final int x, final int y)
    {
        graphics.drawImage(this.image, x, y, this.anchor);
    }

    /**
     * @return the image
     */
    @JsMethod
    protected Image getImage()
    {
        return this.image;
    }

    @Override
    @JsMethod
    public int getWidth() {
        return this.image.getWidth();
    }
    
    @Override
    @JsMethod
    public int getHeight() {
        return this.image.getHeight();
    }

    /*
    public void paint(Graphics graphics) {
    graphics.drawImage(this.image, 0, 0, Anchor.TOP_LEFT);
    }
     */
    
    @JsMethod
    public void close() throws Exception {
        DisposalUtil.getInstance().disposeImage(this.image);
    }
    
    @Override
    @JsMethod
    protected void finalize() throws Throwable {
        DisposalUtil.getInstance().disposeImage(this.image);
    }
    
}
