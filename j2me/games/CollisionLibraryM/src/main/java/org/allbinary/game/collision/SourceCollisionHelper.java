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
import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author user
 */
public class SourceCollisionHelper extends CollisionHelper
{
   public SourceCollisionHelper(AllBinaryLayer layerInterface)
   {
      super(layerInterface);
   }
   
   //LayerInterface
   public boolean isCollidable(CollidableCompositeLayer layerInterface)
   {
      if(this.sourceLayerInterface != layerInterface)
      {
         return true;
      }
      return false;
   }
}
