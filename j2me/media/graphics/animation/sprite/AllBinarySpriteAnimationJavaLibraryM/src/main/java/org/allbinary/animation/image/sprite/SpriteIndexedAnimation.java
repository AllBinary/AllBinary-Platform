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
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorUtil;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;

public class SpriteIndexedAnimation extends IndexedAnimation
    implements ColorCompositeInterface//, AutoCloseable
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final Sprite sprite;
    protected final Image image;
    
    private final BasicColor[] basicColorArray;
    
    public SpriteIndexedAnimation(final Sprite sprite, final Image image, final AnimationBehavior animationBehavior)
        throws Exception
    {
        this(sprite, image, BasicColorUtil.getInstance().ZERO_ARRAY, animationBehavior);
    }
    
    public SpriteIndexedAnimation(final Sprite sprite, final Image image, final BasicColor[] basicColorArray, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(animationBehavior);
        
        this.sprite = sprite;
        this.image = image;
        
        this.basicColorArray = basicColorArray;

        if(this.basicColorArray.length != 0 && this.getSize() != this.basicColorArray.length)
        {
            throw new Exception(new StringMaker().append(CommonLabels.getInstance().TOTAL_LABEL).append(this.getSize()).append("!=").append(this.basicColorArray.length).toString());
        }
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void setBasicColorP(final BasicColor basicColor)
        //throws Exception
    {
        for(int index = 0; index < this.basicColorArray.length; index++)
        {
            if(basicColor == this.basicColorArray[index])
            {
                //logUtil.put(
                  //  "BasicColor: " + basicColor.getName() + "==" + this.basicColorArray[index].getName() + commonLabels.INDEX_LABEL + index, this, "setBasicColor");
                this.setFrame(index);
                break;
            }
        }
    }

    public BasicColor getBasicColorP()
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

    public void paint(final Graphics graphics, final int frame, final int x, final int y)
    {
        this.setFrame(frame);
        this.sprite.setPosition(x, y);
        sprite.paint(graphics);
    }

    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.sprite.setPosition(x, y);
        sprite.paint(graphics);
    }

    protected void paint(final Graphics graphics)
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

    public void setFrame(final int frame)
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

    public void setSequence(final int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
     
    public void close() throws Exception {
    }
 
    protected void finalize() throws Throwable {
    }
     
}
