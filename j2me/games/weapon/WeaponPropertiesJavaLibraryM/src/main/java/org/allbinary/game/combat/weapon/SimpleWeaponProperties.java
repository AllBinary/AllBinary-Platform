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
package org.allbinary.game.combat.weapon;

public class SimpleWeaponProperties
{
    private int damage;
    private short dissipation;
    private int range;
    
    public void set(SimpleWeaponProperties simpleWeaponProperties)
    {
        this.setDamage(simpleWeaponProperties.getDamage());
        this.setDissipation(simpleWeaponProperties.getDissipation());
        this.setRange(simpleWeaponProperties.getRange());
    }
    
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void setDissipation(short dissipation)
    {
        this.dissipation = dissipation;
    }
    
    public short getDissipation()
    {
        return dissipation;
    }

    /**
     * @return the range
     */
    public int getRange()
    {
        return range;
    }

    public void setRange(int range)
    {
        this.range = range;
    }
}
