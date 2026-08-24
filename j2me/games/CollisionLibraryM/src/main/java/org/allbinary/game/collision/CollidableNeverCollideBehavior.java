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

import jsinterop.annotations.JsType;

import org.allbinary.game.layer.CollidableCompositeLayer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class CollidableNeverCollideBehavior extends CollidableBaseBehavior
{    

    @JsConstructor
    CollidableNeverCollideBehavior(boolean isCollidable)
    {
        super(isCollidable);
    }
    
    @Override
    @JsMethod
    public boolean isCollision(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer allbinaryCollidableLayer)
    {
       return false;
    }

    @Override
    @JsMethod
    public void collide(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer allbinaryCollidableLayer)
       throws Exception
    {
       // this.setPickedUp();
    }

    @Override
    @JsMethod
    public void collideInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       // this.setPickedUp();
    }
    
    @Override
    @JsMethod
    public boolean isCollisionInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       return false;
    }
}
