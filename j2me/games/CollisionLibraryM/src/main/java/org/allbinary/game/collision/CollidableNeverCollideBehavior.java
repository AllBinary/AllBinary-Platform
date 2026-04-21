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
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.view.ViewPosition;

public class CollidableNeverCollideBehavior extends CollidableBaseBehavior
{    

    CollidableNeverCollideBehavior(boolean isCollidable)
    {
        super(new CollidableCompositeLayer(StringUtil.getInstance().EMPTY_STRING, RectangleFactory.SINGLETON, ViewPosition.getInstanceD(), CollidableNeverCollideBehaviorFactory.getInstance()), isCollidable);
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
