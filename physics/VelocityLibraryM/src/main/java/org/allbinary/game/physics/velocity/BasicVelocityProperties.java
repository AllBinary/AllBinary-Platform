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

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.vector.AxisMathVectorUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.Angle;
import org.allbinary.math.PositionStrings;

public class BasicVelocityProperties implements BasicVelocityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
    
    public void setVelocity(final BasicDecimal magnitudeBasicDecimal, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = directionUtil.getAngle(direction);
        final Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.setVelocity(magnitudeBasicDecimal, angle, otherAngle);
    }

    public void setVelocity(final long magnitude, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = directionUtil.getAngle(direction);
        final Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.setVelocity(magnitude, angle, otherAngle);
    }

    public void addVelocity(final BasicDecimal magnitudeBasicDecimal, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = directionUtil.getAngle(direction);
        final Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.addVelocity(magnitudeBasicDecimal, angle, otherAngle);
    }
    
    public void addVelocity(final long magnitude, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = directionUtil.getAngle(direction);
        final Angle otherAngle = directionUtil.getAngle(otherDirection);
        this.addVelocity(magnitude, angle, otherAngle);
    }

    public void setVelocity(final BasicDecimal magnitudeBasicDecimal, final Angle angle, final Angle otherAngle)
    {
        final long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.setVelocity(magnitude, angle, otherAngle);
    }

    public void addVelocity(final BasicDecimal magnitudeBasicDecimal, final Angle angle, final Angle otherAngle)
    {
        final long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.addVelocity(magnitude, angle, otherAngle);
    }

    public void setVelocity(final long magnitude, final Angle angle, final Angle otherAngle)
    {
        this.setVelocity(magnitude, angle.getValue(), otherAngle.getValue());
    }

    public void addVelocity(final long magnitude, final Angle angle, final Angle otherAngle)
    {
        this.addVelocity(magnitude, angle.getValue(), otherAngle.getValue());
    }

    public void setVelocity(final long magnitude, final short angle, final short otherAngle)
    {
        final long xVector = (axisMathVectorUtil.calculateX(magnitude, angle) / velocityXBasicDecimal.getScaledFactorValue());
        final long yVector = (axisMathVectorUtil.calculateY(magnitude, angle) / velocityYBasicDecimal.getScaledFactorValue());
        final long zVector = (axisMathVectorUtil.calculateZ(magnitude, otherAngle) / velocityZBasicDecimal.getScaledFactorValue());

        //logUtil.put(this.toString(), this, commonStrings.ADD);

        // StringMaker stringBuffer = new StringMaker();

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

    public void addVelocity(final long magnitude, final short angle, final short otherAngle)
    {
        final long xVector = (axisMathVectorUtil.calculateX(magnitude, angle) / velocityXBasicDecimal.getScaledFactorValue());
        final long yVector = (axisMathVectorUtil.calculateY(magnitude, angle) / velocityYBasicDecimal.getScaledFactorValue());
        final long zVector = (axisMathVectorUtil.calculateZ(magnitude, otherAngle) / velocityZBasicDecimal.getScaledFactorValue());
        
        //logUtil.put(this.toString(), this, commonStrings.ADD);
        velocityXBasicDecimal.add(xVector);
        velocityYBasicDecimal.add(yVector);
        velocityZBasicDecimal.add(zVector);
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
