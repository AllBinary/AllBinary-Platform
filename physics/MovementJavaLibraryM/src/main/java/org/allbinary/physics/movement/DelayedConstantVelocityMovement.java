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
