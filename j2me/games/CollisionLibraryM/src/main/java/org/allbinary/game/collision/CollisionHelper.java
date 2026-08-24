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
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class CollisionHelper {

   @JsProperty
   protected AllBinaryLayer sourceLayerInterface;
   
   @JsConstructor
   public CollisionHelper(AllBinaryLayer sourceLayerInterface)
   {
      this.sourceLayerInterface = sourceLayerInterface;
   }
   
   @JsMethod
   public boolean isCollidable(CollidableCompositeLayer layerInterface)
   {
      return true;
   }

   /**
    * @return the sourceLayerInterface
    */
   @JsMethod
   public AllBinaryLayer getOwnerLayerInterface()
   {
      return this.sourceLayerInterface;
   }

   /**
    * @param sourceLayerInterface the sourceLayerInterface to set
    */
   @JsMethod
   public void setOwnerLayerInterface(AllBinaryLayer sourceLayerInterface)
   {
      this.sourceLayerInterface = sourceLayerInterface;
   }
}
