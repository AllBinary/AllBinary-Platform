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

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;

public class HackRotationSpriteIndexedAnimation extends RotationAnimation
{
    protected final Sprite sprite;
    private final Image image; 

    public HackRotationSpriteIndexedAnimation(final Sprite sprite, final Image image, final AngleInfo angleInfo, final AnimationBehavior animationBehavior)
    {
        super(angleInfo, animationBehavior);
        
        this.sprite = sprite;
        this.image = image;

    }

    /*
     * public SpriteIndexedAnimation(MESprite sprite, int dx, int dy) {
     * this.setSprite(sprite);
     * 
     * this.setDx(dx); this.setDy(dy); }
     */

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }

    public void paint(final Graphics g, final int frame, final int x, final int y)
    {
        this.setFrame(frame);
        this.paint(g, x, y);
    }

    public void paint(final Graphics g, final int x, final int y)
    {
        this.sprite.setPosition(x, y);
        this.paint(g);
    }

    protected void paint(final Graphics g)
    {
        this.sprite.paint(g);
    }

    public void nextRotation()
    {
        this.sprite.nextFrame();
    }

    public void previousRotation()
    {
        this.sprite.prevFrame();
    }

    public int getSize()
    {
        // .getFrameSequenceLength()
        return this.sprite.getRawFrameCount();
    }

    public void setFrame(final int frame)
    {
        this.sprite.setFrame(frame);
    }

    public int getFrame()
    {
        return this.sprite.getFrame();
    }

    public void setSequence(final int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
}
