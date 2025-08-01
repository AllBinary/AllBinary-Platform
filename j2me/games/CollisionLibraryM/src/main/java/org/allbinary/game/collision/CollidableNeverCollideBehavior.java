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
import org.allbinary.graphics.RectangleFactory;

public class CollidableNeverCollideBehavior extends CollidableBaseBehavior
{    
    /*
    public CollidableNeverCollideBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }
    */

    CollidableNeverCollideBehavior(boolean isCollidable)
    {
        super(new CollidableCompositeLayer(RectangleFactory.SINGLETON), isCollidable);
    }
    
    @Override
    public boolean isCollision(CollidableCompositeLayer allbinaryCollidableLayer)
    {
       return false;
    }

    @Override
    public void collide(CollidableCompositeLayer allbinaryCollidableLayer)
       throws Exception
    {
       // this.setPickedUp();
    }

    @Override
    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       // this.setPickedUp();
    }
    
    @Override
    public boolean isCollision(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       return false;
    }
}
