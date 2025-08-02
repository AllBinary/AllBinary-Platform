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

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.vector.AxisMathVectorUtil;
import org.allbinary.math.AngleFactory;

/**
 * 
 * @author user
 */
public class BasicConstantVelocityMovement 
extends Movement 
implements VelocityInterfaceCompositeInterface
{
    private BasicVelocityProperties velocityProperties;

    private BasicDecimal speedBasicDecimal = BasicDecimal.ZERO_BIGDECIMAL;

    private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
    
    public BasicConstantVelocityMovement(BasicDecimal basicDecimal, BasicVelocityProperties velocityProperties)
    {
        this.setSpeedBasicDecimal(basicDecimal);
        this.velocityProperties = velocityProperties;
    }
    
    public BasicConstantVelocityMovement()
    {
        this.setSpeedBasicDecimal(BasicDecimal.ZERO_BIGDECIMAL);
        this.velocityProperties = new BasicVelocityProperties();
    }

    @Override
    public void init(BasicDecimal speedBasicDecimal, int angle, int otherAngle)
    {
        this.speedBasicDecimal = speedBasicDecimal;
        
        AngleFactory angleFactory = AngleFactory.getInstance();
        
        this.velocityProperties.setVelocity(speedBasicDecimal, 
                angleFactory.getInstance(angle), angleFactory.getInstance(otherAngle));
    }

    public void moveOutsideRadius(AllBinaryLayer layer, long radius, int angle, int otherAngle)
    {
        int scaleFactorValue = this.speedBasicDecimal.getScaledFactorValue();
        
        int xVector = (int) (axisMathVectorUtil.calculateX(radius, angle) / scaleFactorValue);
        int yVector = (int) (axisMathVectorUtil.calculateY(radius, angle) / scaleFactorValue);
        int zVector = (int) (axisMathVectorUtil.calculateZ(radius, otherAngle) / scaleFactorValue);

        layer.move(xVector, yVector, zVector);
    }

    @Override
    public void process(AllBinaryGameLayer layer) throws Exception
    {
        layer.move(
                this.velocityProperties.getVelocityXBasicDecimalP().getScaled(),
                this.velocityProperties.getVelocityYBasicDecimalP().getScaled(),
                this.velocityProperties.getVelocityZBasicDecimalP().getScaled()
                );
    }

    public String toString()
    {
    	return this.velocityProperties.toString();
    }

    @Override
    public void stop()
    {
        this.velocityProperties.zero();
    }

    @Override
    public BasicVelocityProperties getVelocityProperties()
    {
        return velocityProperties;
    }

    public void setVelocityProperties(BasicVelocityProperties velocityProperties)
    {
        this.velocityProperties = velocityProperties;
    }

    protected void setSpeedBasicDecimal(BasicDecimal speedBasicDecimal)
    {
        this.speedBasicDecimal = speedBasicDecimal;
    }

    protected BasicDecimal getSpeedBasicDecimal()
    {
        return speedBasicDecimal;
    }

}
