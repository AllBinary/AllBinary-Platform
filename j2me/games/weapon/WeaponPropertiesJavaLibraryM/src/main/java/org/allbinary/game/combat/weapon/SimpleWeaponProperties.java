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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;


@JsType
public class SimpleWeaponProperties
{
    private int damage;
    private short dissipation;
    private int range;
    
    @JsMethod
    public void set(SimpleWeaponProperties simpleWeaponProperties)
    {
        this.setDamage(simpleWeaponProperties.getDamage());
        this.setDissipation(simpleWeaponProperties.getDissipation());
        this.setRange(simpleWeaponProperties.getRange());
    }
    
    @JsMethod
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
    
    @JsMethod
    public int getDamage()
    {
        return this.damage;
    }
    
    @JsMethod
    public void setDissipation(short dissipation)
    {
        this.dissipation = dissipation;
    }
    
    @JsMethod
    public short getDissipation()
    {
        return this.dissipation;
    }

    /**
     * @return the range
     */
    @JsMethod
    public int getRange()
    {
        return this.range;
    }

    @JsMethod
    public void setRange(int range)
    {
        this.range = range;
    }
}
