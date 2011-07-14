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

import org.allbinary.util.CircularIndexUtil;

import allbinary.direction.Direction;
import allbinary.direction.DirectionFactory;
import allbinary.direction.DirectionUtil;
import allbinary.math.Angle;
import allbinary.math.AngleFactory;
import allbinary.math.AngleInfo;

public class RotationAnimation 
    extends IndexedAnimation 
    implements RotationAnimationInterface
{
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    
    private final AngleInfo angleInfo = AngleInfo.getInstance((AngleFactory.getInstance().TOTAL_ANGLE >> 2));

    protected CircularIndexUtil circularIndexUtil;
    
    protected RotationAnimation()
    {
        this.circularIndexUtil = CircularIndexUtil.getInstance(4);
    }

    public void nextRotation()
    throws Exception
    {
        //super.nextFrame();
        
        this.circularIndexUtil.next();

        this.angleInfo.adjustAngle(this.circularIndexUtil.getIndex());
    }

    public void previousRotation() {
        //super.previousFrame();

        this.circularIndexUtil.previous();
        
        this.angleInfo.adjustAngle(this.circularIndexUtil.getIndex());
    }

    public void nextRotation(AngleInfo angleInfo)
    throws Exception
    {
        //this.circularIndexUtil.next();

        //this.getAngleInfo().adjustAngle(this.circularIndexUtil.getIndex());
    }

    public void previousRotation(AngleInfo angleInfo) 
    {
        //this.circularIndexUtil.previous();
        
        //this.getAngleInfo().adjustAngle(this.circularIndexUtil.getIndex());
    }
    
    public void setFrame(Direction direction)
    {
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
    }

    public void setFrame(Angle angle)
    {
    }

    public void adjustFrame(Angle newAngle)
    {
        this.angleInfo.setAngle(newAngle.getValue());
    }

    public void adjustFrame(short angle)
    {
        this.angleInfo.setAngle(angle);
    }

    public AngleInfo getAngleInfo()
    {
        return this.angleInfo;
    }
}
