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

import org.allbinary.animation.IndexedAnimation;
import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class SimultaneousCompoundIndexAnimation 
extends IndexedAnimation
{
    private IndexedAnimation[] animationInterfaceArray;

    public SimultaneousCompoundIndexAnimation(IndexedAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    public void setFrame(int frameIndex)
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

    public int getAnimationSize() throws Exception
    {
        return this.animationInterfaceArray[0].getAnimationSize();
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

    public void nextFrame()
    throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextFrame();
        }
    }

    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void paint(Graphics graphics, int x, int y)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {        
            this.animationInterfaceArray[index].paint(graphics, x, y);
        }
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].paintThreed(graphics, x, y, z);
        }
    }

    public void set(GL gl) throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].set(gl);
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
