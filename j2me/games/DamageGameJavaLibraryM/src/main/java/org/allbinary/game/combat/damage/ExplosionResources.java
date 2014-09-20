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

public class ExplosionResources
{
    private static final ExplosionResources instance = new ExplosionResources();

    public static ExplosionResources getInstance()
    {
        return instance;
    }
    
    private ExplosionResources()
    {
        
    }
    
    public final String EXPLOSION_90_RESOURCE = "/explosion_sprite_90_by_90.png";
    public final String EXPLOSION_60_RESOURCE = "/explosion_sprite_60_by_60.png";
    public final String HALF_EXPLOSION_RESOURCE = "/explosion_sprite_30_by_30.png";
    public final String THIRD_EXPLOSION_RESOURCE = "/explosion_sprite_15_by_15.png";
}
