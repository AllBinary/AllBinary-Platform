package org.allbinary.android.input.motion;

import allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import allbinary.time.TimeDelayHelper;

public class AnalogControllerHelper {

	private final static AnalogControllerHelper instance = new AnalogControllerHelper();
	
	private AnalogControllerHelper()
	{
		
	}
	
	private final int SCALE = AnalogControllerConfigurationFactory.getInstance().SCALE;

	public static AnalogControllerHelper getInstance() {
		return instance;
	}
	
	private final TimeDelayHelper rightTimeElapsedHelper = new TimeDelayHelper(0);
	private final TimeDelayHelper leftTimeElapsedHelper = new TimeDelayHelper(0);
	private final TimeDelayHelper downTimeElapsedHelper = new TimeDelayHelper(0);
	private final TimeDelayHelper upTimeElapsedHelper = new TimeDelayHelper(0);
    
    public void right(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
    throws Exception
    {
    	this.rightTimeElapsedHelper.setDelay(SCALE - xAnalogValue);
    	
    	if(this.rightTimeElapsedHelper.isTime())
    	{
    		collidableDestroyableDamageableLayer.right();
    	}

    }
    
    public void left(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
    throws Exception
    {
    	this.leftTimeElapsedHelper.setDelay(SCALE + xAnalogValue);
    	
    	if(this.leftTimeElapsedHelper.isTime())
    	{
    		collidableDestroyableDamageableLayer.left();
		}
   }
    
    public void up(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
    throws Exception
    {
    	this.upTimeElapsedHelper.setDelay(SCALE - xAnalogValue);
    	
		if (this.upTimeElapsedHelper.isTime())
		{
			collidableDestroyableDamageableLayer.up();
		}
    }
    
    public void down(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int xAnalogValue)
    throws Exception
    {
    	this.downTimeElapsedHelper.setDelay(SCALE + xAnalogValue);
    	
    	if(this.downTimeElapsedHelper.isTime())
		{
    		collidableDestroyableDamageableLayer.down();
		}
    }

}
