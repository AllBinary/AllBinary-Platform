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
package org.allbinary.animation.compound;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;
import org.allbinary.animation.Animation;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.util.CircularIndexUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class CompoundIndexedAnimation extends IndexedAnimation
    implements CompoundAnimationInterface
{
    private CircularIndexUtil circularIndexUtil;
    private Animation[] animationInterfaceArray;

    public CompoundIndexedAnimation(final Animation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);

        this.animationInterfaceArray = animationInterfaceArray;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.animationInterfaceArray.length);
    }
    
    @Override
    public void setFrame(final int index)
    {
        final IndexedAnimation indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
        indexedAnimation.setFrame(index);
    }

    @Override
    public int getFrame()
    {
        final IndexedAnimation indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
        return indexedAnimation.getFrame();
    }

    @Override
    public int getSize()
    {
        final IndexedAnimation indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
        return indexedAnimation.getSize();
    }

    @Override
    public void previousFrame()
    {
        final IndexedAnimation indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
        indexedAnimation.previousFrame();
    }

    @Override
    public void setSequence(int[] sequence)
    {
    }

    @Override
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    @Override
    public void nextFrame()
    throws Exception
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].nextFrame();
    }

    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].paint(graphics, x, y);
    }

    @Override
    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].paintThreed(graphics, x, y, z);
    }
    
    @Override
    public IndexedAnimation getCurrentAnimation()
    {
        return (IndexedAnimation) this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
    }
    
    @Override
    public void nextAnimation()
    {
        int frame = this.getFrame();
        
        this.circularIndexUtil.next();
        
        this.setFrame(frame);
    }

    @Override
    public void previousAnimation()
    {
        int frame = this.getFrame();
        
        this.circularIndexUtil.previous();
        
        this.setFrame(frame);
    }

    @Override
    public void setAnimation(final int index)
    {
        int frame = this.getFrame();
        this.circularIndexUtil.setIndex(index);
        this.setFrame(frame);
    }

    /**
     * @return the animationInterfaceArray
     */
    public Animation[] getAnimationInterfaceArray()
    {
        return animationInterfaceArray;
    }

    /**
     * @param animationInterfaceArray the animationInterfaceArray to set
     */
    public void setAnimationInterfaceArray(final Animation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    @Override
    public void set(final GL gl) throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].set(gl);
        }
    }    
}
