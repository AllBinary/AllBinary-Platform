package org.allbinary.physics.movement;

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.math.ScaleFactorFactory;

public class PreciseConstantVelocityMovement 
extends BasicConstantVelocityMovement
{
	private int accumulatedX;
	private int accumulatedY;
	private int accumulatedZ;
	
    public PreciseConstantVelocityMovement(final BasicDecimal basicDecimal, final BasicVelocityProperties velocityProperties)
    {
    	super(basicDecimal, velocityProperties);
    }
    
    @Override
    public void init(final BasicDecimal speedBasicDecimal, final int angle, final int otherAngle)
    {
    }
    
    private final int factorValue = ScaleFactorFactory.getInstance().DEFAULT_SCALE_VALUE;
    
    @Override
    public void process(final AllBinaryGameLayer layer) throws Exception
    {
        final MathUtil mathUtil = MathUtil.getInstance();
        
    	final BasicVelocityProperties velocityProperties = this.getVelocityProperties();

    	this.accumulatedX = (int) (this.accumulatedX + velocityProperties.getVelocityXBasicDecimalP().getUnscaled());
    	this.accumulatedY = (int) (this.accumulatedY + velocityProperties.getVelocityYBasicDecimalP().getUnscaled());
    	this.accumulatedZ = (int) (this.accumulatedZ + velocityProperties.getVelocityZBasicDecimalP().getUnscaled());
    	
        layer.moveDXYZ(
        		this.accumulatedX / this.factorValue,
        		this.accumulatedY / this.factorValue,
        		this.accumulatedZ / this.factorValue
                );
        
        if(mathUtil.abs(this.accumulatedX) > this.factorValue)
        {
        	this.accumulatedX = 0;
        }

        if(mathUtil.abs(this.accumulatedY) > this.factorValue)
        {
        	this.accumulatedY = 0;
        }
        
        if(mathUtil.abs(this.accumulatedZ) > this.factorValue)
        {
        	this.accumulatedZ = 0;
        }
    }
}
