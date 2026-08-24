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
package org.allbinary.animation.image.sprite;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class HackRotationSpriteIndexedAnimation extends RotationAnimation
{
    @JsProperty
    protected final Sprite sprite;
    private final Image image; 

    @JsConstructor
    public HackRotationSpriteIndexedAnimation(final Sprite sprite, final Image image, final AngleInfo angleInfo, final AnimationBehavior animationBehavior)
    {
        super(angleInfo, CircularIndexUtil.createInstance(360 / angleInfo.getAngleIncrementInfo().getAngleIncrement()), animationBehavior);
        
        this.sprite = sprite;
        this.image = image;

    }

    /*
     * public SpriteIndexedAnimation(MESprite sprite, int dx, int dy) {
     * this.setSprite(sprite);
     * 
     * this.setDx(dx); this.setDy(dy); }
     */

    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }

    @JsMethod
    public void paintFrame(final Graphics g, final int frame, final int x, final int y)
    {
        this.setFrame(frame);
        this.paintXY(g, x, y);
    }

    @Override
    @JsMethod
    public void paintXY(final Graphics g, final int x, final int y)
    {
        this.sprite.setPosition(x, y);
        this.paint(g);
    }

    @JsMethod
    protected void paint(final Graphics g)
    {
        this.sprite.paint(g);
    }

    @Override
    @JsMethod
    public void nextRotation()
    {
        this.sprite.nextFrame();
    }

    @Override
    @JsMethod
    public void previousRotation()
    {
        this.sprite.prevFrame();
    }

    @Override
    @JsMethod
    public int getSize()
    {
        // .getFrameSequenceLength()
        return this.sprite.getRawFrameCount();
    }

    @Override
    @JsMethod
    public void setFrame(final int frame)
    {
        this.sprite.setFrame(frame);
    }

    @Override
    @JsMethod
    public int getFrame()
    {
        return this.sprite.getFrame();
    }

    @Override
    @JsMethod
    public void setSequence(final int[] sequence)
    {

    }

    @Override
    @JsMethod
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
}
