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
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class SpriteIndexedAnimation extends IndexedAnimation
    implements ColorCompositeInterface//, AutoCloseable
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final Sprite sprite;
    @JsProperty
    protected final Image image;
    
    private final BasicColor[] basicColorArray;

    @JsConstructor
    public SpriteIndexedAnimation(final Sprite sprite, final Image image, final BasicColor[] basicColorArray, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(animationBehavior);
        
        this.sprite = sprite;
        this.image = image;
        
        this.basicColorArray = basicColorArray;

        if(this.basicColorArray.length != 0 && this.getSize() != this.basicColorArray.length)
        {
            throw new Exception(new StringMaker().append(CommonLabels.getInstance().TOTAL_LABEL).appendint(this.getSize()).append("!=").appendint(this.basicColorArray.length).toString());
        }
    }

    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    @Override
    @JsMethod
    public void setBasicColorP(final BasicColor basicColor)
        //throws Exception
    {
        for(int index = 0; index < this.basicColorArray.length; index++)
        {
            if(basicColor == this.basicColorArray[index])
            {
                //this.logUtil.putF(
                  //  "BasicColor: " + basicColor.getName() + "==" + this.basicColorArray[index].getName() + commonLabels.INDEX_LABEL + index, this, "setBasicColor");
                this.setFrame(index);
                break;
            }
        }
    }

    @Override
    @JsMethod
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

    @JsMethod
    public void paintFrame(final Graphics graphics, final int frame, final int x, final int y)
    {
        this.setFrame(frame);
        this.sprite.setPosition(x, y);
        this.sprite.paint(graphics);
    }

    @Override
    @JsMethod
    public void paintXY(final Graphics graphics, final int x, final int y)
    {
        this.sprite.setPosition(x, y);
        this.sprite.paint(graphics);
    }

    @JsMethod
    protected void paint(final Graphics graphics)
    {
        this.sprite.paint(graphics);
    }

    @Override
    @JsMethod
    public void nextFrame()
    {
        this.sprite.nextFrame();
    }

    @Override
    @JsMethod
    public void previousFrame()
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

    @JsMethod
    public int getLastFrame()
    {
        //use Seguence at some point
        return this.getSize();
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
    
    @JsMethod
    public void close() throws Exception {
    }
 
    @Override
    @JsMethod
    protected void finalize() throws Throwable {
    }
     
}
