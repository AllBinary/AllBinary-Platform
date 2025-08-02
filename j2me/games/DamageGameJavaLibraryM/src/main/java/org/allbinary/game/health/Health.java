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
package org.allbinary.game.health;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

public class Health implements HealthInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public static final String NAME = "Health Resource";
    
	private int health;
	private int maxHealth;

	private HealthListenerInterface healthListenerInterface = NullHealthListener.NULL_HEALTH_LISTENER;
	
	public Health(int maxHealth)
	{
		this.setMaxHealth(maxHealth);
		this.setHealth(maxHealth);
	}

        @Override
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

        @Override
	public void heal()
	{
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

        @Override
	public void damage(int ahealth)
	{
	    if(ahealth < 0)
	    {
	        StringMaker stringBuffer = new StringMaker();
	        
	        stringBuffer.append("******* Trying to heal when damaging. Damage: ");
	        stringBuffer.append(ahealth);
	        stringBuffer.append(CommonSeps.getInstance().SPACE);
	        stringBuffer.append(this.toString());

	        ForcedLogUtil.log(stringBuffer.toString(), this);
	        return;
	    }
	    
	    int health = this.getHealth() - ahealth;

	    this.setHealth(health);
	}

        @Override
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
	
        @Override
	public int getMaxHealth() {
		return maxHealth;
	}

        @Override
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

        @Override
	public int getHealth() {
		return health;
	}

        @Override
	public void addListener(HealthListenerInterface healthGraphic)
	{
		this.healthListenerInterface = healthGraphic;
	}

	private static final String HEALTH_LABEL = "Health: ";
	private static final String MAX_HEALTH_LABEL = " Max: ";

	public String toString()
	{
	    StringMaker stringBuffer = new StringMaker();

	    stringBuffer.append(Health.HEALTH_LABEL);
	    stringBuffer.append(this.health);
	    stringBuffer.append(Health.MAX_HEALTH_LABEL);
	    stringBuffer.append(this.maxHealth);

	    return stringBuffer.toString();
	}
}
