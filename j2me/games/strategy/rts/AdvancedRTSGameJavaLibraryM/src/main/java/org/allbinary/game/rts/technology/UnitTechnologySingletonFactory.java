/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.rts.technology;

/**
 *
 * @author user
 */
public class UnitTechnologySingletonFactory {

    private static final UnitTechnologySingletonFactory instance =
        new UnitTechnologySingletonFactory();

    /**
     * @return the instance
     */
    public static UnitTechnologySingletonFactory getInstance()
    {
        return instance;
    }

    private int damage;
    private int health;
    private int reload;
    private int repair;
    private int speed;

    private UnitTechnologySingletonFactory()
    {
    }

    public void init()
    {
        this.setDamage(0);
        this.setHealth(0);
        this.setReload(0);
        this.setRepair(0);
        this.setSpeed(0);
    }

    /**
     * @return the damage
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * @return the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * @return the reload
     */
    public int getReload()
    {
        return reload;
    }

    /**
     * @param reload the reload to set
     */
    public void setReload(int reload)
    {
        this.reload = reload;
    }

    /**
     * @return the repair
     */
    public int getRepair()
    {
        return repair;
    }

    /**
     * @param repair the repair to set
     */
    public void setRepair(int repair)
    {
        this.repair = repair;
    }

    /**
     * @return the speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}
