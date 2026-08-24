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
package org.allbinary.physics.movement;

import jsinterop.annotations.JsType;

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.vector.AxisMathVectorUtil;
import org.allbinary.math.AngleFactory;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

/**
 * 
 * @author user
 */

@JsType
public class BasicConstantVelocityMovement 
extends Movement 
implements VelocityInterfaceCompositeInterface
{
    private BasicVelocityProperties velocityProperties;

    private BasicDecimal speedBasicDecimal = BasicDecimal.ZERO_BIGDECIMAL;

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    @JsConstructor
    public BasicConstantVelocityMovement(BasicDecimal basicDecimal, BasicVelocityProperties velocityProperties)
    {
        this.setSpeedBasicDecimal(basicDecimal);
        this.velocityProperties = velocityProperties;
    }

    @Override
    @JsMethod
    public void init(BasicDecimal speedBasicDecimal, int angle, int otherAngle)
    {
        this.speedBasicDecimal = speedBasicDecimal;
        
        AngleFactory angleFactory = AngleFactory.getInstance();
        
        this.velocityProperties.setVelocityWithBigDecimal(speedBasicDecimal,
                angleFactory.getAt(angle), angleFactory.getAt(otherAngle));
    }

    @JsMethod
    public void moveOutsideRadius(AllBinaryLayer layer, long radius, int angle, int otherAngle)
    {
        int scaleFactorValue = this.speedBasicDecimal.getScaledFactorValue();
        
        int xVector = (int) (this.axisMathVectorUtil.calculateX(radius, angle) / scaleFactorValue);
        int yVector = (int) (this.axisMathVectorUtil.calculateY(radius, angle) / scaleFactorValue);
        int zVector = (int) (this.axisMathVectorUtil.calculateZ(radius, otherAngle) / scaleFactorValue);

        layer.moveDXYZ(xVector, yVector, zVector);
    }

    @Override
    @JsMethod
    public void process(AllBinaryGameLayer layer) throws Exception
    {
        layer.moveDXYZ(
                this.velocityProperties.getVelocityXBasicDecimalP().getScaled(),
                this.velocityProperties.getVelocityYBasicDecimalP().getScaled(),
                this.velocityProperties.getVelocityZBasicDecimalP().getScaled()
                );
    }

    @JsMethod
    public String toString()
    {
    	return this.velocityProperties.toString();
    }

    @Override
    @JsMethod
    public void stop()
    {
        this.velocityProperties.zero();
    }

    @Override
    @JsMethod
    public BasicVelocityProperties getVelocityProperties()
    {
        return this.velocityProperties;
    }

    @JsMethod
    public void setVelocityProperties(BasicVelocityProperties velocityProperties)
    {
        this.velocityProperties = velocityProperties;
    }

    @JsMethod
    protected void setSpeedBasicDecimal(BasicDecimal speedBasicDecimal)
    {
        this.speedBasicDecimal = speedBasicDecimal;
    }

    @JsMethod
    protected BasicDecimal getSpeedBasicDecimal()
    {
        return this.speedBasicDecimal;
    }

}
