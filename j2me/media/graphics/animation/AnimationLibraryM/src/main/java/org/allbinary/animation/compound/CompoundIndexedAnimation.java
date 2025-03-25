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

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.util.CircularIndexUtil;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class CompoundIndexedAnimation extends IndexedAnimation
    implements CompoundAnimationInterface
{
    private CircularIndexUtil circularIndexUtil;
    private IndexedAnimation[] animationInterfaceArray;

    public CompoundIndexedAnimation(final IndexedAnimation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);

        this.animationInterfaceArray = animationInterfaceArray;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.animationInterfaceArray.length);
    }
    
    public void setFrame(final int index)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(index);
    }

    public int getFrame()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getFrame();
    }

    public int getSize()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getSize();
    }

    public void previousFrame()
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].previousFrame();
    }

    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void nextFrame()
    throws Exception
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].nextFrame();
    }

    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].paint(graphics, x, y);
    }

    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].paintThreed(graphics, x, y, z);
    }
    
    public IndexedAnimation getCurrentAnimation()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
    }
    
    public void nextAnimation()
    {
        int frame = this.getFrame();
        
        this.circularIndexUtil.next();
        
        this.setFrame(frame);
    }

    public void previousAnimation()
    {
        int frame = this.getFrame();
        
        this.circularIndexUtil.previous();
        
        this.setFrame(frame);
    }

    public void setAnimation(final int index)
    {
        int frame = this.getFrame();
        this.circularIndexUtil.setIndex(index);
        this.setFrame(frame);
    }

    /**
     * @return the animationInterfaceArray
     */
    public IndexedAnimation[] getAnimationInterfaceArray()
    {
        return animationInterfaceArray;
    }

    /**
     * @param animationInterfaceArray the animationInterfaceArray to set
     */
    public void setAnimationInterfaceArray(final IndexedAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    public void set(final GL gl) throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].set(gl);
        }
    }    
}
