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
    private BasicDecimal velocityXBasicDecimal;
    private BasicDecimal velocityYBasicDecimal;

    public BasicVelocityProperties()
    {
        this.setVelocityXBasicDecimal(new BasicDecimal());
        this.setVelocityYBasicDecimal(new BasicDecimal());
    }

    public void zero()
    {
        this.getVelocityXBasicDecimal().set(0);
        this.getVelocityYBasicDecimal().set(0);
    }

    public BasicDecimal getVelocityXBasicDecimal()
    {
        return velocityXBasicDecimal;
    }

    public void setVelocityXBasicDecimal(BasicDecimal velocityXBasicDecimal)
    {
        this.velocityXBasicDecimal = velocityXBasicDecimal;
    }

    public BasicDecimal getVelocityYBasicDecimal()
    {
        return velocityYBasicDecimal;
    }

    public void setVelocityYBasicDecimal(BasicDecimal velocityYBasicDecimal)
    {
        this.velocityYBasicDecimal = velocityYBasicDecimal;
    }

    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    
    public void setVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction)
    {
        Angle angle = directionUtil.getAngle(direction);
        this.setVelocity(magnitudeBasicDecimal, angle);
    }

    public void addVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction)
    {
        Angle angle = directionUtil.getAngle(direction);
        this.addVelocity(magnitudeBasicDecimal, angle);
    }

    public void setVelocity(long magnitude, Direction direction)
    {
        Angle angle = directionUtil.getAngle(direction);
        this.setVelocity(magnitude, angle);
    }

    public void addVelocity(long magnitude, Direction direction)
    {
        Angle angle = directionUtil.getAngle(direction);
        this.addVelocity(magnitude, angle);
    }

    public void setVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle)
    {
        long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.setVelocity(magnitude, angle);
    }

    public void addVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle)
    {
        long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.addVelocity(magnitude, angle);
    }

    public void setVelocity(long magnitude, Angle angle)
    {
        this.setVelocity(magnitude, angle.getValue());
    }

    public void addVelocity(long magnitude, Angle angle)
    {
        this.addVelocity(magnitude, angle.getValue());
    }
    
    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    protected void setVelocity(long magnitude, short angle)
    {
        BasicDecimal velocityXBasicDecimal = this.getVelocityXBasicDecimal();
        BasicDecimal velocityYBasicDecimal = this.getVelocityYBasicDecimal();
        
        long xVector = (axisMathVectorUtil.calculateX(magnitude, angle) / velocityXBasicDecimal.getScaledFactorValue());
        long yVector = (axisMathVectorUtil.calculateY(magnitude, angle) / velocityYBasicDecimal.getScaledFactorValue());

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

        // BlowingInTheWindTestInput.getInstance().append(this.toString());
    }

    public void addVelocity(long magnitude, short angle)
    {
        BasicDecimal velocityXBasicDecimal = this.getVelocityXBasicDecimal();
        BasicDecimal velocityYBasicDecimal = this.getVelocityYBasicDecimal();

        long xVector = (axisMathVectorUtil.calculateX(magnitude, angle) / velocityXBasicDecimal.getScaledFactorValue());
        long yVector = (axisMathVectorUtil.calculateY(magnitude, angle) / velocityYBasicDecimal.getScaledFactorValue());

        velocityXBasicDecimal.add(xVector);
        velocityYBasicDecimal.add(yVector);
    }

    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("Velocity ");
        stringBuffer.append(PositionStrings.getInstance().X_LABEL);
        stringBuffer.append(this.getVelocityXBasicDecimal());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(PositionStrings.getInstance().Y_LABEL);
        stringBuffer.append(this.getVelocityYBasicDecimal());

        return stringBuffer.toString();
    }
}
