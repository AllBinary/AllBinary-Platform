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
package allbinary.animation.compound;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.CircularIndexUtil;

import allbinary.animation.IndexedAnimation;
import allbinary.animation.RotationAnimation;
import allbinary.direction.Direction;
import allbinary.logic.math.PrimitiveIntUtil;
import allbinary.math.Angle;
import allbinary.math.AngleInfo;

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

    public CompoundRotationAnimation(RotationAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.animationInterfaceArray.length);
    }
    
    public void setFrame(int index)
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
    throws Exception
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

    public void paint(Graphics graphics, int x, int y)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].paint(graphics, x, y);
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
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

    public void setAnimation(int index)
    {
        int frame = this.getFrame();
        this.circularIndexUtil.setIndex(index);
        this.setFrame(frame);
    }

    public void setFrame(Direction direction)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(direction);
    }

    public void setFrame(Angle angle)
    {
        this.animationInterfaceArray[this.circularIndexUtil.getIndex()].setFrame(angle);
    }

    public void adjustFrame(Angle newAngle)
    {
        this.adjustFrame(newAngle.getValue());
    }

    public void adjustFrame(short angle)
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
    public void setAnimationInterfaceArray(RotationAnimation[] animationInterfaceArray)
    {
        this.animationInterfaceArray = animationInterfaceArray;
    }
}
