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
package allbinary.animation.image.sprite;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Sprite;

import allbinary.animation.RotationAnimation;
import allbinary.logic.math.PrimitiveIntUtil;
import allbinary.math.AngleInfo;

public class HackRotationSpriteIndexedAnimation extends RotationAnimation
{
    protected Sprite sprite;

    public HackRotationSpriteIndexedAnimation(Sprite sprite, AngleInfo angleInfo)
    {
        super(angleInfo);
        
        this.setSprite(sprite);
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

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public void paint(Graphics g, int frame, int x, int y)
    {
        this.setFrame(frame);
        this.paint(g, x, y);
    }

    public void paint(Graphics g, int x, int y)
    {
        this.sprite.setPosition(x, y);
        this.paint(g);
    }

    protected void paint(Graphics g)
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

    public void setFrame(int frame)
    {
        this.sprite.setFrame(frame);
    }

    public int getFrame()
    {
        return this.sprite.getFrame();
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
}
