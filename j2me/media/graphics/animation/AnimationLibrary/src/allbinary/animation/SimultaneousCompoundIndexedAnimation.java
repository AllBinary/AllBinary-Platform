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
package allbinary.animation;

import javax.microedition.lcdui.Graphics;

import allbinary.logic.math.PrimitiveIntUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class SimultaneousCompoundIndexedAnimation extends IndexedAnimation
    //implements IndexedAnimationInterface
{
    private IndexedAnimation[] animationInterfaceArray;

    public SimultaneousCompoundIndexedAnimation(IndexedAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    public void setFrame(int frameIndex)
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].setFrame(frameIndex);
        }
    }

    public int getFrame()
    {
        return this.getAnimationInterfaceArray()[0].getFrame();
    }

    public int getSize()
    {
        return this.getAnimationInterfaceArray()[0].getSize();
    }

    public void previousFrame()
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].previousFrame();
        }
    }

    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void nextFrame() throws Exception
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].nextFrame();
        }
    }

    public void paint(Graphics graphics, int x, int y)
    {
        int size = this.animationInterfaceArray.length;
        for(int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].paint(graphics, x, y);
        }
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
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
    public void setAnimationInterfaceArray(IndexedAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
}
