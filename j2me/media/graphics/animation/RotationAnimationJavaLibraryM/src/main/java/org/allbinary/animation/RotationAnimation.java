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

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;
import org.allbinary.util.CircularIndexUtil;

public class RotationAnimation 
    extends IndexedAnimation 
    implements RotationAnimationInterface
{

//    ImageArrayBaseRotationAnimation
//    public static RotationAnimation create360(final AngleInfo angleInfo, final AnimationBehavior animationBehavior)
//    {
//        return new RotationAnimation(angleInfo, CircularIndexUtil.getInstance(360 / angleInfo.getAngleIncrementInfo().getAngleIncrement()), animationBehavior);
//    }

//    ImageBaseRotationAnimation
//    public static RotationAnimation createTotalAngle(final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
//    {
//        return new RotationAnimation(angleInfo, CircularIndexUtil.getInstance(totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement()), animationBehavior);
//    }

//    LazyImageRotationAnimation
//    public static RotationAnimation createQuarter(final AnimationBehavior animationBehavior)
//    {
//        //AngleFactory.getInstance().TOTAL_ANGLE / angleInfo.getAngleIncrementInfo().getAngleIncrement() == 4
//        return new RotationAnimation(AngleInfo.getInstance(AngleFactory.getInstance().QUARTER_TOTAL_ANGLE), CircularIndexUtil.getInstance(4), animationBehavior);
//    }

    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final FrameUtil frameUtil = FrameUtil.getInstance();
    
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    protected final AngleInfo angleInfo;
    
    protected CircularIndexUtil circularIndexUtil;

    protected RotationAnimation(final AngleInfo angleInfo, final CircularIndexUtil circularIndexUtil, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);

        this.angleInfo = angleInfo;

        this.circularIndexUtil = circularIndexUtil;
    }

    public void nextRotationX()
    {
    }

    public void previousRotationX()
    {
    }
    
    @Override
    public void nextRotation()
    {
        //super.nextFrame();
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());
    }

    @Override
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

    @Override    
    public void setFrame(final int index)
    {
        //int currentFrame = this.circularIndexUtil.getIndex();
        this.circularIndexUtil.setIndex(index);

        final int newFrame = this.circularIndexUtil.getIndex();
        //this.logUtil.putF("newFrame: " + newFrame, this, "setRotation");
        
        this.angleInfo.adjustAngle(newFrame);
    }

    @Override
    public void setFrameByDirection(final Direction direction)
    {
        //this.logUtil.putF(this.commonStrings.START, this, "setFrame");

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

        final Angle angle = this.directionUtil.getFrameAngle(direction);
        this.adjustFrameToAngle(angle);
    }

    @Override
    public void setFrameToAngle(final Angle angle)
    {
        this.adjustFrameToAngle(angle);
    }

    @Override
    public void adjustFrameToAngle(final Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    @Override
    public void adjustFrame(final short angle)
    {
        this.setFrame(this.frameUtil.getFrameForAngle(
                angle, (int) this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
    }

    @Override
    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }
    
    @Override
    public int getSize()
    {
        return this.circularIndexUtil.getSize();
    }
    
    @Override
    public AngleInfo getAngleInfoP()
    {
        return this.angleInfo;
    }
    
    public String toString() {
        return new StringMaker().append(super.toString()).append("circularIndexUtil: ").append(this.circularIndexUtil.toString()).append("angleInfo: ").append(this.angleInfo.toString()).toString();
    }
}
