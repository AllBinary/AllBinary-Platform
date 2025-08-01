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
package org.allbinary.game.collision;

import org.allbinary.layer.AllBinaryLayer;

public class CollidableAlwaysPickupNeverCollideBehavior extends CollidableNeverCollideBehavior
{
    CollidableAlwaysPickupNeverCollideBehavior()
    {
        //Yes it needs to do collision test but it never collides since it is picked up
        super(true);
    }

    /*
    public CollidableAlwaysPickupNeverCollideBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }
    */
    
    @Override
    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
       return CollisionTypeFactory.getInstance().PICKUP;
    }

}
