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

import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;
import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.pickup.PickedUpLayerInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;

public class CollidableCharacterNoDamageBehavior
extends CollidableDestroyableDamageableBehavior 
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public CollidableCharacterNoDamageBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //logUtil.put("collidableLayer", this, damageUtil.COLLIDE);        
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
        //logUtil.put("collideInterface", this, damageUtil.COLLIDE);
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }
}
