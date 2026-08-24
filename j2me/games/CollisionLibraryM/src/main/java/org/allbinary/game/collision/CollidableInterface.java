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
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.NamedInterface;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author USER
 */

@JsType
public interface CollidableInterface extends NamedInterface
{
   @JsMethod
   boolean isCollidable(final CollidableCompositeLayer ownerLayer)
           throws Exception;

   @JsMethod
   boolean isCollision(final CollidableCompositeLayer ownerLayer, CollidableCompositeLayer collidableInterfaceCompositeInterface)
           throws Exception;

   @JsMethod
   void collide(final CollidableCompositeLayer ownerLayer, CollidableCompositeLayer collidableInterfaceCompositeInterface)
           throws Exception;
   
   @JsMethod
   CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface);
}
