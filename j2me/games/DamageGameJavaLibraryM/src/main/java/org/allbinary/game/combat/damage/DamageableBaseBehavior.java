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
package org.allbinary.game.combat.damage;

public class DamageableBaseBehavior implements DamageableInterface
{
    private static final DamageableBaseBehavior instance = new DamageableBaseBehavior();
    
    public static DamageableBaseBehavior getInstance()
    {
        return instance;
    }
 
    @Override
    public String getName()
    {
        return this.getClass().getName();
    }
    
    @Override
    public void damage(int damage,int damageType)
    throws Exception
    {
        
    }

    //Gives out damage that would be expected of a blunt object
    @Override
    public int getDamage(int damageType)
    throws Exception
    {
        return 6000;
    }
}
