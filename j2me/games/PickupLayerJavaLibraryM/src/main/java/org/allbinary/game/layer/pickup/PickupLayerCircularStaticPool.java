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
package org.allbinary.game.layer.pickup;

import org.allbinary.layer.AllBinaryLayerCircularPool;

public class PickupLayerCircularStaticPool
   extends AllBinaryLayerCircularPool
{
	private static PickupLayerCircularStaticPool SINGLETON = 
		new PickupLayerCircularStaticPool();
   
   private PickupLayerCircularStaticPool()
   {
   }
      
   public static PickupLayerCircularStaticPool getInstance()
   {
      return SINGLETON;
   }
   
   public PickedUpLayerInterface getInstance(
      PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface, int x, int y, int z) 
      throws Exception
   {
      PickupLayer pickupLayer = 
         (PickupLayer) this.getNextInstance();
      
      pickupLayer.init(x, y, z);
      pickupLayer.init( 
              pickedUpLayerInterfaceFactoryInterface, 
              pickedUpLayerInterfaceFactoryInterface.getAnimationInterface());

      return pickupLayer;
   }
}