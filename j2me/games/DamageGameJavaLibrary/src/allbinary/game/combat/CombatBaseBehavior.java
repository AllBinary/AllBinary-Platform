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
package allbinary.game.combat;

import allbinary.game.combat.damage.DamageableBaseBehavior;
import allbinary.game.combat.destroy.DestroyableBaseBehavior;

public class CombatBaseBehavior
{
    private final DamageableBaseBehavior damageableBaseBehavior;
    private final DestroyableBaseBehavior destroyableBaseBehavior;
    
    private boolean readyForExplosion;
    
    public CombatBaseBehavior(DamageableBaseBehavior damageableBaseBehavior,
            DestroyableBaseBehavior destroyableBaseBehavior)
    {
        this.damageableBaseBehavior = damageableBaseBehavior;
        this.destroyableBaseBehavior = destroyableBaseBehavior;
    }

    public DamageableBaseBehavior getDamageableBaseBehavior()
    {
        return damageableBaseBehavior;
    }

    public DestroyableBaseBehavior getDestroyableBaseBehavior()
    {
        return destroyableBaseBehavior;
    }
    
    public final boolean isReadyForExplosion()
    {
        return readyForExplosion;
    }

    public final void setReadyForExplosion(boolean readyForExplosion)
    {
        this.readyForExplosion = readyForExplosion;
    }    
}
