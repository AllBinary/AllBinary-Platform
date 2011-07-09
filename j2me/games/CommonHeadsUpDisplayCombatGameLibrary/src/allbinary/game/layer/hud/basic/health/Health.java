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
package allbinary.game.layer.hud.basic.health;

import allbinary.game.health.HealthInterface;
import allbinary.game.health.HealthListenerInterface;

public class Health implements HealthInterface
{
	private int health;
	private int maxHealth;

	private HealthListenerInterface healthListenerInterface;
	
	public Health(int maxHealth)
	{
		this.setMaxHealth(maxHealth);
		this.setHealth(maxHealth);
	}
	
	public void heal(int ahealth) 
	{
	    int newHealth = this.getHealth() + ahealth;
	    
        if (newHealth > this.getMaxHealth()) 
        {
            this.setHealth(this.getMaxHealth());
        }
        else
        {
            this.setHealth(newHealth);
        }
	}

	public void heal() {
		this.setHealth(this.getMaxHealth());
	}

	public boolean isDamaged()
	{
	    if(this.getHealth() != this.getMaxHealth())
	    {
	        return true;
	    }
	    else
	    {
	        return false;
	    }
	}

	public void damage(int ahealth)
	{
	    int health = this.getHealth() - ahealth;

		this.setHealth(health);
        //LogUtil.put(LogFactory.getInstance("Health: " + this.getHealth() + " Lost: " + ahealth, this, "damage"));
	}

	public boolean isAlive() 
	{
		if (this.getHealth() <= 0) 
		{
			return false;
		} else 
		{
			return true;
		}
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setHealth(int health) {
	    
        if (health > this.getMaxHealth()) 
        {
            this.health = this.getMaxHealth();
        }
        else
        if(health < 0)
        {
            this.health = 0;
        }
        else
        {
            this.health = health;
        }
		
		if(this.healthListenerInterface != null)
		{
		   this.healthListenerInterface.onHealthChange();//this);
		}
	}

	public int getHealth() {
		return health;
	}

	public void addListener(HealthListenerInterface healthGraphic)
	{
		this.healthListenerInterface = healthGraphic;
	}
}
