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
package allbinary.game.physics.velocity;

import abcs.logic.basic.string.CommonSeps;
import allbinary.direction.Direction;
import allbinary.direction.DirectionUtil;
import allbinary.logic.math.BasicDecimal;
import allbinary.logic.math.vector.AxisMathVectorUtil;
import allbinary.math.Angle;
import allbinary.math.PositionStrings;

public class BasicVelocityProperties implements BasicVelocityInterface
{
    protected final BasicDecimal velocityXBasicDecimal;
    protected final BasicDecimal velocityYBasicDecimal;
    protected final BasicDecimal velocityZBasicDecimal;

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    public BasicVelocityProperties()
    {
        this.velocityXBasicDecimal = new BasicDecimal();
        this.velocityYBasicDecimal = new BasicDecimal();
        this.velocityZBasicDecimal = new BasicDecimal();
    }

    public void zero()
    {
        this.velocityXBasicDecimal.set(0);
        this.velocityYBasicDecimal.set(0);
        this.velocityZBasicDecimal.set(0);
    }

    public BasicDecimal getVelocityXBasicDecimal()
    {
        return velocityXBasicDecimal;
    }

    /*
    public void setVelocityXBasicDecimal(BasicDecimal velocityXBasicDecimal)
    {
        this.velocityXBasicDecimal = velocityXBasicDecimal;
    }
    */
    
    public BasicDecimal getVelocityYBasicDecimal()
    {
        return velocityYBasicDecimal;
    }

    /*
    public void setVelocityYBasicDecimal(BasicDecimal velocityYBasicDecimal)
    {
        this.velocityYBasicDecimal = velocityYBasicDecimal;
    }
    */

    public BasicDecimal getVelocityZBasicDecimal()
    {
        return velocityXBasicDecimal;
    }
    
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    
    public void setVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection)
    {
        Angle angle = directionUtil.getAngle(direction);
        Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.setVelocity(magnitudeBasicDecimal, angle, otherAngle);
    }

    public void setVelocity(long magnitude, Direction direction, Direction otherDirection)
    {
        Angle angle = directionUtil.getAngle(direction);
        Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.setVelocity(magnitude, angle, otherAngle);
    }

    public void addVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection)
    {
        Angle angle = directionUtil.getAngle(direction);
        Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.addVelocity(magnitudeBasicDecimal, angle, otherAngle);
    }
    
    public void addVelocity(long magnitude, Direction direction, Direction otherDirection)
    {
        Angle angle = directionUtil.getAngle(direction);
        Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.addVelocity(magnitude, angle, otherAngle);
    }

    public void setVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle)
    {
        long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.setVelocity(magnitude, angle, otherAngle);
    }

    public void addVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle)
    {
        long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.addVelocity(magnitude, angle, otherAngle);
    }

    public void setVelocity(long magnitude, Angle angle, Angle otherAngle)
    {
        this.setVelocity(magnitude, angle.getValue(), otherAngle.getValue());
    }

    public void addVelocity(long magnitude, Angle angle, Angle otherAngle)
    {
        this.addVelocity(magnitude, angle.getValue(), otherAngle.getValue());
    }

    protected void setVelocity(long magnitude, short angle, short otherAngle)
    {
        long xVector = (axisMathVectorUtil.calculateX(magnitude, angle) / velocityXBasicDecimal.getScaledFactorValue());
        long yVector = (axisMathVectorUtil.calculateY(magnitude, angle) / velocityYBasicDecimal.getScaledFactorValue());
        long zVector = (axisMathVectorUtil.calculateZ(magnitude, otherAngle) / velocityZBasicDecimal.getScaledFactorValue());

        // StringBuilder stringBuffer = new StringBuilder();

        // stringBuffer.append("Magnitude: ");
        // stringBuffer.append(magnitude);
        // stringBuffer.append(" Angle: ");
        // stringBuffer.append(angle);
        // stringBuffer.append(" xVector");
        // stringBuffer.append(xVector);

        // BlowingInTheWindTestInput.getInstance().append(stringBuffer.toString());

        velocityXBasicDecimal.set(xVector);
        velocityYBasicDecimal.set(yVector);
        velocityZBasicDecimal.set(zVector);

        // BlowingInTheWindTestInput.getInstance().append(this.toString());
    }

    public void addVelocity(long magnitude, short angle, short otherAngle)
    {
        long xVector = (axisMathVectorUtil.calculateX(magnitude, angle) / velocityXBasicDecimal.getScaledFactorValue());
        long yVector = (axisMathVectorUtil.calculateY(magnitude, angle) / velocityYBasicDecimal.getScaledFactorValue());
        long zVector = (axisMathVectorUtil.calculateZ(magnitude, otherAngle) / velocityZBasicDecimal.getScaledFactorValue());

        velocityXBasicDecimal.add(xVector);
        velocityYBasicDecimal.add(yVector);
        velocityZBasicDecimal.add(zVector);
    }

    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("Velocity ");
        stringBuffer.append(PositionStrings.getInstance().X_LABEL);
        stringBuffer.append(this.velocityXBasicDecimal);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().Y_LABEL);
        stringBuffer.append(this.velocityYBasicDecimal);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().Z_LABEL);
        stringBuffer.append(this.velocityZBasicDecimal);

        return stringBuffer.toString();
    }
}
