package org.allbinary.physics.movement;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.logic.math.BasicDecimal;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;

public class DelayedConstantVelocityMovement 
extends BasicConstantVelocityMovement
{
	private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(60);

    public DelayedConstantVelocityMovement(BasicDecimal basicDecimal, BasicVelocityProperties velocityProperties)
    {
    	super(basicDecimal, velocityProperties);
    }
    
    public void init(BasicDecimal speedBasicDecimal, short angle, short otherAngle)
    {
    }
    
    public void process(AllBinaryGameLayer layer) throws Exception
    {
    	if(timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().getStartTime()))
    	{
    		super.process(layer);
    	}
    }
}
