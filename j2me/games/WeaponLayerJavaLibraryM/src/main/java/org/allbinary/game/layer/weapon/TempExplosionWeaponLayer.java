package org.allbinary.game.layer.weapon;

import org.allbinary.physics.movement.Movement;

import org.allbinary.animation.Animation;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.view.ViewPosition;

public class TempExplosionWeaponLayer
extends SimpleWeaponLayer
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public final WeaponProperties weaponProperties = new WeaponProperties(0, 1000, (short) 0);

    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);
	
    public TempExplosionWeaponLayer(final String name, final Movement movement,
            final Animation animationInterface,
            final Rectangle rectangle, final ViewPosition viewPosition, final int timeDelay)
            throws Exception
    {
        super(name, movement, animationInterface, rectangle, viewPosition);
        
        this.timeDelayHelper.delay = timeDelay;
    }
    
    public void processTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
    	if(timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
    	{
			this.getCollidableInferface().collide(this);
			this.totalDamage = this.getInitDamage() + 1;
			
			//PreLogUtil.put("Self Destructing", this, GameStrings.getInstance().PROCESS_TICK);
    	}
    	
    	super.processTick(allBinaryLayerManager);
    }
}
