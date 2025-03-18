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
    
    public void init(final BasicDecimal speedBasicDecimal, final short angle, final short otherAngle)
    {
    }
    
    private final int factorValue = ScaleFactorFactory.getInstance().DEFAULT_SCALE_VALUE;
    
    public void process(final AllBinaryGameLayer layer) throws Exception
    {
        final MathUtil mathUtil = MathUtil.getInstance();
        
    	final BasicVelocityProperties velocityProperties = this.getVelocityProperties();

    	accumulatedX = (int) (accumulatedX + velocityProperties.getVelocityXBasicDecimal().getUnscaled());
    	accumulatedY = (int) (accumulatedY + velocityProperties.getVelocityYBasicDecimal().getUnscaled());
    	accumulatedZ = (int) (accumulatedZ + velocityProperties.getVelocityZBasicDecimal().getUnscaled());
    	
        layer.move(
        		accumulatedX / factorValue,
        		accumulatedY / factorValue,
        		accumulatedZ / factorValue
                );
        
        if(mathUtil.abs(accumulatedX) > factorValue)
        {
        	accumulatedX = 0;
        }

        if(mathUtil.abs(accumulatedY) > factorValue)
        {
        	accumulatedY = 0;
        }
        
        if(mathUtil.abs(accumulatedZ) > factorValue)
        {
        	accumulatedZ = 0;
        }
    }
}
