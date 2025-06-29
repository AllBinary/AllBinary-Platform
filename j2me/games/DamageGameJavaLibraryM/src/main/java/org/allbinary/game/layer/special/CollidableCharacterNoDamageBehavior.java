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
import org.allbinary.game.layer.pickup.PickedUpLayerInterface;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;

public class CollidableCharacterNoDamageBehavior
extends CollidableDestroyableDamageableBehavior 
{
    public CollidableCharacterNoDamageBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("collidableLayer", this, "collide"));        
        CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
        CollisionType collisionType = collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(this.ownerLayer);

        if (collisionType == collisionTypeFactory.PICKUP)
        {
            ((CollidableDestroyableDamageableLayer) this.ownerLayer).getPickupBehavior().doPickup(
                    (PickedUpLayerInterface) collidableInterfaceCompositeInterface);
        }
        else
        {
            //TWB - Characters currently are unable to collide
            //super.collide(collidableInterfaceCompositeInterface);
        }        
    }

    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("collideInterface", this, "collide"));
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }
}
