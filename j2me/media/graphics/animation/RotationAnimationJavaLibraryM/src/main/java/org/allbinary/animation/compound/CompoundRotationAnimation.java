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
public class CompoundRotationAnimation extends RotationAnimation
    implements CompoundAnimationInterface
{
    private CircularIndexUtil circularIndexUtil;
    private RotationAnimation[] animationInterfaceArray;

    public CompoundRotationAnimation(final RotationAnimation[] animationInterfaceArray, final AnimationBehavior animationBehavior)
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

    public int getAnimationSize() throws Exception
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getAnimationSize();
    }

    public int getSize()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getSize();
    }

    public void previousFrame()
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].previousFrame();
    }

    public void nextFrame()
    throws Exception
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].nextFrame();
    }

    public void previousRotation()
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].previousRotation();
    }

    public void nextRotation()
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].nextRotation();
    }
    
    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
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

    public void setFrame(final Direction direction)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(direction);
    }

    public void setFrame(final Angle angle)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(angle);
    }

    public void adjustFrame(final Angle newAngle)
    {
        this.adjustFrame(newAngle.getValue());
    }

    public void adjustFrame(final short angle)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].adjustFrame(angle);
    }

    public AngleInfo getAngleInfo()
    {
        return this.animationInterfaceArray[this.circularIndexUtil.getIndex()].getAngleInfo();
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
    
    public void set(final GL gl) throws Exception
    {
        for(int index = this.animationInterfaceArray.length; --index >= 0;)
        {
            this.animationInterfaceArray[index].set(gl);
        }
    }    
}
