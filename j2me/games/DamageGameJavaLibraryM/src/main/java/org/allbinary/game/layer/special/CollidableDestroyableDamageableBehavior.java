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
package org.allbinary.game.layer.special;

import org.allbinary.game.layer.CollidableCompositeLayer;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.game.collision.CollidableBaseBehavior;
import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.combat.damage.DamageUtil;

public class CollidableDestroyableDamageableBehavior 
extends CollidableBaseBehavior 
{
    protected final DamageUtil damageUtil = DamageUtil.getInstance();
    
    public CollidableDestroyableDamageableBehavior(final CollidableCompositeLayer ownerLayer, final boolean collidable)
    {
        super(ownerLayer, collidable);
    }
    
    // TODO TWB Special Super Efficient Collision Processing
    public boolean isCollision(final CollidableCompositeLayer collisionLayer)
    {
        if (this.ownerLayer.getGroupInterface()[0] != collisionLayer.getGroupInterface()[0])
        {
            return super.isCollision(collisionLayer);
        }
        return false;
    }
    
    // TODO TWB Special Super Efficient Collision Processing
    //public void collide(CollidableDestroyableDamageableTeamLayer collisionLayer)
    public void collide(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
            throws Exception
    {
        ((CollidableDestroyableDamageableLayer) this.ownerLayer).damage(
                ((CollidableDestroyableDamageableLayer) collidableInterfaceCompositeInterface).getDamage(0), 0);
    }

    public boolean isCollision(final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        ForcedLogUtil.log("No Longer Used", this);
        return false;
        /*
        GroupInterfaceCompositeInterface groupInterfaceCompositeInterface = (GroupInterfaceCompositeInterface) collidableInterfaceCompositeInterface;

        if (this.ownerLayer.getGroupInterface() != groupInterfaceCompositeInterface.getGroupInterface())
        {
        //if(this.getTeamInterface() == Team.GOOD)
          // LogUtil.put(LogFactory.getInstance("isCollision: " + this.getTeamInterface().getName() + "==" + teamInterfaceCompositeInterface.getTeamInterface().getName(), this, damageUtil.IS_COLLISION));
           
            if (LayerCollisionUtil.isCollision(this.ownerLayer, (AllBinaryLayer) collidableInterfaceCompositeInterface))
            {
                return true;
            }
        }
        return false;
        */
    }
    
    public void collide(final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        ForcedLogUtil.log("No Longer Used", this);
        //DamageUtil.getInstance().process((DamageableInterface) this.ownerLayer, (DamageableInterface) collidableInterfaceCompositeInterface);
        
        //// if (collidableInterface was instance of DamageableInterface)
        // //{
        // //}
    }
}
