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

public class CollidableDestroyableDamageableBehavior 
extends CollidableBaseBehavior 
{
    public CollidableDestroyableDamageableBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }
    
    // TODO TWB Special Super Efficient Collision Processing
    public boolean isCollision(
            CollidableCompositeLayer collisionLayer)
    {
        if (this.ownerLayer.getGroupInterface() != collisionLayer.getGroupInterface())
        {
            return super.isCollision(collisionLayer);
        }
        return false;
    }
    
    // TODO TWB Special Super Efficient Collision Processing
    //public void collide(CollidableDestroyableDamageableTeamLayer collisionLayer)
    public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
            throws Exception
    {
        ((CollidableDestroyableDamageableLayer) this.ownerLayer).damage(
                ((CollidableDestroyableDamageableLayer) collidableInterfaceCompositeInterface).getDamage(0), 0);
    }

    public boolean isCollision(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        ForcedLogUtil.log("No Longer Used", this);
        return false;
        /*
        GroupInterfaceCompositeInterface groupInterfaceCompositeInterface = (GroupInterfaceCompositeInterface) collidableInterfaceCompositeInterface;

        if (this.ownerLayer.getGroupInterface() != groupInterfaceCompositeInterface.getGroupInterface())
        {
        //if(this.getTeamInterface() == Team.GOOD)
          // LogUtil.put(LogFactory.getInstance("isCollision: " + this.getTeamInterface().getName() + "==" + teamInterfaceCompositeInterface.getTeamInterface().getName(), this, "isCollision"));
           
            if (LayerCollisionUtil.isCollision(this.ownerLayer, (AllBinaryLayer) collidableInterfaceCompositeInterface))
            {
                return true;
            }
        }
        return false;
        */
    }
    
    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        ForcedLogUtil.log("No Longer Used", this);
        //DamageUtil.process((DamageableInterface) this.ownerLayer, (DamageableInterface) collidableInterfaceCompositeInterface);
        
        //// if (collidableInterface was instance of DamageableInterface)
        // //{
        // //}
    }
}
