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
package org.allbinary.game.physics.velocity;

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.vector.AxisMathVectorUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.Angle;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonSeps;

public class BasicVelocityProperties implements BasicVelocityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final BasicDecimal velocityXBasicDecimal;
    protected final BasicDecimal velocityYBasicDecimal;
    protected final BasicDecimal velocityZBasicDecimal;

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    public BasicVelocityProperties()
    {
        this.velocityXBasicDecimal = new BasicDecimal(0);
        this.velocityYBasicDecimal = new BasicDecimal(0);
        this.velocityZBasicDecimal = new BasicDecimal(0);
    }

    @Override
    public void zero()
    {
        this.velocityXBasicDecimal.setint(0);
        this.velocityYBasicDecimal.setint(0);
        this.velocityZBasicDecimal.setint(0);
    }

    @Override
    public BasicDecimal getVelocityXBasicDecimalP()
    {
        return this.velocityXBasicDecimal;
    }

    /*
    public void setVelocityXBasicDecimal(BasicDecimal velocityXBasicDecimal)
    {
        this.velocityXBasicDecimal = velocityXBasicDecimal;
    }
    */
    
    @Override
    public BasicDecimal getVelocityYBasicDecimalP()
    {
        return this.velocityYBasicDecimal;
    }

    /*
    public void setVelocityYBasicDecimal(BasicDecimal velocityYBasicDecimal)
    {
        this.velocityYBasicDecimal = velocityYBasicDecimal;
    }
    */

    public BasicDecimal getVelocityZBasicDecimalP()
    {
        return this.velocityZBasicDecimal;
    }
    
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    
    @Override
    public void setVelocityWithBigDecimalAndDirection(final BasicDecimal magnitudeBasicDecimal, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.setVelocityWithBigDecimal(magnitudeBasicDecimal, angle, otherAngle);
    }

    @Override
    public void setVelocityWithDirection(final long magnitude, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.setVelocity(magnitude, angle, otherAngle);
    }

    @Override
    public void addVelocityWithBigDecimalAndDirection(final BasicDecimal magnitudeBasicDecimal, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.addVelocityWithBigDecimal(magnitudeBasicDecimal, angle, otherAngle);
    }
    
    @Override
    public void addVelocityWithDirection(final long magnitude, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.addVelocity(magnitude, angle, otherAngle);
    }

    @Override
    public void setVelocityWithBigDecimal(final BasicDecimal magnitudeBasicDecimal, final Angle angle, final Angle otherAngle)
    {
        final long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.setVelocity(magnitude, angle, otherAngle);
    }

    @Override
    public void addVelocityWithBigDecimal(final BasicDecimal magnitudeBasicDecimal, final Angle angle, final Angle otherAngle)
    {
        final long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.addVelocity(magnitude, angle, otherAngle);
    }

    @Override
    public void setVelocity(final long magnitude, final Angle angle, final Angle otherAngle)
    {
        this.setVelocityi(magnitude, (int) angle.getValue(), (int) otherAngle.getValue());
    }

    @Override
    public void addVelocity(final long magnitude, final Angle angle, final Angle otherAngle)
    {
        this.addVelocityi(magnitude, (int) angle.getValue(), (int) otherAngle.getValue());
    }

    public void setVelocityi(final long magnitude, final int angle, final int otherAngle)
    {
        final long xVector = (this.axisMathVectorUtil.calculateX(magnitude, angle) / this.velocityXBasicDecimal.getScaledFactorValue());
        final long yVector = (this.axisMathVectorUtil.calculateY(magnitude, angle) / this.velocityYBasicDecimal.getScaledFactorValue());
        final long zVector = (this.axisMathVectorUtil.calculateZ(magnitude, otherAngle) / this.velocityZBasicDecimal.getScaledFactorValue());

        //this.logUtil.putF(this.toString(), this, commonStrings.ADD);

        // StringMaker stringBuffer = new StringMaker();

        // stringBuffer.append("Magnitude: ");
        // stringBuffer.append(magnitude);
        // stringBuffer.append(" Angle: ");
        // stringBuffer.append(angle);
        // stringBuffer.append(" xVector");
        // stringBuffer.append(xVector);

        // BlowingInTheWindTestInput.getInstance().append(stringBuffer.toString());

        this.velocityXBasicDecimal.setlong(xVector);
        this.velocityYBasicDecimal.setlong(yVector);
        this.velocityZBasicDecimal.setlong(zVector);

        // BlowingInTheWindTestInput.getInstance().append(this.toString());
    }

    @Override
    public void addVelocityi(final long magnitude, final int angle, final int otherAngle)
    {
        final long xVector = (this.axisMathVectorUtil.calculateX(magnitude, angle) / this.velocityXBasicDecimal.getScaledFactorValue());
        final long yVector = (this.axisMathVectorUtil.calculateY(magnitude, angle) / this.velocityYBasicDecimal.getScaledFactorValue());
        final long zVector = (this.axisMathVectorUtil.calculateZ(magnitude, otherAngle) / this.velocityZBasicDecimal.getScaledFactorValue());
        
        //this.logUtil.putF(this.toString(), this, commonStrings.ADD);
        this.velocityXBasicDecimal.addlong(xVector);
        this.velocityYBasicDecimal.addlong(yVector);
        this.velocityZBasicDecimal.addlong(zVector);
    }

    public String toString()
    {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final PositionStrings positionStrings = PositionStrings.getInstance();
        final StringMaker stringBuffer = new StringMaker();

        final StringUtil stringUtil = StringUtil.getInstance();
        
        stringBuffer.append("Velocity ");
        stringBuffer.append(positionStrings.X_LABEL);
        stringBuffer.append(stringUtil.toString(this.velocityXBasicDecimal));
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Y_LABEL);
        stringBuffer.append(stringUtil.toString(this.velocityYBasicDecimal));
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.Z_LABEL);
        stringBuffer.append(stringUtil.toString(this.velocityZBasicDecimal));

        return stringBuffer.toString();
    }
}
