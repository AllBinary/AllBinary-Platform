package allbinary.game.layer.weapon;

import org.allbinary.physics.movement.Movement;

import allbinary.animation.Animation;
import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;
import allbinary.view.ViewPosition;

public class TempExplosionWeaponLayer
extends SimpleWeaponLayer
{
	public final WeaponProperties weaponProperties = new WeaponProperties(0, 1000, (short) 0);
	
	private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(0);
	
    public TempExplosionWeaponLayer(Movement movement,
            Animation animationInterface,
            Rectangle rectangle, ViewPosition viewPosition, int timeDelay)
            throws Exception
    {
        super(movement, animationInterface, rectangle, viewPosition);
        
        timeDelayHelper.setDelay(timeDelay);
    }
    
    public void processTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
    	if(timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().getStartTime()))
    	{
			this.getCollidableInferface().collide(this);
			this.totalDamage = this.getInitDamage() + 1;
			
			//PreLogUtil.put("Self Destructing", this, GameStrings.getInstance().PROCESS_TICK);
    	}
    	
    	super.processTick(allBinaryLayerManager);
    }
}
