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

import javax.microedition.lcdui.Graphics;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;

import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class SimultaneousCompoundIndexedAnimation 
    extends IndexedAnimation
    //implements IndexedAnimationInterface
{
    private IndexedAnimation[] animationInterfaceArray;

    public SimultaneousCompoundIndexedAnimation(final IndexedAnimation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    public void setFrame(final int frameIndex)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(frameIndex);
        }
    }

    public int getFrame()
    {
        return this.animationInterfaceArray[0].getFrame();
    }

    public int getSize()
    {
        return this.animationInterfaceArray[0].getSize();
    }

    public void previousFrame()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].previousFrame();
        }
    }

    public void setSequence(final int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void nextFrame() throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextFrame();
        }
    }

    public void paint(final Graphics graphics, final int x, final int y)
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].paint(graphics, x, y);
        }
    }

    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].paintThreed(graphics, x, y, z);
        }
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
}
