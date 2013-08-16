package org.allbinary.physics.movement;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.logic.math.BasicDecimal;
import allbinary.logic.math.ScaleFactorFactory;

public class PreciseConstantVelocityMovement 
extends BasicConstantVelocityMovement
{
	private int accumulatedX;
	private int accumulatedY;
	private int accumulatedZ;
	
    public PreciseConstantVelocityMovement(BasicDecimal basicDecimal, BasicVelocityProperties velocityProperties)
    {
    	super(basicDecimal, velocityProperties);
    }
    
    public void init(BasicDecimal speedBasicDecimal, short angle, short otherAngle)
    {
    }
    
    private final int factorValue = ScaleFactorFactory.getInstance().DEFAULT_SCALE_VALUE;
    
    public void process(AllBinaryGameLayer layer) throws Exception
    {
    	BasicVelocityProperties velocityProperties = this.getVelocityProperties();

    	accumulatedX = (int) (accumulatedX + velocityProperties.getVelocityXBasicDecimal().getUnscaled());
    	accumulatedY = (int) (accumulatedY + velocityProperties.getVelocityYBasicDecimal().getUnscaled());
    	accumulatedZ = (int) (accumulatedZ + velocityProperties.getVelocityZBasicDecimal().getUnscaled());
    	
        layer.move(
        		accumulatedX / factorValue,
        		accumulatedY / factorValue,
        		accumulatedZ / factorValue
                );
        
        if(Math.abs(accumulatedX) > factorValue)
        {
        	accumulatedX = 0;
        }

        if(Math.abs(accumulatedY) > factorValue)
        {
        	accumulatedY = 0;
        }
        
        if(Math.abs(accumulatedZ) > factorValue)
        {
        	accumulatedZ = 0;
        }
    }
}
