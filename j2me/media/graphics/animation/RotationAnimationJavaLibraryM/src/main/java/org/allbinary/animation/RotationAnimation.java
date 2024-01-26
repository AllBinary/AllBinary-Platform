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
package org.allbinary.animation;

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;

public class RotationAnimation 
    extends IndexedAnimation 
    implements RotationAnimationInterface
{
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    protected final AngleInfo angleInfo;
    protected CircularIndexUtil circularIndexUtil;

    protected RotationAnimation(final AngleInfo angleInfo, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.angleInfo = angleInfo;
        
        //Is 360 okay?
        this.circularIndexUtil = CircularIndexUtil.getInstance(
                360 / angleInfo.getAngleIncrementInfo().getAngleIncrement());
    }
    
    protected RotationAnimation(final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.angleInfo = angleInfo;
        this.circularIndexUtil = CircularIndexUtil.getInstance(
                totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement());
    }

    protected RotationAnimation(final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.angleInfo = AngleInfo.getInstance((short) (AngleFactory.getInstance().TOTAL_ANGLE >> 2)); //
        //AngleFactory.getInstance().TOTAL_ANGLE / angleInfo.getAngleIncrementInfo().getAngleIncrement() == 4
        this.circularIndexUtil = CircularIndexUtil.getInstance(4);
    }

    public void nextRotation()
    {
        //super.nextFrame();
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());
    }

    public void previousRotation() 
    {
        //super.previousFrame();
        this.angleInfo.adjustAngle(this.circularIndexUtil.previous());
    }

    public void nextRotationZ()
    {
    }

    public void previousRotationZ()
    {
    }
    
    public void setFrame(int index)
    {
        //int currentFrame = this.circularIndexUtil.getIndex();
        this.circularIndexUtil.setIndex(index);

        final int newFrame = this.circularIndexUtil.getIndex();
        //LogUtil.put(LogFactory.getInstance("newFrame: " + newFrame, this, "setRotation"));
        
        this.angleInfo.adjustAngle(newFrame);
    }

    public void setFrame(Direction direction)
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "setFrame"));

        /*
        DirectionFactory directionFactory = DirectionFactory.getInstance();
        
        if(directionFactory.UP == direction)
        {
            this.circularIndexUtil.setIndex(0);
        }
        else
            if(directionFactory.DOWN == direction)
            {
                this.circularIndexUtil.setIndex(2);
            }
            else
                if(directionFactory.LEFT == direction)
                {
                    this.circularIndexUtil.setIndex(3);
                }
                else
                    if(directionFactory.RIGHT == direction)
                    {
                        this.circularIndexUtil.setIndex(1);
                    }

        this.angleInfo.adjustAngle(this.circularIndexUtil.getIndex());
        */

        Angle angle = directionUtil.getFrameAngle(direction);
        this.adjustFrame(angle);
    }
        
    public void setFrame(Angle angle)
    {
        this.adjustFrame(angle);
    }

    public void adjustFrame(Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    private final FrameUtil frameUtil = FrameUtil.getInstance();

    public void adjustFrame(short angle)
    {
        this.setFrame(this.frameUtil.getFrameForAngle(
                angle, this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }
    
    public int getSize()
    {
        return this.circularIndexUtil.getSize();
    }
    
    public AngleInfo getAngleInfo()
    {
        return this.angleInfo;
    }
    
    public String toString() {
        return new StringMaker().append("circularIndexUtil: ").append(this.circularIndexUtil.toString()).append("angleInfo: ").append(this.angleInfo.toString()).toString();
    }
}
