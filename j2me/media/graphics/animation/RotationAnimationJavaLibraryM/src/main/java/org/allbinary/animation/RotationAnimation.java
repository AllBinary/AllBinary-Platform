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

import jsinterop.annotations.JsType;

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
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

    @JsProperty
    protected final FrameUtil frameUtil = FrameUtil.getInstance();
    
    @JsProperty
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    @JsProperty
    protected final AngleInfo angleInfo;
    
    @JsProperty
    protected CircularIndexUtil circularIndexUtil;

    @JsConstructor
    protected RotationAnimation(final AngleInfo angleInfo, final CircularIndexUtil circularIndexUtil, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);

        this.angleInfo = angleInfo;

        this.circularIndexUtil = circularIndexUtil;
    }

    @JsMethod
    public void nextRotationX()
    {
    }

    @JsMethod
    public void previousRotationX()
    {
    }
    
    @Override
    @JsMethod
    public void nextRotation()
    {
        //super.nextFrame();
        this.angleInfo.adjustAngle(this.circularIndexUtil.next());
    }

    @Override
    @JsMethod
    public void previousRotation() 
    {
        //super.previousFrame();
        this.angleInfo.adjustAngle(this.circularIndexUtil.previous());
    }

    @JsMethod
    public void nextRotationZ()
    {
    }

    @JsMethod
    public void previousRotationZ()
    {
    }

    @Override    
    @JsMethod
    public void setFrame(final int index)
    {
        //int currentFrame = this.circularIndexUtil.getIndex();
        this.circularIndexUtil.setIndex(index);

        final int newFrame = this.circularIndexUtil.getIndex();
        //this.logUtil.putF("newFrame: " + newFrame, this, "setRotation");
        
        this.angleInfo.adjustAngle(newFrame);
    }

    @Override
    @JsMethod
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
    @JsMethod
    public void setFrameToAngle(final Angle angle)
    {
        this.adjustFrameToAngle(angle);
    }

    @Override
    @JsMethod
    public void adjustFrameToAngle(final Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    @Override
    @JsMethod
    public void adjustFrame(final short angle)
    {
        this.setFrame(this.frameUtil.getFrameForAngle(
                angle, (int) this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
    }

    @Override
    @JsMethod
    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }
    
    @Override
    @JsMethod
    public int getSize()
    {
        return this.circularIndexUtil.getSize();
    }
    
    @Override
    @JsMethod
    public AngleInfo getAngleInfoP()
    {
        return this.angleInfo;
    }
    
    @JsMethod
    public String toString() {
        return new StringMaker().append(super.toString()).append("circularIndexUtil: ").append(this.circularIndexUtil.toString()).append("angleInfo: ").append(this.angleInfo.toString()).toString();
    }
}
