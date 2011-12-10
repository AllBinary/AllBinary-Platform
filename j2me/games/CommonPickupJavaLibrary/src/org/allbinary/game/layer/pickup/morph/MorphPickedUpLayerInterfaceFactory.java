/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.game.layer.pickup.morph;

import org.allbinary.game.layer.pickup.IconLayerFactory;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactory;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerTypeFactory;
import org.allbinary.game.layer.pickup.PickupProcessorInterface;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.layer.AllBinaryLayer;

public class MorphPickedUpLayerInterfaceFactory extends PickedUpLayerInterfaceFactory
   implements PickupProcessorInterface
{
   private static PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface;
   
   public static void init()
      throws Exception
   {
      pickedUpLayerInterfaceFactoryInterface = new MorphPickedUpLayerInterfaceFactory();
   }
   
   private MorphLayerCircularStaticPool pool = 
      new MorphLayerCircularStaticPool(
          new MorphLayerFactory(), 1
      );
   
   private MorphPickedUpLayerInterfaceFactory()
      throws Exception
   {
      super(PickedUpLayerTypeFactory.getInstance().MORPH, 
    		  IconLayerFactory.getInstance(
              FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(MorphResources.getInstance().RESOURCE).getInstance(), 10, 10),
              FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(MorphResources.getInstance().RESOURCE).getInstance());
   }

   public int getTotal()
   {
      return 1;
   }
   
   public static PickedUpLayerInterfaceFactoryInterface getInstance()
   {
      return pickedUpLayerInterfaceFactoryInterface;
   }

   public void process(AllBinaryLayer sourceLayerInterface) throws Exception
   {
      pool.visit(sourceLayerInterface);
   }
}