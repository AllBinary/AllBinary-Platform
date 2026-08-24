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

import jsinterop.annotations.JsType;

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
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class BasicVelocityProperties implements BasicVelocityInterface
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final BasicDecimal velocityXBasicDecimal;
    @JsProperty
    protected final BasicDecimal velocityYBasicDecimal;
    @JsProperty
    protected final BasicDecimal velocityZBasicDecimal;

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    @JsConstructor
    public BasicVelocityProperties()
    {
        this.velocityXBasicDecimal = new BasicDecimal(0);
        this.velocityYBasicDecimal = new BasicDecimal(0);
        this.velocityZBasicDecimal = new BasicDecimal(0);
    }

    @Override
    @JsMethod
    public void zero()
    {
        this.velocityXBasicDecimal.setint(0);
        this.velocityYBasicDecimal.setint(0);
        this.velocityZBasicDecimal.setint(0);
    }

    @Override
    @JsMethod
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
    @JsMethod
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

    @JsMethod
    public BasicDecimal getVelocityZBasicDecimalP()
    {
        return this.velocityZBasicDecimal;
    }
    
    @JsProperty
    protected final DirectionUtil directionUtil = DirectionUtil.getInstance();
    
    @Override
    @JsMethod
    public void setVelocityWithBigDecimalAndDirection(final BasicDecimal magnitudeBasicDecimal, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.setVelocityWithBigDecimal(magnitudeBasicDecimal, angle, otherAngle);
    }

    @Override
    @JsMethod
    public void setVelocityWithDirection(final long magnitude, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.setVelocity(magnitude, angle, otherAngle);
    }

    @Override
    @JsMethod
    public void addVelocityWithBigDecimalAndDirection(final BasicDecimal magnitudeBasicDecimal, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.addVelocityWithBigDecimal(magnitudeBasicDecimal, angle, otherAngle);
    }
    
    @Override
    @JsMethod
    public void addVelocityWithDirection(final long magnitude, final Direction direction, final Direction otherDirection)
    {
        final Angle angle = this.directionUtil.getAngle(direction);
        final Angle otherAngle = this.directionUtil.getAngle(otherDirection);
        this.addVelocity(magnitude, angle, otherAngle);
    }

    @Override
    @JsMethod
    public void setVelocityWithBigDecimal(final BasicDecimal magnitudeBasicDecimal, final Angle angle, final Angle otherAngle)
    {
        final long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.setVelocity(magnitude, angle, otherAngle);
    }

    @Override
    @JsMethod
    public void addVelocityWithBigDecimal(final BasicDecimal magnitudeBasicDecimal, final Angle angle, final Angle otherAngle)
    {
        final long magnitude = magnitudeBasicDecimal.getUnscaled();
        this.addVelocity(magnitude, angle, otherAngle);
    }

    @Override
    @JsMethod
    public void setVelocity(final long magnitude, final Angle angle, final Angle otherAngle)
    {
        this.setVelocityi(magnitude, (int) angle.getValue(), (int) otherAngle.getValue());
    }

    @Override
    @JsMethod
    public void addVelocity(final long magnitude, final Angle angle, final Angle otherAngle)
    {
        this.addVelocityi(magnitude, (int) angle.getValue(), (int) otherAngle.getValue());
    }

    @JsMethod
    public void setVelocityi(final long magnitude, final int angle, final int otherAngle)
    {
        final long xVector = (this.axisMathVectorUtil.calculateX(magnitude, angle) / this.velocityXBasicDecimal.getScaledFactorValue());
        final long yVector = (this.axisMathVectorUtil.calculateY(magnitude, angle) / this.velocityYBasicDecimal.getScaledFactorValue());
        final long zVector = (this.axisMathVectorUtil.calculateZ(magnitude, otherAngle) / this.velocityZBasicDecimal.getScaledFactorValue());

        //this.logUtil.putF(this.toString(), this, this.commonStrings.ADD);

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
    @JsMethod
    public void addVelocityi(final long magnitude, final int angle, final int otherAngle)
    {
        final long xVector = (this.axisMathVectorUtil.calculateX(magnitude, angle) / this.velocityXBasicDecimal.getScaledFactorValue());
        final long yVector = (this.axisMathVectorUtil.calculateY(magnitude, angle) / this.velocityYBasicDecimal.getScaledFactorValue());
        final long zVector = (this.axisMathVectorUtil.calculateZ(magnitude, otherAngle) / this.velocityZBasicDecimal.getScaledFactorValue());
        
        //this.logUtil.putF(this.toString(), this, this.commonStrings.ADD);
        this.velocityXBasicDecimal.addlong(xVector);
        this.velocityYBasicDecimal.addlong(yVector);
        this.velocityZBasicDecimal.addlong(zVector);
    }

    @JsMethod
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
