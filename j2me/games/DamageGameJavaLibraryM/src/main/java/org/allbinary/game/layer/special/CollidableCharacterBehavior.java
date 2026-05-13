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

public class CollidableCharacterBehavior
extends CollidableDestroyableDamageableBehavior 
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public CollidableCharacterBehavior(boolean collidable)
    {
        super(collidable);
    }

    @Override
    public void collide(final CollidableCompositeLayer ownerLayer, CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //this.logUtil.putF("collidableLayer", this, damageUtil.COLLIDE);        
        final CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
        final CollisionType collisionType = collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(ownerLayer);

        if (collisionType == collisionTypeFactory.PICKUP)
        {
            final CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer = (CollidableDestroyableDamageableLayer) ownerLayer;
            collidableDestroyableDamageableLayer.getPickupBehavior().doPickupLayer((PickedUpLayerInterface) collidableInterfaceCompositeInterface);
        }
        else
        {
            //TWB - Characters currently are unable to collide
            super.collide(ownerLayer, collidableInterfaceCompositeInterface);
        }        
    }

    @Override
    public void collideInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
        throws Exception
    {
        //this.logUtil.putF("collideInterface", this, damageUtil.COLLIDE);
        ForcedLogUtil.log(this.commonStrings.NOT_IMPLEMENTED, this);
    }
}
