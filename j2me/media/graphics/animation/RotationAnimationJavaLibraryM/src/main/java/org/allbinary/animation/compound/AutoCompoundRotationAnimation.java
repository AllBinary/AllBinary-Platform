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
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.direction.Direction;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleInfo;
import org.allbinary.util.CircularIndexUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class AutoCompoundRotationAnimation extends RotationAnimation
    implements CompoundAnimationInterface
{
    private RotationAnimation[] animationInterfaceArray;

    public AutoCompoundRotationAnimation(final RotationAnimation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.animationInterfaceArray = animationInterfaceArray;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.animationInterfaceArray.length);
    }

    @Override    
    public void setFrame(final int index)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(index);
    }

    @Override
    public int getFrame()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getFrame();
    }

    @Override
    public int getAnimationSize() throws Exception
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getAnimationSize();
    }
    
    @Override
    public int getSize()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getSize();
    }

    @Override
    public void previousFrame()
    {
        this.circularIndexUtil.previous();
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].previousFrame();
    }

    @Override
    public void nextFrame()
    throws Exception
    {
        this.circularIndexUtil.next();
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].nextFrame();
    }

    @Override
    public void previousRotation()
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].previousRotation();
    }

    @Override
    public void nextRotation()
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].nextRotation();
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
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()];
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
        final int frame = this.getFrame();
        this.circularIndexUtil.setIndex(index);
        this.setFrame(frame);
    }

    @Override
    public void setFrame(final Direction direction)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(direction);
    }

    @Override
    public void setFrame(final Angle angle)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(angle);
    }

    @Override
    public void adjustFrame(final Angle newAngle)
    {
        this.adjustFrame(newAngle.getValue());
    }

    @Override
    public void adjustFrame(final short angle)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].adjustFrame(angle);
    }

    @Override
    public AngleInfo getAngleInfoP()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getAngleInfoP();
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
