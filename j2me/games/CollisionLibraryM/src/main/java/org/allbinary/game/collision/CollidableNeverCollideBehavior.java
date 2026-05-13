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

import org.allbinary.game.layer.CollidableCompositeLayer;

public class CollidableNeverCollideBehavior extends CollidableBaseBehavior
{    

    CollidableNeverCollideBehavior(boolean isCollidable)
    {
        super(isCollidable);
    }
    
    @Override
    public boolean isCollision(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer allbinaryCollidableLayer)
    {
       return false;
    }

    @Override
    public void collide(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer allbinaryCollidableLayer)
       throws Exception
    {
       // this.setPickedUp();
    }

    @Override
    public void collideInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       // this.setPickedUp();
    }
    
    @Override
    public boolean isCollisionInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       return false;
    }
}
