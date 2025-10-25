/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.waypoint;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.CollidableRTSBehavior;

import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;

public class CollidableWaypointBehavior
extends CollidableRTSBehavior 
{
    public CollidableWaypointBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    //public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
    //public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    @Override
    public boolean isCollision(CollidableCompositeLayer collisionLayer)
    {
        return false;
    }

    @Override
    public void collide(CollidableCompositeLayer collisionLayer)
            throws Exception
    {
    }

    @Override
    public boolean isCollision(CollidableInterfaceCompositeInterface collidableInterface)
    {
        return false;
    }

    @Override
    public void collide(CollidableInterfaceCompositeInterface collidableInterface)
            throws Exception
    {
    }
}
