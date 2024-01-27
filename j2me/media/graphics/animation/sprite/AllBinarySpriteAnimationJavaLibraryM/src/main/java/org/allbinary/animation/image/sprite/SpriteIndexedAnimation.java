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
import javax.microedition.lcdui.game.Sprite;
import org.allbinary.animation.AnimationBehavior;

import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorUtil;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class SpriteIndexedAnimation extends IndexedAnimation
    implements ColorCompositeInterface
{
    private Sprite sprite;
    private final BasicColor[] basicColorArray;
    
    public SpriteIndexedAnimation(final Sprite sprite, final AnimationBehavior animationBehavior)
        throws Exception
    {
        this(sprite, BasicColorUtil.getInstance().ZERO_ARRAY, animationBehavior);
    }
    
    public SpriteIndexedAnimation(final Sprite sprite, final BasicColor[] basicColorArray, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(animationBehavior);
        
        this.setSprite(sprite);
        this.basicColorArray = basicColorArray;

        if(this.getSize() != this.basicColorArray.length)
        {
            throw new Exception(new StringMaker().append(CommonLabels.getInstance().TOTAL_LABEL).append(this.getSize()).append("!=").append(this.basicColorArray.length).toString());
        }
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void setBasicColor(BasicColor basicColor)
        //throws Exception
    {
        for(int index = 0; index < this.basicColorArray.length; index++)
        {
            if(basicColor == this.basicColorArray[index])
            {
                //LogUtil.put(LogFactory.getInstance(
                  //  "BasicColor: " + basicColor.getName() + "==" + this.basicColorArray[index].getName() + CommonStrings.getInstance().INDEX_LABEL + index, this, "setBasicColor"));
                this.setFrame(index);
                break;
            }
        }
    }

    public BasicColor getBasicColor()
    {
        if(this.basicColorArray.length < this.getFrame())
        {
            return this.basicColorArray[this.getFrame()];
        }
        else
        {
            return BasicColorFactory.getInstance().WHITE;
        }
    }

    /*
     * public SpriteIndexedAnimation(MESprite sprite, int dx, int dy) {
     * this.setSprite(sprite);
     * 
     * this.setDx(dx); this.setDy(dy); }
     */

    public Sprite getSprite()
    {
        return sprite;
    }

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public void paint(Graphics graphics, int frame, int x, int y)
    {
        this.setFrame(frame);
        this.paint(graphics, x, y);
    }

    public void paint(Graphics graphics, int x, int y)
    {
        this.sprite.setPosition(x, y);
        this.paint(graphics);
    }

    protected void paint(Graphics graphics)
    {
        sprite.paint(graphics);
    }

    public void nextFrame()
    {
        this.sprite.nextFrame();
    }

    public void previousFrame()
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

    public boolean isLastFrame()
    {
        if(this.sprite.getFrame() == this.getSize() - 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getLastFrame()
    {
        //use Seguence at some point
        return this.getSize();
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
}
