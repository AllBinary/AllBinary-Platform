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

import abcs.logic.basic.string.CommonStrings;
import allbinary.animation.IndexedAnimation;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.BasicColorUtil;
import allbinary.graphics.color.ColorCompositeInterface;
import allbinary.logic.math.PrimitiveIntUtil;

public class SpriteIndexedAnimation extends IndexedAnimation
    implements ColorCompositeInterface
{
    private Sprite sprite;
    private final BasicColor[] basicColorArray;

    public SpriteIndexedAnimation(Sprite sprite)
    {
        this.setSprite(sprite);
        basicColorArray = BasicColorUtil.getInstance().ZERO_ARRAY;
    }

    public SpriteIndexedAnimation(Sprite sprite, BasicColor[] basicColorArray)
        throws Exception
    {
        this.setSprite(sprite);
        this.basicColorArray = basicColorArray;

        if(this.getSize() != this.basicColorArray.length)
        {
            throw new Exception(CommonStrings.getInstance().TOTAL_LABEL + this.getSize() + "!=" + this.basicColorArray.length);
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
