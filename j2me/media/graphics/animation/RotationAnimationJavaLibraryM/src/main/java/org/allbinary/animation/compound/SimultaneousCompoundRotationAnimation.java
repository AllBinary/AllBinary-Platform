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
import org.allbinary.animation.RotationAnimation;
import org.allbinary.direction.Direction;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleInfo;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class SimultaneousCompoundRotationAnimation 
extends RotationAnimation
{
    private RotationAnimation[] animationInterfaceArray;

    public SimultaneousCompoundRotationAnimation(final RotationAnimation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.animationInterfaceArray = animationInterfaceArray;
    }
    
    @Override
    public void setFrame(final int frameIndex)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(frameIndex);
        }
    }

    @Override
    public int getFrame()
    {
        return this.animationInterfaceArray[0].getFrame();
    }

    @Override
    public int getAnimationSize() throws Exception
    {
        return this.animationInterfaceArray[0].getAnimationSize();
    }
    
    @Override
    public int getSize()
    {
        return this.animationInterfaceArray[0].getSize();
    }

    @Override
    public void previousFrame()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].previousFrame();
        }
    }

    @Override
    public void nextFrame()
    throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextFrame();
        }
    }

    @Override
    public void previousRotation()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].previousRotation();
        }
    }

    @Override
    public void nextRotation()
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].nextRotation();
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
    public void paint(final Graphics graphics, final int x, final int y)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {        
            this.animationInterfaceArray[index].paint(graphics, x, y);
        }
    }

    @Override
    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].paintThreed(graphics, x, y, z);
        }
    }

    @Override
    public void setFrame(final Direction direction)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(direction);
        }
    }

    @Override
    public void setFrame(final Angle angle)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].setFrame(angle);
        }
    }

    @Override
    public void adjustFrame(final Angle newAngle)
    {
        this.adjustFrame(newAngle.getValue());
    }

    @Override
    public void adjustFrame(final short angle)
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].adjustFrame(angle);
        }
    }

    @Override
    public AngleInfo getAngleInfoP()
    {
        return this.animationInterfaceArray[0].getAngleInfoP();
    }
    
    /**
     * @return the animationInterfaceArray
     */
    public RotationAnimation[] getAnimationInterfaceArray()
    {
        return animationInterfaceArray;
    }

    /**
     * @param animationInterfaceArray the animationInterfaceArray to set
     */
    public void setAnimationInterfaceArray(final RotationAnimation[] animationInterfaceArray)
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
