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
import org.allbinary.animation.Animation;

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
    private Animation[] animationInterfaceArray;

    public SimultaneousCompoundIndexedAnimation(final Animation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.animationInterfaceArray = animationInterfaceArray;
    }
  
    @Override
    public void setFrame(final int frameIndex)
    {
        IndexedAnimation indexedAnimation;
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[index];
            indexedAnimation.setFrame(frameIndex);
        }
    }

    @Override
    public int getFrame()
    {
        final IndexedAnimation indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[0];
        return indexedAnimation.getFrame();
    }

    @Override
    public int getSize()
    {
        final IndexedAnimation indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[0];
        return indexedAnimation.getSize();
    }

    @Override
    public void previousFrame()
    {
        IndexedAnimation indexedAnimation;
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            indexedAnimation = (IndexedAnimation) this.animationInterfaceArray[index];
            indexedAnimation.previousFrame();
        }
    }

    @Override
    public void setSequence(final int[] sequence)
    {
    }

    @Override
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    @Override
    public void nextFrame() throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextFrame();
        }
    }

    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].paint(graphics, x, y);
        }
    }

    @Override
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
}
